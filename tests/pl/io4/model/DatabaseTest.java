package pl.io4.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import pl.io4.model.database.Database;
import pl.io4.model.database.entities.EmployeeEntity;
import pl.io4.model.database.responses.Response;
import pl.io4.model.database.HibernateUtil;

/**
 * Created by Zax37 on 22.03.2017.
 */
public class DatabaseTest {

	@Test
	public void connectionTest() {
		Database db = Model.getDatabase();
		db.connect();
		assert (db.isConnect());
	}

	@Test
	public void testQuery() {
		Database db = Model.getDatabase();
		db.connect();
		Response response = new Response(false);//db.sendQuery(new TestQuery());
		assert (response.wasSuccessful);
	}

	@Test
	public void testAddEmployee() {
		Transaction transactionAdd = null;
		Transaction transactionGet = null;
		try (Session session = HibernateUtil.getSession();) {
			transactionAdd = session.beginTransaction();
			EmployeeEntity employeeEntity = new EmployeeEntity("11111111111", "a", "b", "a@b", "hs", "hmd");
			session.save(employeeEntity);
			transactionAdd.commit();
			transactionGet = session.beginTransaction();
			org.hibernate.query.Query query = session.createQuery("FROM EmployeeEntity");
			EmployeeEntity e = (EmployeeEntity) query.list().get(query.list().size() - 1);
			String pesel = e.getPesel();
			//System.out.println(pesel + "koniec");
			transactionGet.commit();
			assert (pesel.equals("11111111111"));
		} catch (HibernateException e) {
			if (transactionAdd != null) transactionAdd.rollback();
			if (transactionGet != null) transactionGet.rollback();
			e.printStackTrace();
		}

	}

	@Test
	public void testDeleteEmployee() {
		Transaction transactionDelete = null;
		try (Session session = HibernateUtil.getSession();) {
			transactionDelete = session.beginTransaction();
			org.hibernate.query.Query query = session.createQuery("FROM EmployeeEntity");
			int size1 = query.list().size();
			EmployeeEntity employee = (EmployeeEntity) session.get(EmployeeEntity.class, "11111111111");
			session.delete(employee);
			org.hibernate.query.Query query2 = session.createQuery("FROM EmployeeEntity");
			int size2 = query2.list().size();
			transactionDelete.commit();
			assert (size1 == size2 + 1);
		} catch (HibernateException e) {
			if (transactionDelete != null) transactionDelete.rollback();
			e.printStackTrace();
		}
	}
}
