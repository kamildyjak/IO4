package pl.io4.model.machines;

import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Product;
import pl.io4.model.exceptions.ProductNotFoundException;
import pl.io4.model.labels.ExceptionsLabels;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class ProductsMachine extends CachableObject {
    private CachableList<Product> products;

    public ProductsMachine() {
        products = new CachableArrayList<>(Product.class);
    }

    public Product getProduct(int id) throws ProductNotFoundException {
        return products
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(ExceptionsLabels.PRODUCT_NOT_FOUND));
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
