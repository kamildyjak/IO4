package pl.io4.model.wrappers;

import out.tax_calculators.Calc1;
import pl.io4.model.entities.Product;

/**
 * Created by Marcin on 10.05.2017.
 */
public final class TaxCalculator1 implements TaxCalculator {

    @Override
    public double calculateTax(Product product) {
        return Calc1.calc(product.getPrice(), product.getTaxRule());
    }
}
