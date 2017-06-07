package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class TaxSystemConnectionException extends Exception {
    public TaxSystemConnectionException(String message) {
        super(message);
    }

    public TaxSystemConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}