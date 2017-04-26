package pl.io4.model.database;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa obsługująca komunikację z bazą danych.
 */

import org.hibernate.Session;
import pl.io4.resources.HibernateUtil;
public class Database {

    Session session;
	
	public boolean isConnect(){
        if(session == null) return false;
	    return session.isConnected();
    }

    public boolean connect(){
        session = HibernateUtil.getSession();
        return session.isConnected();
    }

    public void disconnect(){
        HibernateUtil.closeSession();
    }

    /*public Response sendQuery(Query query){
		//TODO: Wysyłanie zapytań
        return new Response(false); //rezultat zapytania
    }*/

}
