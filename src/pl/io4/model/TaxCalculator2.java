package pl.io4.model;

import pl.io4.model.external_services.Calc2;
import pl.io4.model.transactions.Product;

/**
 * Created by Marcin on 10.05.2017.
 */
public class TaxCalculator2 implements TaxCalculator {

    @Override
    public double calculateTax(Product product) {
        return Calc2.licz(product.getCategory(), product.getPrice());
    }
}
