package pl.io4.model.machines;

import java.util.List;
import org.json.JSONObject;
import pl.io4.model.Model;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Discount;
import pl.io4.model.exceptions.DiscountNotFoundException;
import pl.io4.model.exceptions.DiscountOverflowException;

/**
 * Created by Marcin on 27.03.2017.
 */
public final class DiscountsMachine extends CachableObject {
    private CachableList<Discount> discounts;
    private static final int PERCENT = 100;

    public DiscountsMachine() {
        discounts = new CachableArrayList<>(Discount.class);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Discount getDiscount(int id) throws DiscountNotFoundException {
        return discounts
                .stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElseThrow(() -> new DiscountNotFoundException(Model.getString("DISCOUNT_NOT_FOUND")));
    }

    public void add(Discount discount, double totalPrice) throws DiscountOverflowException {
        try {
            discounts.add(discount);
            checkForOverflow(totalPrice);
        } catch (DiscountOverflowException exc) {
            discounts.remove(discount);
            throw exc;
        }
    }


    private void checkForOverflow(double totalPrice) throws DiscountOverflowException {
        if (calculateTotalDiscount(totalPrice) > totalPrice) {
            throw new DiscountOverflowException(Model.getString("DISCOUNT_OVERFLOW"));
        }
    }

    public double calculateTotalDiscount(double totalPrice) {
        return calculateTotalDiscount(totalPrice, discounts);
    }

    public static double calculateTotalDiscount(double totalPrice, List<Discount> discounts) {
        double totalPercentageDiscount = getTotalPercentageDiscount(discounts);
        double totalVoucherDiscount = getTotalVoucherDiscount(discounts);
        return totalVoucherDiscount + totalPercentageDiscount * totalPrice / PERCENT;
    }

    public double getTotalPercentageDiscount() {
        return getTotalPercentageDiscount(discounts);
    }

    public static double getTotalPercentageDiscount(List<Discount> discounts) {
        return getTotalDiscount(Discount.DiscountType.PERCENTAGE, discounts);
    }

    public double getTotalVoucherDiscount() {
        return getTotalVoucherDiscount(discounts);
    }

    public static double getTotalVoucherDiscount(List<Discount> discounts) {
        return getTotalDiscount(Discount.DiscountType.VOUCHER, discounts);
    }

    public double getTotalDiscount(Discount.DiscountType type) {
        return getTotalDiscount(type, discounts);
    }

    public static double getTotalDiscount(Discount.DiscountType type, List<Discount> discounts) {
        return discounts
                .stream()
                .filter(d -> d.getType() == type)
                .mapToDouble(d -> d.getValue())
                .sum();
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("discounts", discounts.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        discounts.load(data.getJSONArray("discounts"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiscountsMachine that = (DiscountsMachine) o;
        return discounts.equals(that.discounts);
    }
}
