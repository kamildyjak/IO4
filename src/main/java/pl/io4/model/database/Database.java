package pl.io4.model.database;

import javafx.util.Pair;
import org.hibernate.Session;
import pl.io4.model.database.queries.Query;
import pl.io4.model.database.responses.Response;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
* Created by Zax37 on 22.03.2017.
* Klasa obsługująca komunikację z bazą danych.
* Dzięki rozszerzeniu klasy Thread istnieje możliwość
* równoczesnego przetwarzania zapytań i wyświetlania interfejsu.
*/
public final class Database extends Thread {
    /*
    * Prosta implementacja callbacku pozwalająca użytkownikowi, zależnie od potrzeb,
    * sprawdzić i zareagować na różne efekty wykonanego do bazy danych zapytania.
    */
    public abstract static class Callback implements Runnable {
        private Response res;

        final void execute(Response res) {
            this.res = res;
            run();
        }
    }

    private Session session;

    private ConcurrentLinkedQueue<Pair<Query, Callback>> pending;
    private ConcurrentLinkedQueue<Query> successful;
    private ConcurrentLinkedQueue<Pair<Query, Callback>> failed;

    private boolean closing = false;
    private Runnable onConnect, onFailure, onEnd;

    public Database() {
        pending = new ConcurrentLinkedQueue<Pair<Query, Callback>>();
        successful = new ConcurrentLinkedQueue<Query>();
        failed = new ConcurrentLinkedQueue<Pair<Query, Callback>>();
    }

    public boolean isConnected() {
        return session != null && session.isConnected();
    }

    public void start(Runnable onConnect, Runnable onFailure) {
        this.onConnect = onConnect;
        this.onFailure = onFailure;
        start();
    }

    public void run() {
        try {
            session = HibernateUtil.getSession();
        } catch (Throwable e) {
            closing = true;
        }

        if (session != null && session.isConnected()) {
            if (onConnect != null) {
                onConnect.run();
            }
        } else {
            if (onFailure != null) {
                onFailure.run();
            }
        }

        while (!closing) {
            while (!pending.isEmpty()) {
                Pair<Query, Callback> pair = pending.poll();
                Response res = pair.getKey().execute(session);
                if (res.wasSuccessful()) {
                    successful.add(pair.getKey());
                } else {
                    failed.add(pair);
                }
                pair.getValue().execute(res);
            }
        }
        if (session != null) {
            HibernateUtil.closeSession();
        }
        if (onEnd != null) {
            onEnd.run();
        }
    }

    public void sendQuery(Query query, Callback callback) {
        pending.add(new Pair<Query, Callback>(query, callback));
    }

    public void end(Runnable onEnd) {
        closing = true;
        this.onEnd = onEnd;
    }

    public void end() {
        closing = true;
    }

}
