package pl.io4.model.machines;

import java.util.List;
import org.json.JSONObject;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Shop;

/**
 * Created by Zax37 on 21.05.2017.
 */
public final class ShopsMachine extends CachableObject {
    private CachableList<Shop> shops;

    public ShopsMachine() {
        shops = new CachableArrayList<>(Shop.class);
    }

    public void addShop(Shop shop) {
        shops.add(shop);
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ShopsMachine that = (ShopsMachine) o;
        return shops.equals(that.shops);
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("shops", shops.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        shops.load(data.getJSONArray("shops"));
    }
}
