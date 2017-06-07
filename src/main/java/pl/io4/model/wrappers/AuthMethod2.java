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
    public boolean authorize(int card_id, int pin, double price) throws InvalidPinException, CardNoCashException, AuthConnectionException  {
        boolean result = false;
        try {
            result = Auth2.authorize(card_id, pin, price);
        } catch (InvalidPinException e) {
            throw e;
        } catch (CardNoCashException e) {
            throw e;
        } catch (AuthConnectionException e) {
            throw e;
        }
        return  result;
    }
}
