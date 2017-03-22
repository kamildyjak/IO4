package pl.io4.model;

import org.junit.Test;
import pl.io4.model.queries;
import pl.io4.model.responses;

/**
 * Created by Zax37 on 22.03.2017.
 */
public class DatabaseTest {
    
	@Test
    public void connectionTest(){
        Database db = Model.getDatabase();
        db.connect();
        assert(db.isConnect());
    }
	
	@Test
	public void testQuery(){
		Database db = Model.getDatabase();
        db.connect();
		TestResponse response = db.sendQuery(new TestQuery());
		assert(response.wasSuccessful);
	}
}
