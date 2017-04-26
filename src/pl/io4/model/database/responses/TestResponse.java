package pl.io4.model.database.responses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Odpowiedz testowa, zawiera opis bazy danych.
 */

public class TestResponse extends Response {

	private List<String> names;
	
	public TestResponse(boolean status){
		super(status);
		names = new ArrayList<String>();
	}
	
}
