package out.receipt_generators;

import pl.io4.model.transactions.TransactionItem;
import java.util.List;

/**
 * Created by Marcin on 28.05.2017.
 */
public class Generator2 {

    public static void generateReceipt(List<TransactionItem> items, double priceTotal) {
        String receiptText = "";
        for(TransactionItem item : items) {
            receiptText += item.toString();
            receiptText += "\n";
        }
        receiptText += "Total Price = " + priceTotal + "\n";
        System.out.printf(receiptText);
    }
}
