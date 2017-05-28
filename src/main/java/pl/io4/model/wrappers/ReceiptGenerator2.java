package pl.io4.model.wrappers;

import out.receipt_generators.Generator2;
import pl.io4.model.transactions.SaleTransaction;

/**
 * Created by Marcin on 28.05.2017.
 */
public class ReceiptGenerator2 implements ReceiptGenerator {

    @Override
    public void generateReceipt(SaleTransaction transaction) {
        Generator2.generateReceipt(transaction.getProductsList(), transaction.calculateTotalPrice());
    }
}
