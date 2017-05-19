package pl.io4.model;

import pl.io4.model.database.Database;
import pl.io4.model.transactions.TransactionRegister;

/**
 * Created by Zax37 on 14.03.2017.
 *
 * Model łączy się z bazą danych i opisuje niezbędne dane.
 */

public class Model {
    private static Database db = new Database();
    private static TransactionRegister tr = new TransactionRegister();
    private static StringsMachine sm = new StringsMachine();

    public static Database getDatabase(){
        return db;
    }

    public static TransactionRegister getTransactionRegister(){
        return tr;
    }

    public static String getString(String string) {
        return sm.getString(string);
    }
}
