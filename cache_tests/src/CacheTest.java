import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//import org.junit.runner.JUnitCore;
//import org.junit.runner.Result;
//import org.junit.runner.notification.Failure;

/**
 * Created by Kuba on 2017-04-18.
 */

interface CachableRecord {
	int getLength();
	String toString();
	void fromString(String s);
}


interface Cachable {
	void cache();
	boolean loadFromCache() throws IOException;
	String getFilename() throws IOException;
}

class Record implements CachableRecord {
	public int value1;
	public String value2;
	public double value3;

	public Record(int v1, String v2, double v3) {
		value1 = v1;
		value2 = v2;
		value3 = v3;
	}
	public boolean equals(Record rhs) {
		return value1 == rhs.value1 && value2 == rhs.value2 && value3 == rhs.value3;
	}

	@Override
	public int getLength() {
		return 3;
	}
	@Override
	public String toString() {
		return "\"" + Integer.toString(value1) + "\",\"" + value2 + "\",\"" + Double.toString(value3) + "\"";
	}
	@Override
	public void fromString(String s) {
		String[] values = s.split(",");
		value1 = Integer.valueOf(values[0]);
		value2 = values[1];
		value3 = Integer.valueOf(values[2]);
	}
}

class RecordString implements CachableRecord {
	public String value1, value2, value3;

	public RecordString(String v1, String v2, String v3) {
		value1 = v1;
		value2 = v2;
		value3 = v3;
	}
	public boolean equals(Record rhs) {
		return value1.equals(rhs.value1) && value2.equals(rhs.value2) && value3.equals(rhs.value3);
	}

	@Override
	public int getLength() {
		return 3;
	}
	@Override
	public String toString() {
		return "\"" + value1 + "\",\"" + value2 + "\",\"" + value3 + "\"";
	}
	@Override
	public void fromString(String s) {
		String[] values = s.split(",");
		value1 = values[0];
		value2 = values[1];
		value3 = values[2];
	}
}

class Database<T> implements Cachable {
	public ArrayList<T> records;
	public void insert(T r) {
		records.add(r);
	}
	public Database() {
		records = new ArrayList<T>();
	}
	public Database(Database d) {
		records = new ArrayList<T>(d.records);
	}
	public boolean equals(Database rhs) {
		if (records.size() == rhs.records.size()) {
			Iterator<T> it1 = records.iterator(), it2 = rhs.records.iterator();
			while (it1.hasNext()) {
				T rec1 = it1.next();
				T rec2 = it2.next();
				if (!rec1.equals(rec2))
					return false;
			}
		}
		else
			return false;
		return true;
	}
	@Override
	public void cache() {

	}
	@Override
	public boolean loadFromCache() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(getFilename()));
		try {
			records = new ArrayList<T>();
			//T rec = new T();
			String line = br.readLine();
			while (line != null) {

				line = br.readLine();
			}
		} finally {
			br.close();
		}
		return true;
	}
	@Override
	public String getFilename() {
		return "TestDatabase_cache.csv";
	}
}

public class CacheTest {

	public static void main(String[] args) {
		TestPustaBazaDanych();
		TestZPrzecinkami();
		TestZCudzyslowami();
		TestNormalnyRandomowyPrzyklad();
		TestCacheCorrupted();
		TestCachePuste();
		TestKrotkaZaDuza();
		TestKrotkaZaMala();
		TestZlyTypWartosciWKrotce();
	}

	static boolean TestPustaBazaDanych() {
		Database<Record> d1 = new Database();

		Database<Record> d2 = new Database(d1);

		d1.cache();
		d1.loadFromCache();

		return d1.equals(d2);
	}
	static boolean TestZPrzecinkami() {
		Database<RecordString> d1 = new Database();

		d1.insert(new RecordString(",", ",,", ",\","));

		Database<RecordString> d2 = new Database(d1);

		d1.cache();
		d1.loadFromCache();

		return d1.equals(d2);
	}
	static boolean TestZCudzyslowami() {
		Database<RecordString> d1 = new Database();

		d1.insert(new RecordString("\"", "\"\"", "\""));

		Database<RecordString> d2 = new Database(d1);

		d1.cache();
		d1.loadFromCache();

		return d1.equals(d2);
	}
	static boolean TestNormalnyRandomowyPrzyklad() {
		Database<Record> d1 = new Database();

		d1.insert(new Record(1, "asdf", 1.));
		d1.insert(new Record(-1, "abcd", -1.));
		d1.insert(new Record(0, "zxcv", 0));

		Database<Record> d2 = new Database(d1);

		d1.cache();
		d1.loadFromCache();

		return d1.equals(d2);
	}
	static boolean TestCacheCorrupted() {
		return false;
	}
	static boolean TestCachePuste() {
		return false;
	}
	static boolean TestKrotkaZaDuza() {
		return false;
	}
	static boolean TestKrotkaZaMala() {
		return false;
	}
	static boolean TestZlyTypWartosciWKrotce() {
		return false;
	}
}
