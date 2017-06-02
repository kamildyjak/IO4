package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class DiscountOverflowException extends Exception {
    public DiscountOverflowException(String message) {
        super(message);
    }

    public DiscountOverflowException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
