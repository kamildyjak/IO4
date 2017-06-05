package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class WriteFileException extends Exception {
    public WriteFileException(String message) {
        super(message);
    }

    public WriteFileException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
