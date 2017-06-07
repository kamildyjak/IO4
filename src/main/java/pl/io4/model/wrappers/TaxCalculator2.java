package pl.io4.model.wrappers;

import out.tax_calculators.Calc2;
import pl.io4.model.entities.Product;
import pl.io4.model.entities.TaxRule;
import pl.io4.model.exceptions.TaxSystemConnectionException;

/**
 * Created by Marcin on 10.05.2017.
 */
public final class TaxCalculator2 implements TaxCalculator {

    @Override
    public double calculateTax(Product product) throws TaxSystemConnectionException {
        if (Calc2.isConnected()) {
            return Calc2.licz(product.getTaxRule(), product.getPrice());
        }
        else
            throw new TaxSystemConnectionException("Tax system not connected");
    }

    @Override
    public void updateTaxRule(TaxRule tr) throws TaxSystemConnectionException {
        if (Calc2.isConnected()) {
            if (Calc2.isTaxRuleValid(tr)) {
                Calc2.updateTaxRule(tr);
            }
            else
                throw new TaxSystemConnectionException("Invaid tax rule");
        }
        else
            throw new TaxSystemConnectionException("Tax system not connected");
    }
}
