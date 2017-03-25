package pl.io4.model;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa opisująca odpowiedź z bazy danych.
 * Powinna zostać rozszerzona na konkretne rodzaje odpowiedzi, jakich aplikacja może oczekiwać.
 */
 
public class Response {
	
	public final boolean wasSuccessful;
	
	protected Response(boolean status){
		wasSuccessful = status;
	}
	
}
