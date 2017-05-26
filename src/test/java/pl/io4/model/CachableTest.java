package pl.io4.model;

import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.io4.model.cachable.CachableObject;

/**
 * Created by Zax37 on 20.05.2017.
 */
public class CachableTest {
    class SimpleTypesTestContainer extends CachableObject {
        public int integerValue;
        public double doubleValue;
        public boolean boolValue;
        public String string;

        @Override
        public JSONObject cache() {
            JSONObject ret = new JSONObject();
            ret.put("1", integerValue);
            ret.put("2", doubleValue);
            ret.put("3", boolValue);
            ret.put("4", string);
            return ret;
        }

        @Override
        public void load(JSONObject data) {
            integerValue = data.getInt("1");
            doubleValue = data.getDouble("2");
            boolValue = data.getBoolean("3");
            string = data.getString("4");
        }
    }

    @Test
    public void simpleTypesTest() {
        SimpleTypesTestContainer original = new SimpleTypesTestContainer(),
            fromCache = new SimpleTypesTestContainer();

        original.integerValue = 1;
        original.doubleValue = .256f;
        original.boolValue = true;
        original.string = "String";

        JSONObject cache = original.cache();
        fromCache.load(cache);

        assertEquals(original.integerValue, fromCache.integerValue);
        assertEquals(original.doubleValue, fromCache.doubleValue, 0);
        assertEquals(original.boolValue, fromCache.boolValue);
        assertEquals(original.string, fromCache.string);
    }
}
