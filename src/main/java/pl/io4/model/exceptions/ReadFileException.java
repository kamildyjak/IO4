package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class ReadFileException extends Exception {
    public ReadFileException(String message) {
        super(message);
    }

    public ReadFileException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
