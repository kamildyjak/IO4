package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
