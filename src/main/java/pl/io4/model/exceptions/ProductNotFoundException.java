package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}