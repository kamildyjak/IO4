package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.views.SaleTransactionView;

/**
 * Created by Zax37 on 23.05.2017.
 */
public class ActionsMenuController extends Controller {
    public ActionsMenuController(NextGen app) throws NoSuchElementException {
        super(app);

        addButtonClickListener("startNewSaleTransaction", new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switchTo(SaleTransactionView.class,
                        SaleTransactionController.class);
            }
        });
    }
}
