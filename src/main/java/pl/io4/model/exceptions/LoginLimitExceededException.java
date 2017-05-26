package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class LoginLimitExceededException extends Exception {
    public LoginLimitExceededException(String message) {
        super(message);
    }

    public LoginLimitExceededException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
