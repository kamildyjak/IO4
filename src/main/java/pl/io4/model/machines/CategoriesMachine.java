package pl.io4.model.machines;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.Cachable;
import pl.io4.model.entities.Category;

/**
 * Created by Zax37 on 22.05.2017.
 */
public final class CategoriesMachine extends Cachable {
    private List<Category> categories;

    public CategoriesMachine() {
        categories = new ArrayList<Category>();
    }

    public Category getCategory(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("categories", Cachable.cache(categories));
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        Cachable.load(categories, Category.class, data.getJSONArray("categories"));
    }
}
