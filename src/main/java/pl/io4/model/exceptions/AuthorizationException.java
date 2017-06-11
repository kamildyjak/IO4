package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class AuthorizationException extends Exception {
    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
