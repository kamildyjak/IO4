package pl.io4.model.wrappers;

import out.tax_calculators.Calc2;
import pl.io4.model.entities.Product;

/**
 * Created by Marcin on 10.05.2017.
 */
public final class TaxCalculator2 implements TaxCalculator {

    @Override
    public double calculateTax(Product product) {
        return Calc2.licz(product.getTaxRule(), product.getPrice());
    }
}
