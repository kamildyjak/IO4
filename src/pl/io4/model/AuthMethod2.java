package pl.io4.model;

import pl.io4.model.external_services.Auth2;

/**
 * Created by Marcin on 10.05.2017.
 */
public class AuthMethod2 implements Authorization {

    @Override
    public boolean authorize(int id, int pin, double price) {
        return Auth2.authorize(id, pin);
    }
}
