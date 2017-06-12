package pl.io4.model.machines;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Category;
import pl.io4.model.entities.Product;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class CategoriesMachine extends CachableObject {
    private CachableList<Category> categories;
    private Map<Category, List<Product>> productsMap;

    public CategoriesMachine() {
        categories = new CachableArrayList<>(Category.class);
        productsMap = new HashMap<>();
    }
    public void addCategory(Category category) {
        categories.add(category);
    }

    public Category getCategory(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public List<Product> getCategoryProducts(Category category) {
        return productsMap.get(category);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CategoriesMachine that = (CategoriesMachine) o;
        return categories.equals(that.categories);
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("categories", categories.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        categories.load(data.getJSONArray("categories"));
    }
}
