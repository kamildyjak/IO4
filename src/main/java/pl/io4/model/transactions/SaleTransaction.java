package pl.io4.model.transactions;

import java.util.LinkedList;
import java.util.List;
import pl.io4.model.entities.Product;
import pl.io4.model.entities.Transaction;
import pl.io4.model.exceptions.DiscountException;
import pl.io4.model.entities.Discount;
import pl.io4.model.machines.DiscountsMachine;

/**
 * Created by Marcin on 26.03.2017.
 */

public class SaleTransaction extends Transaction {
    private List<TransactionItem> productList;
    private DiscountsMachine discountsMachine;
    private double totalPrice;

    public SaleTransaction() {
        productList = new LinkedList<>();
        discountsMachine = new DiscountsMachine();
    }

    public final List<TransactionItem> getProductList() {
        return productList;
    }

    public final List<Discount> getDiscountList() {
        return discountsMachine.getDiscounts();
    }

    public final TransactionItem addProduct(Product product, double quantity) {
        TransactionItem item = null;
        for (TransactionItem transactionItem : productList) {
            if (transactionItem.getProduct().equals(product)) {
                item = transactionItem;
            }
        }
        if (item == null) {
            item = new TransactionItem(product, quantity);
            totalPrice += item.getTotalPrice();
            productList.add(item);
        } else {
            totalPrice -= item.getTotalPrice();
            item.incrementQuantity(quantity);
            totalPrice += item.getTotalPrice();
        }

        return item;
    }

    public final TransactionItem addProduct(Product product) {
        return addProduct(product, 1);
    }

    public final void addDiscount(Discount discount) throws DiscountException {
        discountsMachine.add(discount, totalPrice);
    }

    public final double calculateTotalPrice() {
        return totalPrice - discountsMachine.calculateTotalDiscount(totalPrice);
    }
}
