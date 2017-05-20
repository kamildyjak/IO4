package pl.io4.model.transactions;

import pl.io4.model.database.entities.Product;

/**
 * Created by Marcin on 26.03.2017.
 */

public final class TransactionItem {
    private final Product product;
    private double quantity;

    public TransactionItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void incrementQuantity(double increment) {
        quantity += increment;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
