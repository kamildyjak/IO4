package pl.io4.model;

import pl.io4.model.transactions.Product;

/**
 * Created by Marcin on 10.05.2017.
 */
public interface TaxCalculator {

    double calculateTax(Product product);
}
