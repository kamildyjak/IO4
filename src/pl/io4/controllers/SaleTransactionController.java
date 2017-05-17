package pl.io4.controllers;

import pl.io4.model.*;

import java.util.LinkedList;
import java.util.List;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.transactions.*;
import pl.io4.views.SaleTransactionView;

/**
 * Created by Zax37 on 08.04.2017.
 */

public class SaleTransactionController extends Controller {
    private List<SaleTransaction> saleTransactions;
    private ActualSaleTransaction actualSaleTransaction;
    private DiscountService discountService;
    private DataProvider dataProvider;
    private ProductService productService;
    private SaleTransactionView view;
    private Authorization auth;

    public SaleTransactionController (NextGen app) throws NoSuchElementException {
        super(app);
        view = (SaleTransactionView)super.view;
        actualSaleTransaction = new ActualSaleTransaction();
        saleTransactions = new LinkedList<>();
        dataProvider = new MockDataProvider();
        productService = new ProductService(dataProvider);
        discountService = new DiscountService(dataProvider);

        try{
            makeNewSale();
        } catch(SaleTransactionException exc) {
            view.addErrorMessage(exc.getMessage());
        }

        addButtonClickListener("addProductButton", new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                try {
                    addProduct(view.getProductCode(), getQuantity(view.getProductQuantity()));

                    view.setProductsList(
                            actualSaleTransaction.getTransactionItemList(),
                            actualSaleTransaction.getDiscountList(),
                            actualSaleTransaction.calculateTotalPrice()
                    );
                    ((ScrollPane) interactiveElements.get("scroll")).setScrollPercentY(100);
                } catch(Exception exc) {
                    view.addErrorMessage(exc.getMessage());
                } finally {
                    view.clearTextFields();
                }
            }
        });

        addButtonClickListener("addDiscountButton", new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    addDiscount(view.getDiscountCode(), (int) getQuantity(view.getDiscountQuantity()));

                    view.setProductsList(
                            actualSaleTransaction.getTransactionItemList(),
                            actualSaleTransaction.getDiscountList(),
                            actualSaleTransaction.calculateTotalPrice()
                    );
                    ((ScrollPane) interactiveElements.get("scroll")).setScrollPercentY(100);
                } catch(Exception exc) {
                    view.addErrorMessage(exc.getMessage());
                } finally {
                    view.clearTextFields();
                }
            }
        });

        addButtonClickListener("endButton", new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor)  {
                try{
                    auth = new AuthMethod1();
                    auth.authorize(0, 0, actualSaleTransaction.calculateTotalPrice());
                } catch (Exception exc) {
                    view.addErrorMessage(exc.getMessage());
                }
            }
        });
    }

    public void makeNewSale() throws SaleTransactionException{
        actualSaleTransaction.newSaleTransaction();
        saleTransactions.add(actualSaleTransaction.getSaleTransaction());
    }

    public SaleTransaction getActualSaleTransaction() throws SaleTransactionException {
        return actualSaleTransaction.getSaleTransaction();
    }

    public TransactionItem addProduct(String barCode, double quantity) throws SaleTransactionException, ItemNotFoundException {
        SaleTransaction saleTransaction = getActualSaleTransaction();
        Product product = productService.findByProduct(barCode);
        return saleTransaction.addProduct(product, quantity);
    }

    public Discount addDiscount(String id, int quantity) throws SaleTransactionException, ItemNotFoundException, DiscountException {
        SaleTransaction saleTransaction = getActualSaleTransaction();
        Discount discount = discountService.findById(id);
        return saleTransaction.addDiscount(discount, quantity);
    }

    public void finishSale() {
        actualSaleTransaction.finishSaleTransaction();
    }

    private class ActualSaleTransaction {

        private SaleTransaction saleTransaction;
        private boolean isActive;

        public ActualSaleTransaction() {
            isActive = false;
        }

        public void newSaleTransaction() throws SaleTransactionException {
            checkIfIsNotActive();
            saleTransaction = new SaleTransaction();
            isActive = true;
        }

        public SaleTransaction getSaleTransaction() throws SaleTransactionException {
            checkIfIsActive();
            return saleTransaction;
        }

        public List<TransactionItem> getTransactionItemList() throws SaleTransactionException {
            checkIfIsActive();
            return saleTransaction.getProductList();
        }

        public List<DiscountItem> getDiscountList() throws SaleTransactionException {
            checkIfIsActive();
            return saleTransaction.getDiscountList();
        }

        public double calculateTotalPrice() throws SaleTransactionException {
            checkIfIsActive();
            return saleTransaction.calculateTotalPrice();
        }

        public void finishSaleTransaction() {
            isActive = false;
        }

        private void checkIfIsNotActive() throws SaleTransactionException {
            if(isActive == true) {
                throw new SaleTransactionException("Transakcja nie została zakończona");
            }
        }

        private void checkIfIsActive() throws SaleTransactionException {
            if(isActive == false) {
                throw new SaleTransactionException("Transakcja nie istnieje");
            }
        }
    }

    private class SaleTransactionException extends Exception {

        public SaleTransactionException(String message) {
            super(message);
        }
    }

    private double getQuantity(String quantity) {
        if(quantity.isEmpty() == true) {
            return 1;
        }
        return Double.valueOf(quantity);
    }

}
