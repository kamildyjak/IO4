package pl.io4.model.wrappers;

import out.authentication.Auth1;
/**
 * Created by Marcin on 10.05.2017.
 */
public final class AuthMethod1 implements Authorization {
    @Override
    public boolean authorize(int id, int pin, double price) {
        return Auth1.authorize(id, pin, price);
    }
}
