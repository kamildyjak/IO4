package pl.io4.model.wrappers;

import out.authentication.Auth1;
import pl.io4.model.exceptions.AuthConnectionException;
import pl.io4.model.exceptions.CardNoCashException;
import pl.io4.model.exceptions.InvalidPinException;

/**
 * Created by Marcin on 10.05.2017.
 */
public final class AuthMethod1 implements Authorization {
    @Override
    public boolean authorize(int id, int pin, double price) throws InvalidPinException, CardNoCashException, AuthConnectionException {
        boolean result = false;
        try {
            result = Auth1.authorize(id, pin, price);
        } catch (InvalidPinException e) {
            throw e;
        } catch (CardNoCashException e) {
            throw e;
        } catch (AuthConnectionException e) {
            throw e;
        }
        return result;
    }
}
