package out.tax_calculators;

import pl.io4.model.entities.TaxRule;

/**
 * Created by Marcin on 10.05.2017.
 */
public class Calc1 {
    public static double calc(double price, TaxRule taxRule){
        return taxRule.getPercent() * price;
    }
}
