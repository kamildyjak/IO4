package pl.io4.model.database;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa obsługująca komunikację z bazą danych.
 */

import org.hibernate.Session;
import pl.io4.model.database.queries.Query;
import pl.io4.model.database.responses.Response;
import pl.io4.resources.hibernateUtil;
public class Database {

    Session session;
	
	public boolean isConnect(){
        if(session == null) return false;
	    return session.isConnected();
    }

    public boolean connect(){
        session = hibernateUtil.getSession();
        return session.isConnected();
    }

    public void disconnect(){
        hibernateUtil.closeSession();
    }

    /*public Response sendQuery(Query query){
		//TODO: Wysyłanie zapytań
        return new Response(false); //rezultat zapytania
    }*/

}
