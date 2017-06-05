package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class EmployeePermissionException extends Exception {
    public EmployeePermissionException(String message) {
        super(message);
    }

    public EmployeePermissionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
