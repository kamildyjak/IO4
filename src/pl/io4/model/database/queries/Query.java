package pl.io4.model.database.queries;

import org.hibernate.Session;
import pl.io4.model.database.responses.Response;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Klasa abstrakcyjnie opisująca zapytania do bazy danych.
 * Powinna zostać rozszerzona na konkretne rodzaje zapytań,
 * które aplikacja może wykonać.
 */
 
public abstract class Query {
	public abstract Response execute(Session session);
}
