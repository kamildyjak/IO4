package pl.io4.model.wrappers;

import pl.io4.model.entities.Product;

import java.util.Map;

/**
 * Created by jperek on 11.06.2017.
 */

public interface Warehouse {
    Map<Product, Integer> receiveDelivery();
}
