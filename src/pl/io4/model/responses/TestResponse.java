package pl.io4.model.responses;

import pl.io4.model.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Odpowied� testowa, zawiera opis bazy danych.
 */
  
public class TestResponse extends Response {

	private List<String> names;
	
	public TestResponse(boolean status){
		super(status);
		names = new ArrayList<String>();
	}
	
}
