package pl.io4.model.machines;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Employee;
import pl.io4.model.entities.Permissions;

/**
 * Created by Zax37 on 21.05.2017.
 */
public final class PermissionsMachine extends CachableObject {
    private CachableList<Permissions> permissions;

    public PermissionsMachine() {
        permissions = new CachableArrayList<>(Permissions.class);
    }

    public void addPermission(Permissions permission) {
        permissions.add(permission);
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
        return permissions.hashCode() == that.permissions.hashCode();
    }

    public List<Permissions> getPermissionsOf(Employee employee) {
        List<Permissions> ret = new ArrayList<>();
        for (Permissions rule : permissions) {
            if (rule.getEmployee().equals(employee)) {
                ret.add(rule);
            }
        }
        return ret;
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
