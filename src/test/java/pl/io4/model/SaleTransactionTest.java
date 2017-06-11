package pl.io4.model;

import org.junit.Before;
import org.junit.Test;
import pl.io4.model.entities.Product;
import pl.io4.model.exceptions.DiscountOverflowException;
import pl.io4.model.transactions.*;
import pl.io4.model.entities.Discount;
import pl.io4.model.entities.Discount.DiscountType;

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
        Product product = new Product(1, "test", 10.0);

        saleTransaction.addProduct(product);

        TransactionItem transactionItem = saleTransaction
                .getProductsList()
                .stream()
                .filter(p -> p.getProduct().equals(product))
                .findFirst()
                .get();

        double expected = 1;
        double actual = transactionItem.getQuantity();

        assertEquals(expected , actual, 0.001);
    }

    @Test
    public void addProductTwoTimesTest() {
        Product product = new Product(1, "test", 10.0);
        saleTransaction.addProduct(product);
        saleTransaction.addProduct(product);

        TransactionItem transactionItem = saleTransaction
                .getProductsList()
                .stream()
                .filter(p -> p.getProduct().equals(product))
                .findFirst()
                .get();

        double expected = 2;
        double actual = transactionItem.getQuantity();

        assertEquals(expected , actual, 0.001);
    }

    @Test
    public void checkTotalPriceTest() {
        double price1 = 10;
        Product product1 = new Product(1, "test", price1);
        saleTransaction.addProduct(product1);

        double price2 = 20;
        Product product2 = new Product(2, "test", price2);
        saleTransaction.addProduct(product2);

        double expected = price1 + price2;
        double actual = saleTransaction.calculateTotalPrice();

        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void addOnePercentageDiscountTest() {
        try {
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue = 20;
            Discount discount = new Discount(1, DiscountType.PERCENTAGE, discountValue);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount);

            double expected = price - price * discountValue / 100;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException threw", false);
        }
    }

    @Test
    public void addTwoPercentageDiscountsTest() {
        try{
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue1 = 20;
            Discount discount1 = new Discount(1, DiscountType.PERCENTAGE, discountValue1);

            double discountValue2 = 30;
            Discount discount2 = new Discount(2, DiscountType.PERCENTAGE, discountValue2);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            double expected = price - price * (discountValue1 + discountValue2) / 100;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException threw", false);
        }
    }

    @Test
    public void addOneVoucherDiscountTest() {
        try{
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue = 3;
            Discount discount = new Discount(1, DiscountType.VOUCHER, discountValue);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount);

            double expected = price - discountValue;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException threw", false);
        }
    }

    @Test
    public void addTwoVoucherDiscountsTest() {
        try {
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue1 = 3;
            Discount discount1 = new Discount(1, DiscountType.VOUCHER, discountValue1);

            double discountValue2 = 5;
            Discount discount2 = new Discount(2, DiscountType.VOUCHER, discountValue2);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            double expected = price - discountValue1 - discountValue2;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException threw", false);
        }
    }

    @Test
    public void addOneVoucherOnePercentageDiscountsTest() {
        try {
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue1 = 3;
            Discount discount1 = new Discount(1, DiscountType.VOUCHER, discountValue1);

            double discountValue2 = 20;
            Discount discount2 = new Discount(2, DiscountType.PERCENTAGE, discountValue2);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            double expected = price - discountValue1 - discountValue2 * price / 100;
            double actual = saleTransaction.calculateTotalPrice();

            assertEquals(expected, actual, 0.001);
        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException threw", false);
        }
    }

    @Test
    public void overflowPercentageDiscount() {
        try {
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue1 = 60;
            Discount discount1 = new Discount(1, DiscountType.PERCENTAGE, discountValue1);

            double discountValue2 = 50;
            Discount discount2 = new Discount(2, DiscountType.PERCENTAGE, discountValue2);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue(false);

        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException should be thrown", true);
        }
    }

    @Test
    public void overflowVoucherDiscount() {
        try {
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue1 = 5;
            Discount discount1 = new Discount(1, DiscountType.VOUCHER, discountValue1);

            double discountValue2 = 6;
            Discount discount2 = new Discount(2, DiscountType.VOUCHER, discountValue2);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue(false);

        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException should be thrown", true);
        }
    }

    @Test
    public void overflowVoucherAndPercentageDiscount() {
        try {
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue1 = 5;
            Discount discount1 = new Discount(1, DiscountType.VOUCHER, discountValue1);

            double discountValue2 = 70;
            Discount discount2 = new Discount(2, DiscountType.PERCENTAGE, discountValue2);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue("DiscountOverflowException should be thrown",false);
        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException should be thrown", true);
        }
    }

    @Test
    public void overflowPercentageAndVoucherDiscount() {
        try {
            double price = 10;
            Product product = new Product(1, "test", price);

            double discountValue1 = 80;
            Discount discount1 = new Discount(1, DiscountType.PERCENTAGE, discountValue1);

            double discountValue2 = 3;
            Discount discount2 = new Discount(2, DiscountType.VOUCHER, discountValue2);

            saleTransaction.addProduct(product);
            saleTransaction.addDiscount(discount1);
            saleTransaction.addDiscount(discount2);

            assertTrue(false);

        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountNotFoundException should be thrown", true);
        }
    }

    @Test
    public void overflowCatchAndTryAgainDiscount() {
        try {
            try{
                double price = 10;
                Product product = new Product(1, "test", price);

                double discountValue1 = 50;
                Discount discount1 = new Discount(1, DiscountType.PERCENTAGE, discountValue1);

                double discountValue2 = 7;
                Discount discount2 = new Discount(2, DiscountType.VOUCHER, discountValue2);

                saleTransaction.addProduct(product);
                saleTransaction.addDiscount(discount1);
                saleTransaction.addDiscount(discount2);

                assertTrue("DiscountOverflowException should be thrown", false);
            } catch (DiscountOverflowException exc) {

                double discountValue3 = 3;
                Discount discount3 = new Discount(3, DiscountType.VOUCHER, discountValue3);
                saleTransaction.addDiscount(discount3);

                assertTrue( true);
            }
        } catch (DiscountOverflowException exc) {
            assertTrue("DiscountOverflowException threw", false);
        }
    }
}
