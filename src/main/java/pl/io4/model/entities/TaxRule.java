package pl.io4.model.entities;

import javax.persistence.Basic;
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
@Table(name = "TaxRule", schema = "dbo", catalog = "io4")
public final class TaxRule extends CachableObject {
    private int id;
    private String symbol;
    private byte percent;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "symbol", nullable = false, length = -1)
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Basic
    @Column(name = "percent", nullable = false)
    public byte getPercent() {
        return percent;
    }

    public void setPercent(byte percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaxRule that = (TaxRule) o;

        if (id != that.id) {
            return false;
        }
        if (percent != that.percent) {
            return false;
        }
        return symbol != null ? symbol.equals(that.symbol) : that.symbol == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (int) percent;
        return result;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("id", this.id);
        ret.put("symbol", this.symbol);
        ret.put("percent", this.percent);
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        id = data.getInt("id");
        symbol = data.getString("symbol");
        percent = (byte)data.getInt("percent");
    }
}
