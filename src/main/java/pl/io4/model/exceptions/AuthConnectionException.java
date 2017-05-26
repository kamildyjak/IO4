package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class AuthConnectionException extends Exception {
    public AuthConnectionException(String message) {
        super(message);
    }

    public AuthConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}