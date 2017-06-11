package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class SyncCacheDatabaseException extends Exception {
    public SyncCacheDatabaseException(String message) {
        super(message);
    }

    public SyncCacheDatabaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
