package pl.io4.model.queries;

import pl.io4.model.Query;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Zapytanie testowe, pobierajace zawartosc bazy danych.
 */

public class TestQuery extends Query {

	private final String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

	public String toSQL(){
		return sql;
	}
	
}