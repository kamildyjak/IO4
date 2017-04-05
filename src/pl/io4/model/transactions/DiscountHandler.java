package pl.io4.model.transactions;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by Marcin on 27.03.2017.
 */
public class DiscountHandler {

    private List<Discount> discountList;

    public DiscountHandler() {
        discountList = new LinkedList<>();
    }

    public void add(Discount discount, double totalPrice) throws DiscountException {
        try {
            discountList.add(discount);
            checkForOverlow(totalPrice);
        } catch (DiscountException exc) {
            discountList.remove(discount);
            throw exc;
        }
    }

    private void checkForOverlow(double totalPrice) throws DiscountException {
        if(calculateTotalDiscount(totalPrice) > totalPrice) {
            throw new DiscountException("Discount overlow");
        }
    }

    public double calculateTotalDiscount(double totalPrice) {
        double totalPercentageDiscount = getTotalPercentageDiscount();
        double totalVoucherDiscount = getTotalVoucherDiscount();
        return totalVoucherDiscount + totalPercentageDiscount * totalPrice;
    }

    private double getTotalPercentageDiscount() {
        return getTotalDiscount(DiscountType.PERCENTAGE);
    }

    private double getTotalVoucherDiscount() {
        return getTotalDiscount(DiscountType.VOUCHER);
    }

    private double getTotalDiscount(DiscountType type) {
        return discountList
                .stream()
                .filter(d -> d.getType() == type)
                .mapToDouble(d -> d.getValue())
                .sum();
    }
}
