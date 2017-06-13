package pl.io4.model.wrappers;

import out.tax_calculators.Calc1;
import pl.io4.model.entities.Product;
import pl.io4.model.entities.TaxRule;
import pl.io4.model.exceptions.TaxSystemConnectionException;

/**
 * Created by Marcin on 10.05.2017.
 */
public final class TaxCalculator1 implements TaxCalculator {
    public TaxCalculator1() {
        Calc1.connect();
    }

    @Override
    public void connect() {
        if (!Calc1.isConnected()) {
            Calc1.connect();
        }
    }

    @Override
    public double calculateTax(Product product) throws TaxSystemConnectionException {
        if (Calc1.isConnected()) {
            return Calc1.calc(product.getPrice(), product.getTaxRule());
        } else {
            throw new TaxSystemConnectionException("Tax system not connected");
        }
    }

    @Override
    public void updateTaxRule(TaxRule tr)  throws TaxSystemConnectionException {
        if (Calc1.isConnected()) {
            if (Calc1.isTaxRuleValid(tr)) {
                Calc1.updateTaxRule(tr);
            } else {
                throw new TaxSystemConnectionException("Invaid tax rule");
            }
        } else {
            throw new TaxSystemConnectionException("Tax system not connected");
        }
    }
}
