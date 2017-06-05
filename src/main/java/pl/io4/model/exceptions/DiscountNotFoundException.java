package pl.io4.model.exceptions;

/**
 * Created by Marcin on 27.03.2017.
 */

public class DiscountNotFoundException extends Exception {
    public DiscountNotFoundException(String message) {
        super(message);
    }

    public DiscountNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
