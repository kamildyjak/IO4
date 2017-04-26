package pl.io4.model.transactions;

import pl.io4.model.Model;
import pl.io4.model.database.responses.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa obsługująca rejestr transakcji.
 */

public class TransactionRegister {
    private List<Transaction> pending;
    private List<Transaction> added;
    private List<Transaction> failed;

    public TransactionRegister(){
        pending = new ArrayList<Transaction>();
        added = new ArrayList<Transaction>();
        failed = new ArrayList<Transaction>();
    }

    public void pushTransaction(Transaction t){
        pending.add(t);
    }

    public void tryNext(){
        if(pending.isEmpty()) return; //Nic do dodania.
        Transaction transaction = pending.remove(0);
        //TODO: cache lokalny transakcji
        Response response = new Response(false);//Model.getDatabase().sendQuery(transaction.toQuery());
        if(response.wasSuccessful) added.add(transaction);
        else failed.add(transaction);
    }
}
