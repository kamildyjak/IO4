package pl.io4.model.exceptions;

/**
 * Created by Zax37 on 21.05.2017.
 */

public class IncorrectEmployeeDataException extends Exception {
    public IncorrectEmployeeDataException(String message) {
        super(message);
    }

    public IncorrectEmployeeDataException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
