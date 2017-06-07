package out.tax_calculators;

import pl.io4.model.entities.TaxRule;
import pl.io4.model.exceptions.TaxSystemConnectionException;

/**
 * Created by Marcin on 10.05.2017.
 */
public class Calc2 {
    static boolean isConnected = false;

    public static boolean connect() {
        isConnected = true;
        return isConnected;
    }

    public static boolean isConnected() {
        return isConnected;
    }

    public static boolean isTaxRuleValid(TaxRule tr) {
        return true;
    }

    public static void updateTaxRule(TaxRule tr) {
        tr.setPercent(tr.getPercent());
    }

    public static double licz(TaxRule taxRule, double price) {
        return price * taxRule.getPercent();
    }
}
