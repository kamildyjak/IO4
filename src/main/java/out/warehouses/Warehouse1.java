package out.warehouses;

import pl.io4.model.entities.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jperek on 11.06.2017.
 */
public class Warehouse1 {
    static public Map<Product, Integer> getDelivery() {
        HashMap<Product, Integer> ret = new HashMap<>();
        ret.put(new Product(54, "Masło Extra", 4.49), 8);
        ret.put(new Product(1337, "Mleko Hej!", 2.49), 13);
        ret.put(new Product(54, "Sok Tymbark", 4.39), 2);
        ret.put(new Product(54, "Baton Mars", 1.49), 7);
        ret.put(new Product(54, "Czekolada Milka", 4.49), 9);
        ret.put(new Product(54, "Woda Cechini Muszyna", 2.49), 66);
        ret.put(new Product(54, "Kajzerka", 0.49), 18);
        ret.put(new Product(54, "Wino Fresco", 14.49), 3);
        return ret;
    }
}
