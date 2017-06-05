package out.tax_calculators;

import pl.io4.model.entities.TaxRule;

/**
 * Created by Marcin on 10.05.2017.
 */
public class Calc2 {
    public static double licz(TaxRule taxRule, double price){
        return price * taxRule.getPercent();
    }
}
