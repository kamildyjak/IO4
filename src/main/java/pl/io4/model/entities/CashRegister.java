package pl.io4.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.json.JSONObject;
import pl.io4.model.cachable.CachableObject;

/**
 * Created by jacob on 25.04.2017.
 */
@Entity
@Table(name = "CashRegister", schema = "dbo", catalog = "io4")
public final class CashRegister extends CachableObject {
    private int id;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CashRegister that = (CashRegister) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("id", this.id);
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        id = data.getInt("id");
    }
}
