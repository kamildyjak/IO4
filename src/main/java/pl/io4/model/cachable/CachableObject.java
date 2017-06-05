package pl.io4.model.cachable;

import org.json.JSONObject;

/**
 * Created by Zax37 on 25.05.2017.
 */
public abstract class CachableObject implements Cachable {
    @Override
    public abstract JSONObject cache();

    @Override
    public final void load(Object data) {
        if (data.getClass() != JSONObject.class) {
            throw new IllegalArgumentException("Not a JSONObject.");
        } else {
            load((JSONObject)data);
        }
    }

    public abstract void load(JSONObject data);
}
