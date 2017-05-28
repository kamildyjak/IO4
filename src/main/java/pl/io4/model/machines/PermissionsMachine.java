package pl.io4.model.machines;

import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Permissions;

/**
 * Created by Zax37 on 21.05.2017.
 */
public final class PermissionsMachine extends CachableObject {
    private CachableList<Permissions> permissions;

    public PermissionsMachine() {
        permissions = new CachableArrayList<>(Permissions.class);
    }

    public void addPermission(Permissions permissions_) {
        permissions.add(permissions_);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PermissionsMachine that = (PermissionsMachine) o;
        return permissions.equals(that.permissions);
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("permissions", permissions.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        permissions.load(data.getJSONArray("permissions"));
    }
}
