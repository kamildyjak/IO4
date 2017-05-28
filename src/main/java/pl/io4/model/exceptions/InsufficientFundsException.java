package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
