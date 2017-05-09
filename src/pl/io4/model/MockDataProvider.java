package pl.io4.model;

import pl.io4.model.transactions.Discount;
import pl.io4.model.transactions.DiscountType;
import pl.io4.model.transactions.Product;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Marcin on 28.04.2017.
 */
public class MockDataProvider extends DataProvider {

    @Override
    public List<Product> loadAllProducts() {
        List<Product> products = new LinkedList<>();
        products.add(new Product("1", "p1", 5.0));
        products.add(new Product("2", "p2", 2.0));
        products.add(new Product("3", "p3", 8.0));
        products.add(new Product("4", "p4", 7.0));
        products.add(new Product("5", "p5", 3.0));
        return products;
    }

    @Override
    public List<Discount> loadAllDiscounts() {
        List<Discount> discounts = new LinkedList<>();
        discounts.add(new Discount("1", 2, DiscountType.PERCENTAGE));
        discounts.add(new Discount("2", 2, DiscountType.VOUCHER));
        return discounts;
    }


}
