package pl.io4.model.database.queries;

import org.hibernate.Session;
import pl.io4.model.database.responses.Response;
import pl.io4.model.database.responses.TestResponse;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Zapytanie testowe, pobierajace zawartosc bazy danych.
 */

public class TestQuery extends Query {
	private final String sql = "FROM ShopEntity";

	public Response execute(Session session){
		return new TestResponse(session.createQuery(sql).getResultList());
	}
	
}
