package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class EmptyTransactionException extends Exception {
    public EmptyTransactionException(String message) {
        super(message);
    }

    public EmptyTransactionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
