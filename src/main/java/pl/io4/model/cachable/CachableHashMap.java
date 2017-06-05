package pl.io4.model.cachable;

import java.util.HashMap;
import org.json.JSONObject;

/**
 * Created by Zax37 on 25.05.2017.
 */
public final class CachableHashMap<V extends Cachable>
extends HashMap<String, V> implements Cachable {
    private Class<V> cls;

    public CachableHashMap(Class<V> cls) {
        super();
        this.cls = cls;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        for (String entry : keySet()) {
            ret.put(entry, get(entry).cache());
        }
        return ret;
    }

    @Override
    public void load(Object data) {
        if (data.getClass() != JSONObject.class) {
            throw new IllegalArgumentException("Not a JSONObject.");
        } else {
            load((JSONObject)data);
        }
    }

    public void load(JSONObject data) {
        clear();
        for (String key : data.keySet()) {
            try {
                V obj = cls.newInstance();
                obj.load(data.get(key));
                put(key, obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
