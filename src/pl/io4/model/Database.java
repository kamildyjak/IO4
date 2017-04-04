package pl.io4.model;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa obsługująca komunikację z bazą danych.
 */

import org.hibernate.Session;
<<<<<<< HEAD
=======
import org.hibernate.SessionEventListener;
import org.hibernate.event.spi.LoadEventListener;
>>>>>>> 78d3deb62d4cc83276d1ec6440335100f6df9a3f
import pl.io4.model.Query;
import pl.io4.model.Response;
import pl.io4.resources.hibernateUtil;
public class Database {

    Session session;
	
	public boolean isConnect(){
        if(session == null) return false;
	    return session.isConnected();
    }

    public boolean connect(){
<<<<<<< HEAD
        final Session session = hibernateUtil.getSession();
        connected = session.isConnected();
        //TODO: Połączenie z bazą danych
        return connected;
    }

=======
        session = hibernateUtil.getSession();
        return session.isConnected();
    }

    public void disconnect(){
        hibernateUtil.closeSession();
    }
>>>>>>> 78d3deb62d4cc83276d1ec6440335100f6df9a3f

    public Response sendQuery(Query query){
		//TODO: Wysyłanie zapytań
        return new Response(false); //rezultat zapytania
    }

}
