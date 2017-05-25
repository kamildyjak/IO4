package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.transactions.SaleTransaction;
import pl.io4.views.PaymentView;

/**
 * Created by Zax37 on 23.05.2017.
 */
public final class PaymentController extends Controller {
    private SaleTransaction saleTransaction;
    private PaymentView view;

    public PaymentController(NextGen app) throws NoSuchElementException {
        super(app);
        view = getView();

        addButtonClickListener("payByCash", new ChangeListener() {
            @Override
            public void changed(com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent event, Actor actor) {
                view.showPayByCash();
            }
        });

        addButtonClickListener("payWithCard", new ChangeListener() {
            @Override
            public void changed(com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent event, Actor actor) {
                view.showPayWithCard();
            }
        });
    }

    void setSaleTransaction(SaleTransaction saleTransaction) {
        this.saleTransaction = saleTransaction;
        view.setTotalPrice(saleTransaction.calculateTotalPrice());
    }
}
