package pl.io4.model;

import pl.io4.model.transactions.Discount;
import pl.io4.model.transactions.Product;
import java.util.List;

/**
 * Created by Marcin on 28.04.2017.
 */
public abstract class DataProvider {

    public abstract List<Product> loadAllProducts();
    public abstract List<Discount> loadAllDiscounts();
}
