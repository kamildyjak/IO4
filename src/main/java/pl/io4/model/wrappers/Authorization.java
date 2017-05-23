package pl.io4.model.wrappers;

/**
 * Created by Marcin on 10.05.2017.
 */
public interface Authorization {
   boolean authorize(int id, int pin, double price);
}
