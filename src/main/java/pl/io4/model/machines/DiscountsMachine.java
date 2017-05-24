package pl.io4.model.machines;

import java.util.List;
import java.util.LinkedList;
import org.json.JSONObject;
import pl.io4.model.Cachable;
import pl.io4.model.entities.Discount;
import pl.io4.model.exceptions.DiscountException;

/**
 * Created by Marcin on 27.03.2017.
 */
public final class DiscountsMachine extends Cachable {
    private List<Discount> discounts;
    private static final int PERCENT = 100;

    public DiscountsMachine() {
        discounts = new LinkedList<>();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public Discount getDiscount(int id) {
        for (Discount discount : discounts) {
            if (discount.getId() == id) {
                return discount;
            }
        }
        return null;
    }

    public void add(Discount discount, double totalPrice) throws DiscountException {
        try {
            discounts.add(discount);
            checkForOverlow(totalPrice);
        } catch (DiscountException exc) {
            discounts.remove(discount);
            throw exc;
        }
    }

    private void checkForOverlow(double totalPrice) throws DiscountException {
        if (calculateTotalDiscount(totalPrice) > totalPrice) {
            throw new DiscountException("Discount overlow");
        }
    }

    public double calculateTotalDiscount(double totalPrice) {
        double totalPercentageDiscount = getTotalPercentageDiscount();
        double totalVoucherDiscount = getTotalVoucherDiscount();
        return totalVoucherDiscount + totalPercentageDiscount * totalPrice / PERCENT;
    }

    private double getTotalPercentageDiscount() {
        return getTotalDiscount(Discount.DiscountType.PERCENTAGE);
    }

    private double getTotalVoucherDiscount() {
        return getTotalDiscount(Discount.DiscountType.VOUCHER);
    }

    private double getTotalDiscount(Discount.DiscountType type) {
        return discounts
                .stream()
                .filter(d -> d.getType() == type)
                .mapToDouble(d -> d.getValue())
                .sum();
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("discounts", Cachable.cache(discounts));
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        Cachable.load(discounts, Discount.class, data.getJSONArray("discounts"));
    }
}
