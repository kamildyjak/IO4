package pl.io4.model.wrappers;

import out.receipt_generators.Generator1;
import pl.io4.model.transactions.SaleTransaction;
import pl.io4.model.transactions.TransactionItem;

/**
 * Created by Marcin on 28.05.2017.
 */
public final class ReceiptGenerator1 implements ReceiptGenerator {
    @Override
    public void generateReceipt(SaleTransaction transaction) {
        String receiptText = "";
        for (TransactionItem item : transaction.getProductsList()) {
            receiptText += item.toString();
            receiptText += "\n";
        }
        receiptText += "Total Price = " + transaction.calculateTotalPrice() + "\n";
        Generator1.generateReceipt(receiptText);
    }
}
