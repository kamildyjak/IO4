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

    public void addProduct(Product product, double quantity) {
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
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addDiscount(Discount discount) throws DiscountException {
        discountHandler.add(discount, totalPrice);
    }

    public double calculateTotalPrice() {
        return totalPrice - discountHandler.calculateTotalDiscount(totalPrice);
    }
}
