package pl.io4.model.transactions;

import java.text.DecimalFormat;

/**
 * Created by Marcin on 26.03.2017.
 */
public class Discount {

    private final String id;
    private final double value;
    private final DiscountType type;

    public Discount(String id, double value, DiscountType type) {
        this.id = id;
        this.value = value;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public DiscountType getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Discount discount = (Discount) obj;
        if(discount.getId() == this.getId()) {
            return true;
        }
        return false;
    }
}
