package pl.io4.model;

import org.junit.Before;
import org.junit.Test;
import pl.io4.model.transactions.*;

import static org.junit.Assert.*;

/**
 * Created by Marcin on 26.03.2017.
 */

public class SaleTransactionTest {

    SaleTransaction saleTransaction;

    @Before
    public void initBeforeEach() {
        saleTransaction = new SaleTransaction();
    }

    @Test
    public void addOneProductTest() {
        Product product = new Product("1", "test", 10.0);

        saleTransaction.addProduct(product);

        TransactionItem transactionItem = saleTransaction
                .getProductList()
                .stream()
                .filter(p -> p.getProduct().equals(product))
                .findFirst()
                .get();

        int expected = 1;
        int actual = transactionItem.getQuantity();

        assertEquals(expected , actual);
    }

    @Test
    public void addProductTwoTimesTest() {
        Product product = new Product("1", "test", 10.0);
        saleTransaction.addProduct(product);
        saleTransaction.addProduct(product);

        TransactionItem transactionItem = saleTransaction
                .getProductList()
                .stream()
                .filter(p -> p.getProduct().equals(product))
                .findFirst()
                .get();

        int expected = 2;
        int actual = transactionItem.getQuantity();

        assertEquals(expected , actual);
    }

    @Test
    public void checkTotalPriceTest() {
        double price1 = 10;
        Product product1 = new Product("1", "test", price1);
        saleTransaction.addProduct(product1);

        double price2 = 20;
        Product product2 = new Product("2", "test", price2);
        saleTransaction.addProduct(product2);

        double expected = price1 + price2;
        double actual = saleTransaction.calculateTotalPrice();

        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void addOnePercentageDiscountTest() {
        try {
            double price = 10;
            Product product = new Product("1", "test", price);

            Double discountValue = 0.2;
            Discount discount = new Discount(discountValue, DiscountType.PERCENTAGE);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount);

            double expected = price - price * discountValue;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountException exc) {
            assertTrue("DiscountException threw", false);
        }
    }

    @Test
    public void addTwoPercentageDiscountsTest() {
        try{
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue1 = 0.2;
            Discount discount1 = new Discount(discountValue1, DiscountType.PERCENTAGE);

            double discountValue2 = 0.3;
            Discount discount2 = new Discount(discountValue2, DiscountType.PERCENTAGE);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            double expected = price - price * (discountValue1 + discountValue2);
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountException exc) {
            assertTrue("DiscountException threw", false);
        }
    }

    @Test
    public void addOneVoucherDiscountTest() {
        try{
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue = 3;
            Discount discount = new Discount(discountValue, DiscountType.VOUCHER);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount);

            double expected = price - discountValue;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountException exc) {
            assertTrue("DiscountException threw", false);
        }
    }

    @Test
    public void addTwoVoucherDiscountsTest() {
        try {
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue1 = 3;
            Discount discount1 = new Discount(discountValue1, DiscountType.VOUCHER);

            double discountValue2 = 5;
            Discount discount2 = new Discount(discountValue2, DiscountType.VOUCHER);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            double expected = price - discountValue1 - discountValue2;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountException exc) {
            assertTrue("DiscountException threw", false);
        }
    }

    @Test
    public void addOneVoucherOnePercentageDiscountsTest() {
        try {
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue1 = 3;
            Discount discount1 = new Discount(discountValue1, DiscountType.VOUCHER);

            double discountValue2 = 0.2;
            Discount discount2 = new Discount(discountValue2, DiscountType.PERCENTAGE);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            double expected = price - discountValue1 - discountValue2 * price;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountException exc) {
            assertTrue("DiscountException threw", false);
        }
    }

    @Test
    public void overflowPercentageDiscount() {
        try {
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue1 = 0.6;
            Discount discount1 = new Discount(discountValue1, DiscountType.PERCENTAGE);

            double discountValue2 = 0.5;
            Discount discount2 = new Discount(discountValue2, DiscountType.PERCENTAGE);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue(false);

        } catch (DiscountException exc) {
            assertTrue("DiscountException should be thrown", true);
        }
    }

    @Test
    public void overflowVoucherDiscount() {
        try {
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue1 = 5;
            Discount discount1 = new Discount(discountValue1, DiscountType.VOUCHER);

            double discountValue2 = 6;
            Discount discount2 = new Discount(discountValue2, DiscountType.VOUCHER);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue(false);

        } catch (DiscountException exc) {
            assertTrue("DiscountException should be thrown", true);
        }
    }

    @Test
    public void overflowVoucherAndPercentageDiscount() {
        try {
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue1 = 5;
            Discount discount1 = new Discount(discountValue1, DiscountType.VOUCHER);

            double discountValue2 = 0.7;
            Discount discount2 = new Discount(discountValue2, DiscountType.PERCENTAGE);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue(false);

        } catch (DiscountException exc) {
            assertTrue("DiscountException should be thrown", true);
        }
    }

    @Test
    public void overflowPercentageAndVoucherDiscount() {
        try {
            double price = 10;
            Product product = new Product("1", "test", price);

            double discountValue1 = 0.8;
            Discount discount1 = new Discount(discountValue1, DiscountType.PERCENTAGE);

            double discountValue2 = 3;
            Discount discount2 = new Discount(discountValue2, DiscountType.VOUCHER);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue(false);

        } catch (DiscountException exc) {
            assertTrue("DiscountException should be thrown", true);
        }
    }

    @Test
    public void overflowCatchAndTryAgainDiscount() {
        try {
            try{
                double price = 10;
                Product product = new Product("1", "test", price);

                double discountValue1 = 0.5;
                Discount discount1 = new Discount(discountValue1, DiscountType.PERCENTAGE);

                double discountValue2 = 7;
                Discount discount2 = new Discount(discountValue2, DiscountType.VOUCHER);

                saleTransaction.addProduct(product);
                saleTransaction.addDiscount(discount1);
                saleTransaction.addDiscount(discount2);

                assertTrue("DiscountException should be thrown", false);
            } catch (DiscountException exc) {

                double discountValue3 = 3;
                Discount discount3 = new Discount(discountValue3, DiscountType.VOUCHER);
                saleTransaction.addDiscount(discount3);

                assertTrue( true);
            }
        } catch (DiscountException exc) {
            assertTrue("DiscountException threw", false);
        }
    }
}
