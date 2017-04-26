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

    public void addProduct(Product product) {
        TransactionItem transactionItem = getTransactionItem(product);
        transactionItem.incrementQuantity();
        totalPrice += product.getPrice();
        productList.add(transactionItem);
    }

    public void addDiscount(Discount discount) throws DiscountException {
        discountHandler.add(discount, totalPrice);
    }

    public double calculateTotalPrice() {
        return totalPrice - discountHandler.calculateTotalDiscount(totalPrice);
    }

    private TransactionItem getTransactionItem(Product product) {
        for(TransactionItem transactionItem : productList) {
            if(transactionItem.getProduct().equals(product)) {
                return transactionItem;
            }
        }
        return new TransactionItem(product);
    }
}
