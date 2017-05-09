package pl.io4.model;

import pl.io4.model.transactions.Discount;

import java.util.List;

/**
 * Created by Marcin on 07.05.2017.
 */
public class DiscountService {

    private List<Discount> discounts;

    public DiscountService(DataProvider dataProvider) {
        discounts = dataProvider.loadAllDiscounts();
    }

    public Discount findById(String id) throws ItemNotFoundException {
            return discounts
                    .stream()
                    .filter(d -> d.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new ItemNotFoundException("Nie znaleziono zniżki"));
    }
}