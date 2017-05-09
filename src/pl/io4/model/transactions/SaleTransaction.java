package pl.io4.model.transactions;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marcin on 26.03.2017.
 */

public class SaleTransaction extends Transaction {

    private List<TransactionItem> productList;
    private DiscountHandler discountHandler;
    private double totalPrice;

    public SaleTransaction(){
        productList = new LinkedList<>();
        discountHandler = new DiscountHandler();
    }

    public List<TransactionItem> getProductList() {
        return productList;
    }

    public List<DiscountItem> getDiscountList() {
        return discountHandler.getDiscountList();
    }

    public TransactionItem addProduct(Product product, double quantity) {
        TransactionItem item = null;
        for (TransactionItem transactionItem : productList) {
            if (transactionItem.getProduct().equals(product)) {
                item = transactionItem;
            }
        }
        if ( item == null ) {
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

    public TransactionItem addProduct(Product product) {
        return addProduct(product, 1);
    }

    public Discount addDiscount(Discount discount, int quantity) throws DiscountException {
        return discountHandler.add(discount, quantity, totalPrice);
    }

    public double calculateTotalPrice() {
        return totalPrice - discountHandler.calculateTotalDiscount(totalPrice);
    }
}
