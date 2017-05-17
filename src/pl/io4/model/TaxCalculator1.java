package pl.io4.model;

import pl.io4.model.external_services.Calc1;
import pl.io4.model.transactions.Product;

/**
 * Created by Marcin on 10.05.2017.
 */
public class TaxCalculator1 implements TaxCalculator {

    @Override
    public double calculateTax(Product product) {
        return Calc1.calc(product.getPrice(), product.getCategory());
    }
}
