package pl.io4.model.wrappers;

import out.warehouses.Warehouse1;
import pl.io4.model.entities.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jperek on 11.06.2017.
 */

public class WarehouseSystem1 implements Warehouse {
    public Map<Product, Integer> receiveDelivery() {
        HashMap<Product, Integer> ret = new HashMap<>(Warehouse1.getDelivery());

        return ret;
    }
}
