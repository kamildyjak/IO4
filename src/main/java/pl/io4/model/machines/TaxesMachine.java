package pl.io4.model.machines;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.Cachable;
import pl.io4.model.entities.Product;
import pl.io4.model.entities.TaxRule;
import pl.io4.model.wrappers.TaxCalculator;
import pl.io4.model.wrappers.TaxCalculator1;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class TaxesMachine extends Cachable {
    private List<TaxRule> taxRules;
    private static final TaxCalculator TAX_CALCULATOR = new TaxCalculator1();

    public TaxesMachine() {
        taxRules = new ArrayList<TaxRule>();
    }

    public TaxRule getTaxRule(int id) {
        for (TaxRule rule : taxRules) {
            if (rule.getId() == id) {
                return rule;
            }
        }
        return null;
    }

    public static double getTax(Product product) {
        return TAX_CALCULATOR.calculateTax(product);
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("taxRules", Cachable.cache(taxRules));
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        Cachable.load(taxRules, TaxRule.class, data.getJSONArray("taxRules"));
    }
}
