package pl.io4.model.wrappers;

import pl.io4.model.entities.Product;

/**
 * Created by Marcin on 10.05.2017.
 */
public interface TaxCalculator {

    double calculateTax(Product product);
}
