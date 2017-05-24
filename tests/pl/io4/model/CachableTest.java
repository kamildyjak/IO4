package pl.io4.model;

/**
 * Created by Kuba on 2017-04-22.
 */

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
import static org.junit.Assert.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

class ModelMock implements Cachable {
	private EmployeeDBMock employees;
	private ProductsDBMock products;
	//private TransactionRegister transactions;
	//reszta baz

	public ModelMock(EmployeeDBMock e, ProductsDBMock p) {
		employees = e;
		products = p;
	}
	public ModelMock(ModelMock mm) {
		employees = new EmployeeDBMock(mm.employees);
		products = new ProductsDBMock(mm.products);
	}
	@Override
	public boolean equals(Object rhs) {
		return products.equals(((ModelMock)rhs).products) && employees.equals(((ModelMock)rhs).employees);
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
	@Override
	public String toString() {
		return employees.toString() + "\n" + products.toString();
	}
}

class ProductsDBMock implements Cachable {
	private ArrayList<Product> records;
	public void insert(Product r) {
		records.add(r);
	}
	public ProductsDBMock() {
		records = new ArrayList<Product>();
	}
	public ProductsDBMock(ProductsDBMock d) {
		records = new ArrayList<Product>(d.records);
	}
	@Override
	public boolean equals(Object o) { return o != null && o instanceof ProductsDBMock && records.equals(((ProductsDBMock) o).records); }
	@Override
	public void cache() {
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			gson.toJson(records, writer);
			writer.close();
		} catch (IOException e) {}
	}
	@Override
	public void load() {
		try {
			Reader reader = new InputStreamReader(new FileInputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			Type type = new TypeToken<ArrayList<Product>>(){}.getType();
			records = gson.fromJson(reader, type);
			reader.close();
		} catch (IOException e) {}
	}
	public String toString() {
		return records.toString();
	}
}

class EmployeeDBMock implements Cachable {
	private ArrayList<Employee> records;
	public void insert(Employee r) {
		records.add(r);
	}
	public EmployeeDBMock() {
		records = new ArrayList<Employee>();
	}
	public EmployeeDBMock(EmployeeDBMock d) {
		records = new ArrayList<Employee>(d.records);
	}
	@Override
	public boolean equals(Object o) { return o != null && o instanceof EmployeeDBMock && records.equals(((EmployeeDBMock) o).records); }
	@Override
	public void cache() {
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			gson.toJson(records, writer);
			writer.close();
		} catch (IOException e) {}
	}
	@Override
	public void load() {
		try {
			Reader reader = new InputStreamReader(new FileInputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			Type type = new TypeToken<ArrayList<Employee>>(){}.getType();
			records = gson.fromJson(reader, type);
			reader.close();
		} catch (IOException e) {}
	}
	public String toString() {
		return records.toString();
	}
}

class StringDBMock implements Cachable {
	private ArrayList<RecordString> records;
	public void insert(RecordString r) {
		records.add(r);
	}
	public StringDBMock() {
		records = new ArrayList<RecordString>();
	}
	public StringDBMock(StringDBMock d) {
		records = new ArrayList<RecordString>(d.records);
	}
	@Override
	public boolean equals(Object o) { return o != null && o instanceof StringDBMock && records.equals(((StringDBMock) o).records); }
	@Override
	public void cache() {
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			gson.toJson(records, writer);
			writer.close();
		} catch (IOException e) {}
	}
	@Override
	public void load() {
		try {
			Reader reader = new InputStreamReader(new FileInputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			Type type = new TypeToken<ArrayList<RecordString>>(){}.getType();
			records = gson.fromJson(reader, type);
			reader.close();
		} catch (IOException e) {}
	}
	public String toString() {
		return records.toString();
	}
}

class IntDBMock implements Cachable {
	private ArrayList<RecordInt> records;
	public void insert(RecordInt r) {
		records.add(r);
	}
	public IntDBMock() {
		records = new ArrayList<RecordInt>();
	}
	public IntDBMock(IntDBMock d) {
		records = new ArrayList<RecordInt>(d.records);
	}
	@Override
	public boolean equals(Object o) { return o != null && o instanceof IntDBMock && records.equals(((IntDBMock) o).records); }
	@Override
	public void cache() {
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			gson.toJson(records, writer);
			writer.close();
		} catch (IOException e) {}
	}
	@Override
	public void load() {
		try {
			Reader reader = new InputStreamReader(new FileInputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			Type type = new TypeToken<ArrayList<RecordInt>>(){}.getType();
			records = gson.fromJson(reader, type);
			reader.close();
		} catch (IOException e) {}
	}
	public String toString() {
		return records.toString();
	}
}

class FloatingDBMock implements Cachable {
	private ArrayList<RecordDouble> records;
	public void insert(RecordDouble r) {
		records.add(r);
	}
	public FloatingDBMock() {
		records = new ArrayList<RecordDouble>();
	}
	public FloatingDBMock(FloatingDBMock d) {
		records = new ArrayList<RecordDouble>(d.records);
	}
	@Override
	public boolean equals(Object o) { return o != null && o instanceof FloatingDBMock && records.equals(((FloatingDBMock) o).records); }
	@Override
	public void cache() {
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			gson.toJson(records, writer);
			writer.close();
		} catch (IOException e) {}
	}
	@Override
	public void load() {
		try {
			Reader reader = new InputStreamReader(new FileInputStream(getClass().getName() + "_cache.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			Type type = new TypeToken<ArrayList<RecordDouble>>(){}.getType();
			records = gson.fromJson(reader, type);
			reader.close();
		} catch (IOException e) {}
	}
	public String toString() {
		return records.toString();
	}
}

// dla testowych typow
class RecordInt {
	public int v1, v2, v3;
	RecordInt(int s1, int s2, int s3) {v1 = s1; v2 = s2; v3 = s3;}
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof RecordInt) {
			RecordInt rd = (RecordInt)o;
			return v1 == rd.v1 && v2 == rd.v2 && v3 == rd.v3;
		}
		return false;
	}
	@Override
	public String toString() {
		return v1 + " " + v2 + " " + v3;
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
	@Override
	public String toString() {
		return v1 + " " + v2 + " " + v3;
	}
}

class RecordString {
	public String v1, v2, v3;
	RecordString(String s1, String s2, String s3) {v1 = s1; v2 = s2; v3 = s3;}
	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof RecordString) {
			RecordString rd = (RecordString)o;
			return v1.equals(rd.v1) && v2.equals(rd.v2) && v3.equals(rd.v3);
		}
		return false;
	}
	@Override
	public String toString() {
		return v1 + " " + v2 + " " + v3;
	}
}

// wlasciwe testy
public class CachableTest {
	
	@Test
	public void IdenticalIntRecordEqualityTest() {
		RecordInt r1 = new RecordInt(1, 2, 3);
		RecordInt r2 = new RecordInt(1, 2, 3);
		assertEquals(r1, r2);
	}

	@Test
	public void IdenticalDoubleRecordEqualityTest() {
		RecordDouble r1 = new RecordDouble(1., 2., 3.);
		RecordDouble r2 = new RecordDouble(1., 2., 3.);
		assertEquals(r1, r2);
	}

	@Test
	public void IdenticalStringRecordEqualityTest() {
		RecordString r1 = new RecordString("a", "bb", "ccc");
		RecordString r2 = new RecordString("a", "bb", "ccc");
		assertEquals(r1, r2);
	}

	@Test
	public void IdenticalProductEqualityTest() {
		Product p1 = new Product("id1", "nazwa", 1.);
		Product p2 = new Product("id1", "nazwa", 1.);
		System.out.println(p1.toString());
		assertEquals(p1, p2);
	}

	@Test
	public void IdenticalEmployeeEqualityTest() {
		Employee e1 = new Employee(1, "Asdf", "Qwerty", "asdfg");
		Employee e2 = new Employee(1, "Asdf", "Qwerty", "asdfg");
		assertEquals(e1, e2);
	}

	@Test
	public void IdenticalModelMockEqualityTest() {
		EmployeeDBMock employees1 = new EmployeeDBMock();
		employees1.insert(new Employee(1, "Jan", "Kowalski", "0eba5756cd43e35ded410d5b49a870d574eb49d9"));
		EmployeeDBMock employees2 = new EmployeeDBMock();
		employees2.insert(new Employee(1, "Jan", "Kowalski", "0eba5756cd43e35ded410d5b49a870d574eb49d9"));

		ProductsDBMock products1 = new ProductsDBMock();
		products1.insert(new Product("asdf1", "czipsy Mniam", 3.45));
		ProductsDBMock products2 = new ProductsDBMock();
		products2.insert(new Product("asdf1", "czipsy Mniam", 3.45));

		ModelMock mm1 = new ModelMock(employees1, products1);
		ModelMock mm2 = new ModelMock(employees2, products2);
		assertEquals(mm1, mm2);
	}

	@Test
	public void EmptyDBTest() {
		ModelMock mm1 = new ModelMock(new EmployeeDBMock(), new ProductsDBMock());
		mm1.cache();
		mm1.load();

		ModelMock mm2 = new ModelMock(new EmployeeDBMock(), new ProductsDBMock());

		assertEquals(mm2, mm1);
	}

	@Test
	public void GeneralDBTest() {
		EmployeeDBMock employees = new EmployeeDBMock();
		employees.insert(new Employee(1, "Jan", "Kowalski", "0eba5756cd43e35ded410d5b49a870d574eb49d9"));
		employees.insert(new Employee(2, "Bashar", "Al-Assad", "3da541559918a808c2402bba5012f6c60b27661c"));
		employees.insert(new Employee(3, "Suhail", "Al-Hasan", "3da541559918a808c2402bba5012f6c60b27661c"));

		ProductsDBMock products = new ProductsDBMock();
		products.insert(new Product("asdf1", "czipsy Mniam", 3.45));
		products.insert(new Product("asdf2", "ziemniak ąęćż", 1.23));
		products.insert(new Product("asdf3", "Coca-Cola®", 0.01));

		ModelMock mm1 = new ModelMock(employees, products);
		ModelMock mm2 = new ModelMock(mm1);
		mm1.cache();
		mm1.load();

		assertEquals(mm2, mm1);
	}

	@Test
	public void IntsDBTest() {
		IntDBMock db1 = new IntDBMock();
		db1.insert(new RecordInt(1, 2, 434243242));
		db1.insert(new RecordInt(10, 0, 1));
		IntDBMock db2 = new IntDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}

	@Test
	public void NegativeIntsDBTest() {
		IntDBMock db1 = new IntDBMock();
		db1.insert(new RecordInt(-1, -2, -434243242));
		db1.insert(new RecordInt(-10, -0, 0));
		IntDBMock db2 = new IntDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}

	@Test
	public void FloatingPointDBTest() {
		FloatingDBMock db1 = new FloatingDBMock();
		db1.insert(new RecordDouble(1., 2.43214123, 43424.3242));
		db1.insert(new RecordDouble(1.70, 0., 0.0000000001));
		FloatingDBMock db2 = new FloatingDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}

	@Test
	public void EmptyStringDBTest() {
		StringDBMock db1 = new StringDBMock();
		StringDBMock db2 = new StringDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}

	@Test
	public void EmptyStringsThemselvesStringDBTest() {
		StringDBMock db1 = new StringDBMock();
		db1.insert(new RecordString("", "", ""));
		db1.insert(new RecordString("", "", ""));
		StringDBMock db2 = new StringDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}

	@Test
	public void StringDBTest() {
		StringDBMock db1 = new StringDBMock();
		db1.insert(new RecordString("asdf", "qwerty", "abcdefgh"));
		db1.insert(new RecordString("aaaa", "bbbb", "aaaa"));
		db1.insert(new RecordString("1234", "2137", "1337"));
		StringDBMock db2 = new StringDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}

	@Test
	public void StringQuotesDBTest() {
		StringDBMock db1 = new StringDBMock();
		db1.insert(new RecordString("", "\"", "\"\""));
		db1.insert(new RecordString("\"\"\"", ",\":", ":\"\","));
		db1.insert(new RecordString("", "\"", "\"\""));
		StringDBMock db2 = new StringDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}

	@Test
	public void StringNewLinesDBTest() {
		StringDBMock db1 = new StringDBMock();
		db1.insert(new RecordString("\n", "\n", "\n"));
		db1.insert(new RecordString("\n\n\n", "", "\n\n"));
		StringDBMock db2 = new StringDBMock(db1);
		db1.cache();
		db1.load();

		assertEquals(db2, db1);
	}
}