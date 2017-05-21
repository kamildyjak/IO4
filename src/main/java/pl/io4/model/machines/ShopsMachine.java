package pl.io4.model.machines;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.Cachable;
import pl.io4.model.entities.Shop;

/**
 * Created by Zax37 on 21.05.2017.
 */
public final class ShopsMachine extends Cachable {
    private List<Shop> shops;

    public ShopsMachine() {
        shops = new ArrayList<Shop>();
    }

    public List<Shop> getShops() {
        return shops;
    }

    public Shop getShop(int id) {
        for (Shop shop : shops) {
            if (shop.getId() == id) {
                return shop;
            }
        }
        return null;
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("shops", Cachable.cache(shops));
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        Cachable.load(shops, Shop.class, data.getJSONArray("shops"));
    }
}
