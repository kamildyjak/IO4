package pl.io4.model.transactions;

/**
 * Created by Marcin on 26.03.2017.
 */
public class Product {

    private final String id;
    private final String name;
    private final Double price;
    private final char category;

    public Product(String id, String name, Double price, char category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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

    public char getCategory() {
        return category;
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
