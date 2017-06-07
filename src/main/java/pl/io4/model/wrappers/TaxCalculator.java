package pl.io4.model.wrappers;

import pl.io4.model.entities.Product;
import pl.io4.model.entities.TaxRule;
import pl.io4.model.exceptions.TaxSystemConnectionException;

/**
 * Created by Marcin on 10.05.2017.
 */
public interface TaxCalculator {

    double calculateTax(Product product)  throws TaxSystemConnectionException;
    void updateTaxRule(TaxRule tr)  throws TaxSystemConnectionException;
}
