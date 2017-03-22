package pl.io4.model;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa obsługująca komunikację z bazą danych.
 */

import pl.io4.model.Query;
import pl.io4.model.Response;
 
public class Database {

	private boolean connected = false;
	private final String host = "mssql5.gear.host";
	private final String login = "io4";
	private final String password = "Ry1Rn9DZ~?s1";
	
	public boolean isConnect(){
        return connected;
    }
	
    public boolean connect(){
		//TODO: Połączenie z bazą danych
        return connected;
    }
	
    public Response sendQuery(Query query){
		//TODO: Wysyłanie zapytań
        return new Response(false); //rezultat zapytania
    }
	
}
