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
public final class SaleTransaction extends Transaction {
    private List<TransactionItem> productsList;
    private DiscountsMachine discountsMachine;
    private double totalPrice;

    public SaleTransaction() {
        productsList = new LinkedList<>();
        discountsMachine = new DiscountsMachine();
    }

    public List<TransactionItem> getProductsList() {
        return productsList;
    }

    public List<Discount> getDiscountsList() {
        return discountsMachine.getDiscounts();
    }

    public DiscountsMachine getDiscountsMachine() {
        return discountsMachine;
    }

    public TransactionItem addProduct(Product product, double quantity) {
        TransactionItem item = null;
        for (TransactionItem transactionItem : productsList) {
            if (transactionItem.getProduct().equals(product)) {
                item = transactionItem;
            }
        }
        if (item == null) {
            item = new TransactionItem(product, quantity);
            totalPrice += item.getTotalPrice();
            productsList.add(item);
        } else {
            totalPrice -= item.getTotalPrice();
            item.incrementQuantity(quantity);
            totalPrice += item.getTotalPrice();
        }

        return item;
    }

    public TransactionItem addProduct(Product product) {
        return addProduct(product, 1);
    }

    public void addDiscount(Discount discount) throws DiscountException {
        discountsMachine.add(discount, totalPrice);
    }

    public double calculateTotalPrice() {
        return totalPrice - discountsMachine.calculateTotalDiscount(totalPrice);
    }
}
