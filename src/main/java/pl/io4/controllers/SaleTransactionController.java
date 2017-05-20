package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.database.entities.Product;
import pl.io4.model.transactions.SaleTransaction;
import pl.io4.views.SaleTransactionView;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Zax37 on 08.04.2017.
 */

public class SaleTransactionController extends Controller {
    private ArrayList<Product> products;
    private SaleTransaction saleTransaction;
    private SaleTransactionView view;
    private Iterator<Product> iterator;

    private static final int TO_BOTTOM = 100;

    public SaleTransactionController(NextGen app) throws NoSuchElementException {
        super(app);
        view = getView();
        saleTransaction = new SaleTransaction();

        ScrollPane scroll = getElement("scroll");

        addButtonClickListener("addButton", new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                view.setProductsList(saleTransaction.getProductList(), saleTransaction.calculateTotalPrice());
                scroll.setScrollPercentY(TO_BOTTOM);
            }
        });

        view.setProductsList(saleTransaction.getProductList(), saleTransaction.calculateTotalPrice());
    }
}
