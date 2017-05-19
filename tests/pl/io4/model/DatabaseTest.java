package pl.io4.model;

import org.junit.Test;
import pl.io4.model.database.Database;
import pl.io4.model.database.queries.TestQuery;
import pl.io4.model.database.responses.Response;

/**
 * Created by Zax37 on 22.03.2017.
 */
public class DatabaseTest {
    
	@Test
    public void connectionTest() throws Exception {
        Database db = Model.getDatabase();
        db.start();
		db.sendQuery(new TestQuery(), new Database.Callback(){
			public void run(){
				assert(db.isConnected());
				db.end();
			}
		});
        db.join();
    }
}
