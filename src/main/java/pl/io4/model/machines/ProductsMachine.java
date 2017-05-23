package pl.io4.model.machines;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.Cachable;
import pl.io4.model.entities.Product;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class ProductsMachine extends Cachable {
    private List<Product> products;

    public ProductsMachine() {
        products = new ArrayList<Product>();
    }

    public Product getProduct(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("products", Cachable.cache(products));
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        Cachable.load(products, Product.class, data.getJSONArray("products"));
    }
}
