package pl.io4.model.machines;

import com.badlogic.gdx.Gdx;
import java.text.DecimalFormat;
import org.json.JSONObject;
import pl.io4.model.cachable.CachableObject;

/**
 * Created by Zax37 on 19.05.2017.
 */
public final class LocalizationMachine extends CachableObject {
    private JSONObject strings;
    private static final String CURRENCY_SYMBOL = " zł";
    private String currentLanguage;

    public String getString(String string) {
        try {
            return strings.get(string).toString();
        } catch (NullPointerException e) {
            return string;
        }
    }

    public void setLanguage(String lang) {
        String file = Gdx.files.internal("lang/" + lang + ".json")
                .readString();
        strings = new JSONObject(file);
        currentLanguage = lang;
    }

    public static String formatQuantity(double quantity) {
        int iQuantity = (int)quantity;
        if (quantity == iQuantity) {
            return Integer.toString(iQuantity);
        }
        return new DecimalFormat("0.00").format(quantity);
    }

    public static String formatPrice(double price, boolean withCurrency) {
        if (withCurrency) {
            return new DecimalFormat("0.00").format(price) + CURRENCY_SYMBOL;
        } else {
            return new DecimalFormat("0.00").format(price);
        }
    }

    public static String getCurrencySymbol() {
        return CURRENCY_SYMBOL;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("lang", currentLanguage);
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        setLanguage(data.getString("lang"));
    }
}