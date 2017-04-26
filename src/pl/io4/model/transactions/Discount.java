package pl.io4.model.transactions;

/**
 * Created by Marcin on 26.03.2017.
 */
public class Discount {

    private final double value;
    private final DiscountType type;

    public Discount(double value, DiscountType type) {
        this.value = value;
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public DiscountType getType() {
        return type;
    }
}
