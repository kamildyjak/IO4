package pl.io4.model.exceptions;

/**
 * Created by kamil on 06.06.2017.
 */
public class CardNoCashException extends Exception  {
    public CardNoCashException(String message) {
        super(message);
    }

    public CardNoCashException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
