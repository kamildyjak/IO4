package pl.io4.model;

/**
 * Created by Marcin on 27.03.2017.
 */

public class DiscountException extends Exception {

    public DiscountException(String message) {
        super(message);
    }

    public DiscountException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
