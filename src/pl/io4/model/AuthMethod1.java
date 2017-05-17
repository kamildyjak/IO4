package pl.io4.model;

import pl.io4.model.external_services.Auth1;
/**
 * Created by Marcin on 10.05.2017.
 */
public class AuthMethod1 implements Authorization {

    @Override
    public boolean authorize(int id, int pin, double price) {
        return Auth1.authorize(id, pin, price);
    }
}
