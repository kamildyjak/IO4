package pl.io4.model.machines;

import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Product;
import pl.io4.model.entities.TaxRule;
import pl.io4.model.exceptions.TaxSystemConnectionException;
import pl.io4.model.wrappers.TaxCalculator;
import pl.io4.model.wrappers.TaxCalculator1;

import java.util.Calendar;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class TaxesMachine extends CachableObject {
    private CachableList<TaxRule> taxRules;
    private Calendar lastUpdateDate;
    private static final TaxCalculator TAX_CALCULATOR = new TaxCalculator1();

    public TaxesMachine() {
        taxRules = new CachableArrayList<>(TaxRule.class);
        lastUpdateDate = Calendar.getInstance();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaxesMachine that = (TaxesMachine) o;
        return taxRules.hashCode() == that.taxRules.hashCode();
    }

    public void addTaxRule(TaxRule tr) {
        try {
            TAX_CALCULATOR.updateTaxRule(tr);
            taxRules.add(tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public TaxRule getTaxRule(int id) {
        for (TaxRule rule : taxRules) {
            if (rule.getId() == id) {
                return rule;
            }
        }
        return null;
    }

    public double getTax(Product product) throws TaxSystemConnectionException {
        Calendar now = Calendar.getInstance(); // todo: move checking if tax rules should be updated to a better place
        now.add(Calendar.DAY_OF_MONTH, -1);
        if (now.after(lastUpdateDate)) {
            updateTaxRules();
        }

        return TAX_CALCULATOR.calculateTax(product);
    }

    public TaxesMachine clean() {
        taxRules.clear();
        return this;
    }

    void updateTaxRules() {
        try {
            for (TaxRule tr : taxRules) {
                TAX_CALCULATOR.updateTaxRule(tr);
            }
            lastUpdateDate = Calendar.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        updateTaxRules();
    }
}
