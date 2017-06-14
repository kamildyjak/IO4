package pl.io4.model.wrappers;

import pl.io4.model.exceptions.AuthConnectionException;
import pl.io4.model.exceptions.CardNoCashException;
import pl.io4.model.exceptions.InvalidPinException;

/**
 * Created by Marcin on 10.05.2017.
 */
public interface Authorization {
   boolean authorize(int id, int pin, double price)
   throws InvalidPinException, CardNoCashException, AuthConnectionException;
}
