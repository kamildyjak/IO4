package pl.io4.model.database.entities;

import javax.persistence.*;

/**
 * Created by jacob on 25.04.2017.
 */
@Entity
@Table(name = "Product", schema = "dbo", catalog = "io4")
public class ProductEntity {
    private int id;
    private String name;
    private Integer category;
    private Integer taxRule;
    private double price;

    public ProductEntity() {
    }

    public ProductEntity(int id, String name, Integer category, Integer taxRule, double price) {
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
    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Basic
    @Column(name = "taxRule", nullable = true)
    public Integer getTaxRule() {
        return taxRule;
    }

    public void setTaxRule(Integer taxRule) {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (name != that.name) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (taxRule != null ? !taxRule.equals(that.taxRule) : that.taxRule != null) return false;

        return true;
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
}
