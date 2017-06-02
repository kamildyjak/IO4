package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
