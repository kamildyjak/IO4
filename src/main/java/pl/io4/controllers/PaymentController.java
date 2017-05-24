package pl.io4.controllers;

import pl.io4.NextGen;
import pl.io4.model.transactions.SaleTransaction;
import pl.io4.views.PaymentView;

/**
 * Created by Zax37 on 23.05.2017.
 */
public final class PaymentController extends Controller {
    private SaleTransaction saleTransaction;
    private PaymentView view;

    public PaymentController(NextGen app) {
        super(app);
        view = getView();
    }

    void setSaleTransaction(SaleTransaction saleTransaction) {
        this.saleTransaction = saleTransaction;
        view.setTotalPrice(saleTransaction.calculateTotalPrice());
    }
}
