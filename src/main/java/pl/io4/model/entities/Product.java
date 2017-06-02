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
 * Created by jacob on 25.04.2017.
 */
@Entity
@Table(name = "Product", schema = "dbo", catalog = "io4")
public final class Product extends CachableObject {
    private int id;
    private String name;
    private Category category;
    private TaxRule taxRule;
    private double price;

    public Product() {

    }

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(int id, String name, Category category, TaxRule taxRule, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.taxRule = taxRule;
        this.price = price;
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
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "category", nullable = true)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Basic
    @Column(name = "taxRule", nullable = true)
    public TaxRule getTaxRule() {
        return taxRule;
    }

    public void setTaxRule(TaxRule taxRule) {
        this.taxRule = taxRule;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product that = (Product) o;

        if (id != that.id) {
            return false;
        }
        if (name != that.name) {
            return false;
        }
        if (Double.compare(that.price, price) != 0) {
            return false;
        }
        if (category != null ? !category.equals(that.category) : that.category != null) {
            return false;
        }
        return taxRule != null ? taxRule.equals(that.taxRule) : that.taxRule == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + name.length();
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (taxRule != null ? taxRule.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("id", this.id);
        ret.put("name", this.name);
        ret.put("category", this.category.getId());
        if (this.taxRule != null) {
            ret.put("taxRule", this.taxRule.getId());
        }
        ret.put("price", this.price);
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        id = data.getInt("id");
        name = data.getString("name");
        category = Model.getCategoriesMachine().getCategory(data.getInt("category"));
        if(data.has("taxRule")) {
            taxRule = Model.getTaxesMachine().getTaxRule(data.getInt("taxRule"));
        }
        price = data.getDouble("price");
    }
}
