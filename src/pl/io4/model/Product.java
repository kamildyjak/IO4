package pl.io4.model;

/**
 * Created by Marcin on 26.03.2017.
 */
public class Product {

    private final String id;
    private final String name;
    private final Double price;

    public Product(String id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Product product = (Product) obj;
        if(product.getId().equals(this.getId())) {
            return true;
        }
        return false;
    }
}
