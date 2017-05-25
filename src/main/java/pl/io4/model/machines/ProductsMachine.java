package pl.io4.model.machines;

import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Product;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class ProductsMachine extends CachableObject {
    private CachableList<Product> products;

    public ProductsMachine() {
        products = new CachableArrayList<>(Product.class);
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
        ret.put("products", products.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        products.load(data.getJSONArray("products"));
    }
}
