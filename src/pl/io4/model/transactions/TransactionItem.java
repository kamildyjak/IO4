package pl.io4.model.transactions;

/**
 * Created by Marcin on 26.03.2017.
 */

public class TransactionItem {

    private final Product product;
    private int quantity;

    public TransactionItem(Product product) {
        this.product = product;
        quantity = 0;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void incrementQuantity() {
        quantity++;
    }
}
