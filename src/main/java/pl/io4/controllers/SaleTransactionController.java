package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.Model;
import pl.io4.model.entities.Discount;
import pl.io4.model.entities.Product;
import pl.io4.model.exceptions.CodeReadingException;
import pl.io4.model.machines.ProductsMachine;
import pl.io4.model.transactions.SaleTransaction;
import pl.io4.model.wrappers.ReceiptGenerator1;
import pl.io4.views.PaymentView;
import pl.io4.views.SaleTransactionView;

/**
 * Created by Zax37 on 08.04.2017.
 */
public class SaleTransactionController extends Controller {
    private SaleTransaction saleTransaction;
    private ProductsMachine productsMachine;
    private SaleTransactionView view;
    private static final int TO_BOTTOM = 100;

    public SaleTransactionController(NextGen app) throws NoSuchElementException {
        super(app);
        view = getView();
        saleTransaction = new SaleTransaction();
        productsMachine = Model.getProductsMachine();

        addButtonClickListener("addProductButton", new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
            try {
                Product product = productsMachine.getProduct(getCode(view.getProductCode()));
                double quantity = getQuantity(view.getProductQuantity());
                saleTransaction.addProduct(product, quantity);
                view.setProductsList(
                        saleTransaction.getProductsList(),
                        saleTransaction.getDiscountsMachine(),
                        saleTransaction.calculateTotalPrice()
                );
                ScrollPane scroll = getElement("scroll");
                scroll.setScrollPercentY(TO_BOTTOM);
            } catch (Exception exc) {
                view.addErrorMessage(exc.getMessage());
            } finally {
                view.clearTextFields();
            }
            }
        });

        addButtonClickListener("addDiscountButton", new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
            try {
                Discount discount = Model.getDiscountsMachine()
                        .getDiscount(getCode(view.getDiscountCode()));
                saleTransaction.addDiscount(discount);

                view.setProductsList(
                        saleTransaction.getProductsList(),
                        saleTransaction.getDiscountsMachine(),
                        saleTransaction.calculateTotalPrice()
                );
                ScrollPane scroll = getElement("scroll");
                scroll.setScrollPercentY(TO_BOTTOM);
            } catch (Exception exc) {
                view.addErrorMessage(exc.getMessage());
            } finally {
                view.clearTextFields();
            }
            }
        });

        addButtonClickListener("endButton", new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor)  {
            try {
                switchTo(PaymentView.class, PaymentController.class);
                PaymentController controller = app.getController();
                controller.setSaleTransaction(saleTransaction);
            } catch (Exception exc) {
                view.addErrorMessage(exc.getMessage());
            }
            }
        });
    }

    private double getQuantity(String quantity) {
        if (quantity.isEmpty()) {
            return 1;
        }
        return Double.valueOf(quantity);
    }

    private int getCode(String code) throws CodeReadingException {
        try {
            return Integer.parseInt(code);
        } catch(NumberFormatException exc) {
            throw new CodeReadingException(Model.getString("CODE_READING_ERROR"));
        }

    }
}
