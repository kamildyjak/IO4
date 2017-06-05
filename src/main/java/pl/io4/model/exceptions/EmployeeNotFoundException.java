package pl.io4.model.exceptions;

/**
 * Created by Marcin on 26.05.2017.
 */
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }

    public EmployeeNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
