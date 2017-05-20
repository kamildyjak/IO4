package pl.io4.model.transactions.discounts;

/**
 * Created by Marcin on 09.05.2017.
 */
public final class DiscountItem {
    private Discount discount;
    private int quantity;

    public DiscountItem(Discount discount, int quantity) {
        this.discount = discount;
        this.quantity = quantity;
    }

    public DiscountItem(Discount discount) {
        this(discount, 1);
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity(int increment) {
        quantity += increment;
    }

    public void decrementQuantity(int decrement) {
        quantity -= decrement;
    }

    public double getTotalDiscount() {
        return discount.getValue() * quantity;
    }
}
