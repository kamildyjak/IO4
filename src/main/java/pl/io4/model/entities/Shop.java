package pl.io4.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Basic;
import org.json.JSONObject;
import pl.io4.model.Cachable;

/**
 * Created by jacob on 25.04.2017.
 */
@Entity
@Table(name = "Shop", schema = "dbo", catalog = "io4")
public final class Shop extends Cachable {
    private static final int LENGTH_NAME = 10;
    private static final int LENGTH_MAX = 2147483647;
    private int id;
    private String name;
    private String address;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = LENGTH_NAME)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = false, length = LENGTH_MAX)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Shop that = (Shop) o;

        if (id != that.id) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    protected JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("id", this.id);
        ret.put("name", this.name);
        ret.put("address", this.address);
        return ret;
    }

    @Override
    protected void load(JSONObject data) {
        id = data.getInt("id");
        name = data.getString("name");
        address = data.getString("address");
    }
}
