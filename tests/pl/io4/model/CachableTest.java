package pl.io4.model;

/**
 * Created by Kuba on 2017-04-22.
 */

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;


// prototyp (w sensie jak to rozumiem):
interface Cachable {
	void cache();
	void load();
}

// definicja Employee potrzebna zeby nadpisac equals na potrzeby testow
class Employee {
	private final int id;
	private final String firstName;
	private final String lastName;
	private final String hashSHA1;
	private String hashMD5;

	Employee(int id, String firstName, String lastName, String hashSHA1){
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hashSHA1 = hashSHA1;
	}
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof Employee) {
			Employee e = (Employee)o;
			return id == e.id && firstName.equals(e.firstName) && lastName.equals(e.lastName)
					&& hashSHA1.equals(e.hashSHA1);
		}
		return false;
	}
}

class ModelMock implements Cachable {
	private DatabaseMock<Employee> employees;
	private DatabaseMock<Product> products;
	//private TransactionRegister transactions;
	//reszta baz

	public ModelMock(DatabaseMock<Employee> e, DatabaseMock<Product> p) {
		employees = e;
		products = p;
	}
	public ModelMock(ModelMock mm) {
		employees = new DatabaseMock<Employee>(mm.employees);
		products = new DatabaseMock<Product>(mm.products);
	}
	public boolean equals(ModelMock rhs) {
		return employees.equals(rhs.employees) && products.equals(rhs.products);
	}
	@Override
	public void cache() {
		// wywoluje cache dla wszystkich cachable
		employees.cache();
		products.cache();
		//transactions.cache();
	}
	@Override
	public void load() {
		// analogicznie
		employees.load();
		products.load();
		//transactions.load();
	}
}

// i te klasy agregujące produkty czy pracowników tez implementowalyby cachable, zapisujac do pliku
class DatabaseMock<T> implements Cachable {
	private ArrayList<T> records;
	public void insert(T r) {
		records.add(r);
	}
	public DatabaseMock() {
		records = new ArrayList<T>();
	}
	public DatabaseMock(DatabaseMock<T> d) {
		records = new ArrayList<T>(d.records);
	}
	@Override
	public boolean equals(Object o) {
		//return records.equals(((DatabaseMock<T>)o).records); ?
		if (o != null && o instanceof DatabaseMock) {
			DatabaseMock<T> rhs = (DatabaseMock<T>)o;
			if (records.size() == rhs.records.size()) {
				Iterator<T> it1 = records.iterator(), it2 = rhs.records.iterator();
				while (it1.hasNext()) {
					T rec1 = it1.next();
					T rec2 = it2.next();
					if (!rec1.equals(rec2))
						return false;
				}
				return true;
			}
		}
		return false;
	}
	@Override
	public void cache() {

	}
	@Override
	public void load() {
		records = new ArrayList<T>(); // so that it fails
	}
}

// dla testowych typow
class RecordInt {
	public int v1, v2, v3;
	RecordInt(int s1, int s2, int s3) {v1 = s1; v2 = s2; v3 = s3;}
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof RecordDouble) {
			RecordDouble rd = (RecordDouble)o;
			return v1 == rd.v1 && v2 == rd.v2 && v3 == rd.v3;
		}
		return false;
	}
}

class RecordDouble {
	public double v1, v2, v3;
	RecordDouble(double s1, double s2, double s3) {v1 = s1; v2 = s2; v3 = s3;}
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof RecordDouble) {
			RecordDouble rd = (RecordDouble)o;
			return v1 == rd.v1 && v2 == rd.v2 && v3 == rd.v3;
		}
		return false;
	}
}

class RecordString {
	public String v1, v2, v3;
	RecordString(String s1, String s2, String s3) {v1 = s1; v2 = s2; v3 = s3;}
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof RecordDouble) {
			RecordDouble rd = (RecordDouble)o;
			return v1.equals(rd.v1) && v2.equals(rd.v2) && v3.equals(rd.v3);
		}
		return false;
	}
}

// wlasciwe testy
public class CachableTest {

	@Test
	public void EmptyDBTest() {
		ModelMock mm1 = new ModelMock(new DatabaseMock<Employee>(), new DatabaseMock<Product>());
		mm1.cache();
		mm1.load();

		ModelMock mm2 = new ModelMock(new DatabaseMock<Employee>(), new DatabaseMock<Product>());

		assertEquals(true, mm1.equals(mm2));
	}

	@Test
	public void GeneralDBTest() {
		DatabaseMock<Employee> employees = new DatabaseMock<Employee>();
		employees.insert(new Employee(1, "Jan", "Kowalski", "0eba5756cd43e35ded410d5b49a870d574eb49d9"));
		employees.insert(new Employee(1, "Bashar", "Al-Assad", "3da541559918a808c2402bba5012f6c60b27661c"));
		employees.insert(new Employee(1, "Suhail", "Al-Hasan", "3da541559918a808c2402bba5012f6c60b27661c"));

		DatabaseMock<Product> products = new DatabaseMock<Product>();
		products.insert(new Product("asdf1", "czipsy Mniam", 3.45));
		products.insert(new Product("asdf2", "ziemniak ąęćż", 1.23));
		products.insert(new Product("asdf3", "Coca-Cola®", 0.01));

		ModelMock mm1 = new ModelMock(employees, products);
		ModelMock mm2 = new ModelMock(mm1);
		mm1.cache();
		mm1.load();

		assertEquals(true, mm1.equals(mm2));
	}

	@Test
	public void IntsDBTest() {
		DatabaseMock<RecordInt> db1 = new DatabaseMock<RecordInt>();
		db1.insert(new RecordInt(1, 2, 434243242));
		db1.insert(new RecordInt(10, 0, 1));
		DatabaseMock<RecordInt> db2 = new DatabaseMock<RecordInt>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}

	@Test
	public void NegativeIntsDBTest() {
		DatabaseMock<RecordInt> db1 = new DatabaseMock<RecordInt>();
		db1.insert(new RecordInt(-1, -2, -434243242));
		db1.insert(new RecordInt(-10, -0, 0));
		DatabaseMock<RecordInt> db2 = new DatabaseMock<RecordInt>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}

	@Test
	public void FloatingPointDBTest() {
		DatabaseMock<RecordDouble> db1 = new DatabaseMock<RecordDouble>();
		db1.insert(new RecordDouble(1., 2.43214123, 43424.3242));
		db1.insert(new RecordDouble(1.70, 0., 0.0000000001));
		DatabaseMock<RecordDouble> db2 = new DatabaseMock<RecordDouble>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}

	@Test
	public void EmptyStringDBTest() {
		DatabaseMock<RecordString> db1 = new DatabaseMock<RecordString>();
		DatabaseMock<RecordString> db2 = new DatabaseMock<RecordString>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}

	@Test
	public void EmptyStringsThemselvesStringDBTest() {
		DatabaseMock<RecordString> db1 = new DatabaseMock<RecordString>();
		db1.insert(new RecordString("", "", ""));
		db1.insert(new RecordString("", "", ""));
		DatabaseMock<RecordString> db2 = new DatabaseMock<RecordString>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}

	@Test
	public void StringDBTest() {
		DatabaseMock<RecordString> db1 = new DatabaseMock<RecordString>();
		db1.insert(new RecordString("asdf", "qwerty", "abcdefgh"));
		db1.insert(new RecordString("aaaa", "bbbb", "aaaa"));
		db1.insert(new RecordString("1234", "2137", "1337"));
		DatabaseMock<RecordString> db2 = new DatabaseMock<RecordString>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}

	@Test
	public void StringQuotesDBTest() {
		DatabaseMock<RecordString> db1 = new DatabaseMock<RecordString>();
		db1.insert(new RecordString("", "\"", "\"\""));
		db1.insert(new RecordString("\"\"\"", ",\":", ":\"\","));
		db1.insert(new RecordString("", "\"", "\"\""));
		DatabaseMock<RecordString> db2 = new DatabaseMock<RecordString>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}

	@Test
	public void StringNewLinesDBTest() {
		DatabaseMock<RecordString> db1 = new DatabaseMock<RecordString>();
		db1.insert(new RecordString("\n", "\n", "\n"));
		db1.insert(new RecordString("\n\n\n", "", "\n\n"));
		DatabaseMock<RecordString> db2 = new DatabaseMock<RecordString>(db1);
		db1.cache();
		db1.load();

		assertEquals(true, db1.equals(db2));
	}
}