package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.transactions.Product;
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

    private static final Product productWithWeight = new Product("4", "Marchew", 1.99);

    public SaleTransactionController (NextGen app) throws NoSuchElementException {
        super(app);
        view = (SaleTransactionView)super.view;
        saleTransaction = new SaleTransaction();
        products = new ArrayList<Product>(); //imitacja skanera lub wyboru ręcznego produktów
        products.add(new Product("0", "Mleko", 1.80));
        products.add(products.get(0));
        products.add(new Product("1", "Masło", 1.20));
        products.add(new Product("2", "Jajka", 3.99));
        products.add(new Product("3", "Śmietanka", 2.26));
        iterator = products.iterator();

        addButtonClickListener("addButton", new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if (!iterator.hasNext()) {
                    saleTransaction.addProduct(productWithWeight, 0.20001);
                } else {
                    saleTransaction.addProduct(iterator.next());
                }
                view.setProductsList(saleTransaction.getProductList(), saleTransaction.calculateTotalPrice());
                ((ScrollPane)interactiveElements.get("scroll")).setScrollPercentY(100);
            }
        });

        view.setProductsList(saleTransaction.getProductList(), saleTransaction.calculateTotalPrice());
    }
}
