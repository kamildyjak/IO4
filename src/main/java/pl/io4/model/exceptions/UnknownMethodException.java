package pl.io4.model.exceptions;

/**
 * Created by Zax37 on 21.05.2017.
 */
public class UnknownMethodException extends Exception {
    public UnknownMethodException(String message) {
        super(message);
    }

    public UnknownMethodException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
