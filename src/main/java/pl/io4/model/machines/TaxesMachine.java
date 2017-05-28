package pl.io4.model.machines;

import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Product;
import pl.io4.model.entities.TaxRule;
import pl.io4.model.wrappers.TaxCalculator;
import pl.io4.model.wrappers.TaxCalculator1;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class TaxesMachine extends CachableObject {
    private CachableList<TaxRule> taxRules;
    private static final TaxCalculator TAX_CALCULATOR = new TaxCalculator1();

    public TaxesMachine() {
        taxRules = new CachableArrayList<>(TaxRule.class);
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
        ret.put("taxRules", taxRules.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        taxRules.load(data.getJSONArray("taxRules"));
    }
}
