package pl.io4.model;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Zax37 on 20.05.2017.
 *
 * Klasa abstrakcyjna upraszczająca cache'owanie danych do plików JSON.
 */
public abstract class Cachable {
    protected abstract JSONObject cache();
    protected abstract void load(JSONObject data);

    protected static <V> JSONObject cache(Map<String, V> map) {
        JSONObject ret = new JSONObject();
        for (Map.Entry<String, V> entry : map.entrySet()) {
            ret.put(entry.getKey(), entry.getValue());
        }
        return ret;
    }

    protected static <V> void load(Map<String, V> map, JSONObject data) {
        map.clear();
        for (String key : data.keySet()) {
            map.put(key, (V)data.get(key));
        }
    }

    protected static <V extends Cachable> JSONArray cache(List<V> list) {
        JSONArray ret = new JSONArray();
        for (V entry : list) {
            ret.put(entry.cache());
        }
        return ret;
    }

    protected static <V extends Cachable> void load(List<V> list, Class<V> cls, JSONArray data) {
        list.clear();
        for (int i = 0, size = data.length(); i < size; i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            try {
                V element = cls.newInstance();
                element.load(jsonObject);
                list.add(element);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected static <T extends Enum<T>> JSONArray cache(EnumSet<T> enumSet) {
        JSONArray ret = new JSONArray();
        for (T value : enumSet) {
            ret.put(value.toString());
        }
        return ret;
    }

    protected static <T extends Enum<T>> EnumSet<T> load(Class<T> cls, JSONArray data) {
        EnumSet<T> set = EnumSet.noneOf(cls);
        for (int i = 0, size = data.length(); i < size; i++) {
            for (T enu : EnumSet.allOf(cls)) {
                if (enu.toString().equals(data.getString(i))) {
                    set.add(enu);
                    break;
                }
            }
        }
        return set;
    }
}
