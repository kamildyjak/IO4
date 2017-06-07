package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.Model;
import pl.io4.model.exceptions.AuthConnectionException;
import pl.io4.model.exceptions.CardNoCashException;
import pl.io4.model.exceptions.InvalidPinException;
import pl.io4.model.transactions.SaleTransaction;
import pl.io4.model.wrappers.AuthMethod1;
import pl.io4.model.wrappers.Authorization;
import pl.io4.views.ActionsMenuView;
import pl.io4.views.PaymentView;
import pl.io4.views.SaleTransactionView;

/**
 * Created by Zax37 on 23.05.2017.
 */
public final class PaymentController extends Controller {
    private SaleTransaction saleTransaction;
    private PaymentView view;
    private NextGen app;
    private Authorization cardAuth;
    private boolean returnButton;

    public PaymentController(NextGen app) throws NoSuchElementException {
        super(app);
        this.app = app;
        view = getView();
        cardAuth = new AuthMethod1();
        returnButton = false;
        addPaymentMethodButtonListeners();
    }

    private void addReturnButtonListener() throws NoSuchElementException {
        addButtonClickListener("returnPaymentSelect", new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                view.showPaymentMethods();
                if(!returnButton){
                    try {
                        addPaymentMethodButtonListeners();
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void addPaymentMethodButtonListeners() throws NoSuchElementException {
        addButtonClickListener("payByCash", new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                view.showPayByCash();
                try {
                    addReturnButtonListener();
                    addButtonClickListener("acceptPayment", new ChangeListener() {
                        @Override
                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                            try {
                                TextField cash = getElement("acceptCash");
                                double paid = Double.parseDouble(cash.getText());
                                double price = saleTransaction.calculateTotalPrice();
                                if (paid < price) {
                                    view.addErrorMessage(Model.getString("PAYMENT_DOES_NOT_COVER_VALUE"));
                                } else {
                                    view.showCashBack(paid - price);
                                    addButtonClickListener("finalize", new ChangeListener() {
                                        @Override
                                        public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                                            app.switchTo(ActionsMenuView.class,
                                                    ActionsMenuController.class);
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (NoSuchElementException e) {
                    e.printStackTrace();
                }
            }
        });


        addButtonClickListener("payWithCard", new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                try{
                    double price = saleTransaction.calculateTotalPrice();
                    cardAuth.authorize(1, 1234, price);
                    view.cardAccepted();
                    try {
                        addButtonClickListener("finalize", new ChangeListener() {
                            @Override
                            public void changed(ChangeEvent event, Actor actor) {
                                app.switchTo(ActionsMenuView.class,
                                        ActionsMenuController.class);
                            }
                        });
                    } catch (NoSuchElementException e) {
                        e.printStackTrace();
                    }
                } catch (InvalidPinException e) {
                    view.showException(e.getMessage());
                } catch (CardNoCashException e) {
                    view.showException(e.getMessage());
                } catch (AuthConnectionException e) {
                    view.showException(e.getMessage());
                }

                if(!returnButton) {
                    try {
                        addReturnButtonListener();
                        returnButton = true;
                    } catch (NoSuchElementException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        addButtonClickListener("returnSale", new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                try {
                    switchTo(SaleTransactionView.class, SaleTransactionController.class);
                    SaleTransactionController controller = app.getController();
                    controller.setSaleTransaction(saleTransaction);
                } catch (Exception exc) {
                    view.addErrorMessage(exc.getMessage());
                }
            }
        });
    }

    void setSaleTransaction(SaleTransaction saleTransaction) {
        this.saleTransaction = saleTransaction;
        view.setTotalPrice(saleTransaction.calculateTotalPrice());
    }
}
