package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class CodeReadOutException extends Exception {
    public CodeReadOutException(String message) {
        super(message);
    }

    public CodeReadOutException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
