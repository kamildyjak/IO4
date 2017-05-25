package pl.io4.model.cachable;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Zax37 on 25.05.2017.
 */
public final class CachableArrayList<V extends Cachable>
extends ArrayList<V> implements CachableList<V> {
    private Class<V> cls;

    public CachableArrayList(Class<V> cls) {
        super();
        this.cls = cls;
    }

    @Override
    public JSONArray cache() {
        JSONArray ret = new JSONArray();
        for (V entry : this) {
            ret.put(entry.cache());
        }
        return ret;
    }

    @Override
    public void load(Object data) {
        if (data.getClass() != JSONArray.class) {
            throw new IllegalArgumentException("Not a JSONArray.");
        } else {
            load((JSONArray)data);
        }
    }

    public void load(JSONArray data) {
        clear();
        for (int i = 0, size = data.length(); i < size; i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            try {
                V element = cls.newInstance();
                element.load(jsonObject);
                add(element);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
