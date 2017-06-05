package pl.io4.model.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.json.JSONObject;
import pl.io4.model.Model;
import pl.io4.model.cachable.CachableObject;

/**
 * Created by Zax37 on 21.05.2017.
 */
@Entity
@Table(name = "Permissions", schema = "dbo", catalog = "io4")
public final class Permissions extends CachableObject {
    private Employee employee;
    private Shop shop;
    private Integer accessLevel;

    public Permissions() {

    }

    public Permissions(Employee employee, Shop shop, Integer accessLevel) {
        this.employee = employee;
        this.shop = shop;
        this.accessLevel = accessLevel;
    }

    @Id
    @Column(name = "employee", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Basic
    @Column(name = "shop", nullable = false)
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Basic
    @Column(name = "accessLevel", nullable = false)
    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Permissions that = (Permissions) o;
        if (employee != that.employee) {
            return false;
        }
        if (shop != that.shop) {
            return false;
        }
        return accessLevel.equals(that.accessLevel);
    }

    @Override
    public int hashCode() {
        int result;
        result = employee.hashCode();
        result = 31 * result + shop.hashCode();
        result = 31 * result + accessLevel.hashCode();
        return result;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("employee", employee.getPesel());
        ret.put("shop", shop.getId());
        ret.put("accessLevel", accessLevel);
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        employee = Model.getEmployeesMachine().getEmployee(data.getString("employee"));
        shop = Model.getShopsMachine().getShop(data.getInt("shop"));
        accessLevel = data.getInt("accessLevel");
    }
}
