package pl.io4.model.queries;

import pl.io4.model.Query;

/**
 * Created by Zax37 on 22.03.2017.
 *
<<<<<<< HEAD
 * Zapytanie testowe, pobieraj�ce zawarto�� bazy danych.
=======
 * Zapytanie testowe, pobierajace zawartosc bazy danych.
>>>>>>> 78d3deb62d4cc83276d1ec6440335100f6df9a3f
 */

public class TestQuery extends Query {

	private final String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES";

	public String toSQL(){
		return sql;
	}
	
}
