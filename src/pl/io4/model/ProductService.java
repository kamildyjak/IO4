package pl.io4.model;

import pl.io4.model.transactions.Product;
import java.util.List;

/**
 * Created by Marcin on 28.04.2017.
 */
public class ProductService {

    private List<Product> products;

    public ProductService(DataProvider dataProvider) {
            products = dataProvider.loadAllProducts();
    }

    public Product findByProduct(String productCode) throws ItemNotFoundException {
        return products
                .stream()
                .filter(p -> p.getId().equals(productCode))
                .findFirst()
                .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono produktu"));
    }
}
