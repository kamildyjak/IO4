package pl.io4.model.wrappers;

import out.warehouses.Warehouse2;
import pl.io4.model.entities.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jperek on 11.06.2017.
 */

public final class WarehouseSystem2 implements Warehouse {
    public Map<Product, Integer> receiveDelivery() {
        Warehouse2.generateList();

        HashMap<Product, Integer> ret = new HashMap<>();
        for (int i = 0; i < Warehouse2.getListLength(); ++i) {
            ret.put(Warehouse2.getProduct(i), Warehouse2.getQuantity(i));
        }

        return ret;
    }
}
