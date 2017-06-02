package pl.io4.model.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.json.JSONObject;
import pl.io4.model.cachable.CachableObject;

/**
 * Created by Marcin on 26.03.2017.
 */
@Entity
@Table(name = "Discount", schema = "dbo", catalog = "io4")
public final class Discount extends CachableObject {
    public enum DiscountType {
        PERCENTAGE,
        VOUCHER;

        public static DiscountType fromBoolean(boolean type) {
            if (type) {
                return DiscountType.VOUCHER;
            } else {
                return DiscountType.PERCENTAGE;
            }
        }
    }

    private int id;
    private DiscountType type;
    private double value;

    public Discount() {

    }

    public Discount(int id, boolean type, double value) {
        this.id = id;
        this.type = DiscountType.fromBoolean(type);
        this.value = value;
    }

    public Discount(int id, DiscountType type, double value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "value", nullable = false)
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Discount that = (Discount) o;
        return id == that.id && type == that.type
                && value == that.value;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type == DiscountType.VOUCHER ? 1 : 0);
        long temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("id", this.id);
        ret.put("type", this.type == DiscountType.VOUCHER);
        ret.put("value", this.value);
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        id = data.getInt("id");
        type = DiscountType.fromBoolean(data.getBoolean("type"));
        value = data.getDouble("value");
    }
}
