package pl.io4.model.database.entities;

import javax.persistence.*;

/**
 * Created by jacob on 25.04.2017.
 */
@Entity
@Table(name = "TaxRule", schema = "dbo", catalog = "io4")
public class TaxRuleEntity {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxRuleEntity that = (TaxRuleEntity) o;

        if (id != that.id) return false;
        if (percent != that.percent) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (int) percent;
        return result;
    }
}
