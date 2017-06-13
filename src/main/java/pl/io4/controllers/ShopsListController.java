package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.Model;
import pl.io4.model.entities.Shop;
import pl.io4.views.ActionsMenuView;
import pl.io4.views.LoginView;

/**
 * Created by Zax37 on 30.05.2017.
 */
public class ShopsListController extends Controller {
    public ShopsListController(NextGen app) throws NoSuchElementException {
        super(app);

        for (Shop shop : Model.getAvaibleShops()) {
            addButtonClickListener(Integer.toString(shop.getId()), new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Model.setCurrentlyChosenShop(shop);
                    switchTo(ActionsMenuView.class,
                            ActionsMenuController.class);
                }
            });
        }

        addButtonClickListener("logout", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchTo(LoginView.class,
                        LoginController.class);
            }
        });
    }
}
