package pl.io4.model;

import org.junit.Test;
import pl.io4.model.entities.PracownikEntity;
import pl.io4.model.queries.TestQuery;
import pl.io4.resources.Storage;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

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
		Response response = db.sendQuery(new TestQuery());
		assert(response.wasSuccessful);
	}

	@Test
	public void testWrite() {
		// Just a write, verify id set
		PracownikEntity user = new PracownikEntity();
		Storage storage = new Storage(user);
		storage.beginTransaction();
		user.setNazwisko("a");
		user.setImie("b");
		user.setHashMd5("kkk");
		user.setHashSha1("abc");
		storage.insert(user);
		assertNotNull(user.getId());
		int id = user.getId();
		storage.commit();

		PracownikEntity user2  = new PracownikEntity();
		storage = new Storage(user2);
		storage.beginTransaction();
		user2 = (PracownikEntity) storage.getById(id);
		assertEquals(user2.getNazwisko(), "a");
		storage.commit();
	}

	@Test
	public void testUpdate() {

		String surname = "Al";
		String newSurname = "Alan";

		// Insert an actor
		PracownikEntity user = new PracownikEntity();
		Storage storage = new Storage(user);
		storage.beginTransaction();
		user.setNazwisko(surname);
		user.setImie("bb");
		user.setHashMd5("kkkk");
		user.setHashSha1("abcd");
		storage.insert(user);
		int id = user.getId();
		storage.commit();

		PracownikEntity user2  = new PracownikEntity();
		storage = new Storage(user2);
		storage.beginTransaction();
		user2 = (PracownikEntity) storage.getById(id);
		assertEquals(user2.getNazwisko(), "Al");

		// Update it
		user2.setNazwisko(newSurname);
		storage.update(user2);
		storage.commit();

		// Read it again and verify update
		storage.beginTransaction();
		PracownikEntity user3  = (PracownikEntity) storage.getById(id);
		assertEquals(user3.getNazwisko(), newSurname);
		storage.commit();
	}

	@Test
	public void testDelete() {

		PracownikEntity user = new PracownikEntity();
		Storage storage = new Storage(user);

		// Write
		storage.beginTransaction();
		user.setNazwisko("aaa");
		user.setImie("bbb");
		user.setHashMd5("kkkkk");
		user.setHashSha1("abcde");
		storage.insert(user);
		int id = user.getId();
		storage.commit();

		// Delete it now
		storage.beginTransaction();
		storage.delete(user);
		storage.commit();

		// Now we can't read it back, as expected
		storage.beginTransaction();
		PracownikEntity actor2 = (PracownikEntity) storage.getById(id);
		assertNull(actor2);
		storage.commit();
	}
}

