package pl.io4.model.wrappers;

import out.authentication.Auth2;

/**
 * Created by Marcin on 10.05.2017.
 */
public final class AuthMethod2 implements Authorization {
    @Override
    public boolean authorize(int id, int pin, double price) {
        return Auth2.authorize(id, pin);
    }
}
