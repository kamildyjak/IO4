package pl.io4.model.machines;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.Cachable;
import pl.io4.model.entities.Permissions;

/**
 * Created by Zax37 on 21.05.2017.
 */
public final class PermissionsMachine extends Cachable {
    private List<Permissions> permissions;

    public PermissionsMachine() {
        permissions = new ArrayList<Permissions>();
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("permissions", Cachable.cache(permissions));
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        Cachable.load(permissions, Permissions.class, data.getJSONArray("permissions"));
    }
}
