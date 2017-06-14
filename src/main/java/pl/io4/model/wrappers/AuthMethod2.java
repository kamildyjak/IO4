package pl.io4.model.wrappers;

import out.authentication.Auth2;
import pl.io4.model.exceptions.AuthConnectionException;
import pl.io4.model.exceptions.CardNoCashException;
import pl.io4.model.exceptions.InvalidPinException;

/**
 * Created by Marcin on 10.05.2017.
 */
public final class AuthMethod2 implements Authorization {
    @Override
    public boolean authorize(int cardId, int pin, double price)
    throws InvalidPinException, CardNoCashException, AuthConnectionException  {
        boolean result = false;
        result = Auth2.authorize(cardId, pin, price);
        return  result;
    }
}
