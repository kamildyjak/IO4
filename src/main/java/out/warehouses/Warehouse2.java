package out.warehouses;

import pl.io4.model.entities.Product;

import java.util.ArrayList;

/**
 * Created by jperek on 11.06.2017.
 */
public class Warehouse2 {
    static private ArrayList<Product> products = new ArrayList<>();
    static private ArrayList<Integer> quantities = new ArrayList<>();

    static public void generateList() {
        addProduct(new Product(54, "Masło Extra", 4.49), 8);
        addProduct(new Product(1337, "Mleko Hej!", 2.49), 13);
        addProduct(new Product(54, "Sok Tymbark", 4.39), 2);
        addProduct(new Product(54, "Baton Mars", 1.49), 7);
        addProduct(new Product(54, "Czekolada Milka", 4.49), 9);
        addProduct(new Product(54, "Woda Cechini Muszyna", 2.49), 66);
        addProduct(new Product(54, "Kajzerka", 0.49), 18);
        addProduct(new Product(54, "Wino Fresco", 14.49), 3);
    }

    static private void addProduct(Product product, int quantity) {
        products.add(product);
        quantities.add(quantity);
    }

    static public Product getProduct(int i) {
        return products.get(i);
    }

    static public Integer getQuantity(int i) {
        return quantities.get(i);
    }

    static public int getListLength() { return products.size(); }
}
