package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }

    public InvalidPinException(String message, Throwable throwable) {
        super(message, throwable);
    }
}