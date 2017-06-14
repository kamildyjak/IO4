package pl.io4.model;


import org.junit.Test;
import pl.io4.model.database.Database;
import pl.io4.model.database.queries.TestQuery;
import pl.io4.model.entities.Employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kamil on 05.06.2017.
 */
public class SynchronisationTest {

    static final String sha1 = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";
    static final String md5 = "5f4dcc3b5aa765d61d8327deb882cf99";
    Database db = Model.getDatabase();

    static final Employee testEmployee = new Employee(
            "12345678910", "Andrzej", "Testowy",
            "test@gmail.com", sha1, md5 );


    static final Employee dbEmployee = new Employee(
            "12345678910", "Andrzej", "Testowy",
            "test@gmail.com", sha1, md5 );


    @Test
    public void connectionTest() throws Exception {
        db.start();
        db.sendQuery(new TestQuery(), new Database.Callback(){
            public void run(){
                assertTrue(db.isConnected());
                db.end();
            }
        });
        db.join();
    }

    @Test
    public void changeTest(){
        testEmployee.setFirstName("Zbyszek");
        assertEquals(testEmployee, dbEmployee);
    }

}
