package pl.io4.model;

import java.util.Date;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa abstrakcyjnie opisująca transakcje.
 */

public abstract class Transaction {
    private Date date;
    private Employee employee;

    public abstract Query toQuery();
}