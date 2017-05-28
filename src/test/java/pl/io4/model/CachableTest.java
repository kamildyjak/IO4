package pl.io4.model;

import org.json.JSONObject;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import pl.io4.model.cachable.CachableArrayList;
import pl.io4.model.cachable.CachableList;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Employee;
import pl.io4.model.machines.EmployeesMachine;

/**
 * Created by Zax37 on 20.05.2017.
 */
public class CachableTest {
    class SimpleTypesTestContainer extends CachableObject {
        public int integerValue;
        public double doubleValue;
        public boolean boolValue;
        public String string;
        public SimpleTypesTestContainer() {}
        public SimpleTypesTestContainer(int i, double d, boolean b, String s) {
            integerValue = i;
            doubleValue = d;
            boolValue = b;
            string = s;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof SimpleTypesTestContainer) {
                SimpleTypesTestContainer sttc = (SimpleTypesTestContainer)o;
                return integerValue == sttc.integerValue
                        && doubleValue  == sttc.doubleValue
                        && boolValue == sttc.boolValue
                        && string.equals(sttc.string);
            }
            return false;
        }
        @Override
        public JSONObject cache() {
            JSONObject ret = new JSONObject();
            ret.put("1", integerValue);
            ret.put("2", doubleValue);
            ret.put("3", boolValue);
            ret.put("4", string);
            return ret;
        }

        @Override
        public void load(JSONObject data) {
            integerValue = data.getInt("1");
            doubleValue = data.getDouble("2");
            boolValue = data.getBoolean("3");
            string = data.getString("4");
        }
    }

    class IntegerTypeTestContainer extends CachableObject {
        public int v1, v2, v3;
        IntegerTypeTestContainer(int s1, int s2, int s3) {
            v1 = s1;
            v2 = s2;
            v3 = s3;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof IntegerTypeTestContainer) {
                IntegerTypeTestContainer rd = (IntegerTypeTestContainer) o;
                return v1 == rd.v1 && v2 == rd.v2 && v3 == rd.v3;
            }
            return false;
        }

        @Override
        public JSONObject cache() {
            return null;
        }

        @Override
        public void load(JSONObject data) {

        }
    }

    class FloatingTypeTestContainer extends CachableObject {
        public double v1, v2, v3;
        FloatingTypeTestContainer(double s1, double s2, double s3) {
            v1 = s1;
            v2 = s2;
            v3 = s3;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof FloatingTypeTestContainer) {
                FloatingTypeTestContainer rd = (FloatingTypeTestContainer)o;
                return v1 == rd.v1 && v2 == rd.v2 && v3 == rd.v3;
            }
            return false;
        }

        @Override
        public JSONObject cache() {
            return null;
        }

        @Override
        public void load(JSONObject data) {

        }
    }

    class StringTestContainer extends CachableObject {
        public String v1, v2, v3;
        StringTestContainer(String s1, String s2, String s3) {
            v1 = s1;
            v2 = s2;
            v3 = s3;
        }

        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof StringTestContainer) {
                StringTestContainer rd = (StringTestContainer) o;
                return v1.equals(rd.v1) && v2.equals(rd.v2) && v3.equals(rd.v3);
            }
            return false;
        }

        @Override
        public JSONObject cache() {
            return null;
        }

        @Override
        public void load(JSONObject data) {

        }
    }

    class TestContainerDatabase extends CachableObject {
        private SimpleTypesTestContainer sttc;
        private IntegerTypeTestContainer ittc;
        private FloatingTypeTestContainer fttc;
        private StringTestContainer stc;
        public TestContainerDatabase(SimpleTypesTestContainer sttc_,
                                     IntegerTypeTestContainer ittc_,
                                     FloatingTypeTestContainer fttc_,
                                     StringTestContainer stc_) {
            sttc = sttc_;
            ittc = ittc_;
            fttc = fttc_;
            stc = stc_;
        }
        @Override
        public boolean equals(Object o) {
            if (o != null && o instanceof TestContainerDatabase) {
                TestContainerDatabase tcd = (TestContainerDatabase)o;
                return sttc.equals(tcd.sttc)
                        && ittc.equals(tcd.ittc)
                        && fttc.equals(tcd.fttc)
                        && stc.equals(tcd.stc);
            }
            return false;
        }

        @Override
        public JSONObject cache() {
            return null;
        }

        @Override
        public void load(JSONObject data) {

        }
    }

    class IntegerTestContainerDatabase extends CachableObject {
        private CachableList<IntegerTypeTestContainer> db = new CachableArrayList<>(IntegerTypeTestContainer.class);
        public IntegerTestContainerDatabase() {}
        public void insert(IntegerTypeTestContainer record) {
            db.add(record);
        }

        @Override
        public boolean equals(Object o) {
            return o != null
                    && o instanceof IntegerTestContainerDatabase
                    && db.equals(((IntegerTestContainerDatabase) o).db);
        }

        @Override
        public JSONObject cache() {
            return null;
        }

        @Override
        public void load(JSONObject data) {

        }
    }

    class FloatingTestContainerDatabase extends CachableObject {
        private CachableList<FloatingTypeTestContainer> db = new CachableArrayList<>(FloatingTypeTestContainer.class);
        public FloatingTestContainerDatabase() {}
        public void insert(FloatingTypeTestContainer record) {
            db.add(record);
        }

        @Override
        public boolean equals(Object o) {
            return o != null
                    && o instanceof FloatingTestContainerDatabase
                    && db.equals(((FloatingTestContainerDatabase) o).db);
        }

        @Override
        public JSONObject cache() {
            return null;
        }

        @Override
        public void load(JSONObject data) {

        }
    }

    class StringTestContainerDatabase extends CachableObject {
        private CachableList<StringTestContainer> db = new CachableArrayList<>(StringTestContainer.class);
        public StringTestContainerDatabase() {}
        public void insert(StringTestContainer record) {
            db.add(record);
        }

        @Override
        public boolean equals(Object o) {
            return o != null
                    && o instanceof StringTestContainerDatabase
                    && db.equals(((StringTestContainerDatabase) o).db);
        }

        @Override
        public JSONObject cache() {
            return null;
        }

        @Override
        public void load(JSONObject data) {

        }
    }

    // Testing equals methods
    @Test
    public void IdenticalIntRecordEqualityTest() {
        IntegerTypeTestContainer r1 = new IntegerTypeTestContainer(1, 2, 3);
        IntegerTypeTestContainer r2 = new IntegerTypeTestContainer(1, 2, 3);

        assertEquals(r1, r2);
    }

    @Test
    public void IdenticalDoubleRecordEqualityTest() {
        FloatingTypeTestContainer r1 = new FloatingTypeTestContainer(1., 2., 3.);
        FloatingTypeTestContainer r2 = new FloatingTypeTestContainer(1., 2., 3.);

        assertEquals(r1, r2);
    }

    @Test
    public void IdenticalStringRecordEqualityTest() {
        StringTestContainer r1 = new StringTestContainer("a", "bb", "ccc");
        StringTestContainer r2 = new StringTestContainer("a", "bb", "ccc");

        assertEquals(r1, r2);
    }

    @Test
    public void IdenticalTestContainerDatabaseTest() {
        SimpleTypesTestContainer sttc1 = new SimpleTypesTestContainer(1, .256, true, "String");
        SimpleTypesTestContainer sttc2 = new SimpleTypesTestContainer(1, .256, true, "String");
        IntegerTypeTestContainer ittc1 = new IntegerTypeTestContainer(1, 2, 3);
        IntegerTypeTestContainer ittc2 = new IntegerTypeTestContainer(1, 2, 3);
        FloatingTypeTestContainer fttc1 = new FloatingTypeTestContainer(1., 2., 3.);
        FloatingTypeTestContainer fttc2 = new FloatingTypeTestContainer(1., 2., 3.);
        StringTestContainer stc1 = new StringTestContainer("a", "bb", "ccc");
        StringTestContainer stc2 = new StringTestContainer("a", "bb", "ccc");
        TestContainerDatabase db1 = new TestContainerDatabase(sttc1, ittc1, fttc1, stc1);
        TestContainerDatabase db2 = new TestContainerDatabase(sttc2, ittc2, fttc2, stc2);

        assertEquals(db1, db2);
    }

    @Test
    public void IdenticalIntegerTestContainerDatabaseTest() {
        IntegerTestContainerDatabase db1 = new IntegerTestContainerDatabase(),
                db2 = new IntegerTestContainerDatabase();
        db1.insert(new IntegerTypeTestContainer(1, 2, 3));
        db1.insert(new IntegerTypeTestContainer(0, -2, -1));
        db2.insert(new IntegerTypeTestContainer(1, 2, 3));
        db2.insert(new IntegerTypeTestContainer(0, -2, -1));

        assertEquals(db1, db2);
    }

    @Test
    public void IdenticalFloatingTestContainerDatabaseTest() {
        FloatingTestContainerDatabase db1 = new FloatingTestContainerDatabase(),
                db2 = new FloatingTestContainerDatabase();
        db1.insert(new FloatingTypeTestContainer(1., 2., 3.));
        db1.insert(new FloatingTypeTestContainer(0.999998, -2., -1.));
        db2.insert(new FloatingTypeTestContainer(1., 2., 3.));
        db2.insert(new FloatingTypeTestContainer(0.999998, -2., -1.));

        assertEquals(db1, db2);
    }

    @Test
    public void IdenticalStringTestContainerDatabaseTest() {
        StringTestContainerDatabase db1 = new StringTestContainerDatabase(),
                db2 = new StringTestContainerDatabase();
        db1.insert(new StringTestContainer("a", "bb", "ccc"));
        db1.insert(new StringTestContainer("ASDF", "", "-101"));
        db2.insert(new StringTestContainer("a", "bb", "ccc"));
        db2.insert(new StringTestContainer("ASDF", "", "-101"));

        assertEquals(db1, db2);
    }

    @Test
    public void simpleTypesTest() {
        SimpleTypesTestContainer original = new SimpleTypesTestContainer(),
                fromCache = new SimpleTypesTestContainer();

        original.integerValue = 1;
        original.doubleValue = .256f;
        original.boolValue = true;
        original.string = "String";

        JSONObject cache = original.cache();
        fromCache.load(cache);

        assertEquals(original.integerValue, fromCache.integerValue);
        assertEquals(original.doubleValue, fromCache.doubleValue, 0);
        assertEquals(original.boolValue, fromCache.boolValue);
        assertEquals(original.string, fromCache.string);
    }

    @Test
    public void GeneralIntegerTestContainerDatabaseTest() {
        IntegerTestContainerDatabase db1 = new IntegerTestContainerDatabase();
        db1.insert(new IntegerTypeTestContainer(1, 2, 434243242));
        db1.insert(new IntegerTypeTestContainer(10, 0, 1));
        IntegerTestContainerDatabase db2 = new IntegerTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void NegativeIntegerTestContainerDatabaseTest() {
        IntegerTestContainerDatabase db1 = new IntegerTestContainerDatabase();
        db1.insert(new IntegerTypeTestContainer(-1, -2, -434243242));
        db1.insert(new IntegerTypeTestContainer(-10, -0, 0));
        IntegerTestContainerDatabase db2 = new IntegerTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void EmptyFloatingTestContainerDatabaseTest() {
        FloatingTestContainerDatabase db1 = new FloatingTestContainerDatabase(),
                db2 = new FloatingTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void GeneralFloatingTestContainerDatabaseTest() {
        FloatingTestContainerDatabase db1 = new FloatingTestContainerDatabase();
        db1.insert(new FloatingTypeTestContainer(1., 2.43214123, 43424.3242));
        db1.insert(new FloatingTypeTestContainer(1.70, 0., 0.0000000001));
        FloatingTestContainerDatabase db2 = new FloatingTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void EmptyStringTestContainerDatabaseTest() {
        StringTestContainerDatabase db1 = new StringTestContainerDatabase();
        StringTestContainerDatabase db2 = new StringTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void EmptyStringsThemselvesStringTestContainerDatabaseTest() {
        StringTestContainerDatabase db1 = new StringTestContainerDatabase();
        db1.insert(new StringTestContainer("", "", ""));
        db1.insert(new StringTestContainer("", "", ""));
        StringTestContainerDatabase db2 = new StringTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void QuotesStringTestContainerDatabaseTest() {
        StringTestContainerDatabase db1 = new StringTestContainerDatabase();
        db1.insert(new StringTestContainer("", "\"", "\"\""));
        db1.insert(new StringTestContainer("\"\"\"", ",\":", ":\"\","));
        db1.insert(new StringTestContainer("", "\"", "\"\""));
        StringTestContainerDatabase db2 = new StringTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void NewLinesStringTestContainerDatabaseTest() {
        StringTestContainerDatabase db1 = new StringTestContainerDatabase();
        db1.insert(new StringTestContainer("\n", "\n", "\n"));
        db1.insert(new StringTestContainer("\n\n\n", "", "\n\n"));
        StringTestContainerDatabase db2 = new StringTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void GeneralStringTestContainerDatabaseTest() {
        StringTestContainerDatabase db1 = new StringTestContainerDatabase();
        db1.insert(new StringTestContainer("asdf", "qwerty", "abcdefgh"));
        db1.insert(new StringTestContainer("aaaa", "bbbb", "aaaa"));
        db1.insert(new StringTestContainer("1234", "2137", "1337"));
        StringTestContainerDatabase db2 = new StringTestContainerDatabase();

        //db1.cache();
        //db1.load();

        assertEquals(db2, db1);
    }

    @Test
    public void EmptyEmployeeMachineCacheTest() {
        EmployeesMachine em1 = new EmployeesMachine();
        EmployeesMachine em2 = new EmployeesMachine();
        em2.addEmployee(new Employee("a", "b", "c", "d", "e", "f"));

        JSONObject cache = em1.cache();
        em2.load(cache);

        assertEquals(em2, em1);
    }

    @Test
    public void GeneralEmployeeMachineCacheTest() {
        EmployeesMachine em1 = new EmployeesMachine();
        EmployeesMachine em2 = new EmployeesMachine();
        em1.addEmployee(new Employee("92011610096", "Jan", "Kowalski", "asdf@trewq.com", "0eba5756cd43e35ded410d5b49a870d574eb49d9", "de0401b93d9b2e00bb806e717cb58c0a"));
        em1.addEmployee(new Employee("54020154321", "Bashar", "Al-Assad", "abcvd@wp.pl", "3da541559918a808c2402bba5012f6c60b27661c", "5b65ce990c1fc67996d8a664485379ec"));
        em1.addEmployee(new Employee("39090198765", "Suhail", "Al-Hassan", "hunter12@hotmail.com", "3da541559918a808c2402bba5012f6c60b27661c", "0f7e361fe39f9b83a4b0c6c8226b26de"));

        JSONObject cache = em1.cache();
        em2.load(cache);

        assertEquals(em2, em1);
    }
}