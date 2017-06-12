package pl.io4.model.machines;

import org.json.JSONObject;
import pl.io4.model.Model;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Product;
import pl.io4.model.exceptions.ProductNotFoundException;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class ProductsMachine extends CachableObject {
    private CachableList<Product> products;

    public ProductsMachine() {
        products = new CachableArrayList<>(Product.class);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProduct(int id) throws ProductNotFoundException {
        return products
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(Model.getString("PRODUCT_NOT_FOUND")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductsMachine that = (ProductsMachine) o;
        return products.equals(that.products);
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
