package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class CodeReadingException extends Exception {
    public CodeReadingException(String message) {
        super(message);
    }

    public CodeReadingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
