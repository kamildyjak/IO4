package pl.io4.model;

import java.util.Date;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa abstrakcyjnie opisująca transakcje.
 */

public abstract class Transaction {
    protected Date date;
    protected Employee employee;

    public abstract Query toQuery();
}