package pl.io4.model.transactions.discounts;

import java.util.List;
import java.util.LinkedList;

/**
 * Created by Marcin on 27.03.2017.
 */
public final class DiscountHandler {
    private List<DiscountItem> discountItemList;
    private static final int PERCENT = 100;

    public DiscountHandler() {
        discountItemList = new LinkedList<>();
    }

    public List<DiscountItem> getDiscountList() {
        return discountItemList;
    }

    public Discount add(Discount discount, int quantity, double totalPrice) throws DiscountException {
        DiscountItem item = null;
        for (DiscountItem discountItem : discountItemList) {
            if (discountItem.getDiscount().equals(discount)) {
                item = discountItem;
            }
        }

        if (item == null) {
            item = new DiscountItem(discount);
            discountItemList.add(item);
        } else {
            item.incrementQuantity(quantity);
        }

        try {
            checkForOverflow(totalPrice);
            return discount;
        } catch (DiscountException exc) {
            removeDiscount(item, quantity);
            throw exc;
        }
    }

    private void removeDiscount(DiscountItem discountItem, int quantity) {
        discountItem.decrementQuantity(quantity);
        if (discountItem.getQuantity() <= 0) {
            discountItemList.remove(discountItem);
        }
    }

    private void checkForOverflow(double totalPrice) throws DiscountException {
        if (calculateTotalDiscount(totalPrice) > totalPrice) {
            throw new DiscountException("Nadmiar zniżek");
        }
    }

    public double calculateTotalDiscount(double totalPrice) {
        double totalPercentageDiscount = getTotalPercentageDiscount();
        double totalVoucherDiscount = getTotalVoucherDiscount();
        return totalVoucherDiscount + totalPercentageDiscount * totalPrice / PERCENT;
    }

    private double getTotalPercentageDiscount() {
        return getTotalDiscount(DiscountType.PERCENTAGE);
    }

    private double getTotalVoucherDiscount() {
        return getTotalDiscount(DiscountType.VOUCHER);
    }

    private double getTotalDiscount(DiscountType type) {
        return discountItemList
                .stream()
                .filter(d -> d.getDiscount().getType() == type)
                .mapToDouble(d -> d.getTotalDiscount())
                .sum();
    }
}
