package pl.io4.model;

import com.badlogic.gdx.Gdx;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import org.json.JSONObject;
import pl.io4.model.database.Database;
import pl.io4.model.entities.Employee;
import pl.io4.model.machines.EmployeesMachine;
import pl.io4.model.machines.EncryptionMachine;
import pl.io4.model.machines.LoginMachine;
import pl.io4.model.machines.PermissionsMachine;
import pl.io4.model.machines.ShopsMachine;
import pl.io4.model.machines.StringsMachine;
import pl.io4.model.transactions.TransactionRegister;

/**
 * Created by Zax37 on 14.03.2017.
 *
 * Model łączy się z bazą danych i opisuje niezbędne dane.
 */

public final class Model {
    private static Database db = new Database();
    private static TransactionRegister tr = new TransactionRegister();
    private static EmployeesMachine em = new EmployeesMachine();
    private static LoginMachine lm = new LoginMachine();
    private static StringsMachine stm = new StringsMachine();
    private static ShopsMachine shm = new ShopsMachine();
    private static PermissionsMachine pm = new PermissionsMachine();

    private static Employee currentlyLoggedInUser;

    private static final String MODEL_CACHE_PATH = "cache/model";

    private Model() {
        //Utility class - nie powinna być instancjonowana
    }

    public static EmployeesMachine getEmployeesMachine() {
        return em;
    }

    public static Database getDatabase() {
        return db;
    }

    public static TransactionRegister getTransactionRegister() {
        return tr;
    }

    public static LoginMachine getLoginMachine() {
        return lm;
    }

    public static ShopsMachine getShopsMachine() {
        return shm;
    }

    public static PermissionsMachine getPermissionsMachine() {
        return pm;
    }

    public static String getString(String string) {
        return stm.getString(string);
    }

    public static Employee getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }

    public static void setCurrentlyLoggedInUser(Employee currentlyLoggedInUser) {
        Model.currentlyLoggedInUser = currentlyLoggedInUser;
    }

    public static boolean cacheData() {
        JSONObject model = new JSONObject();
        model.put("EmployeesMachine", em.cache());
        model.put("LoginMachine", lm.cache());
        model.put("ShopsMachine", shm.cache());
        model.put("PermissionsMachine", pm.cache());
        try {
            FileOutputStream fos = new FileOutputStream(MODEL_CACHE_PATH);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(EncryptionMachine.dataEncode(model.toString()));
            osw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loadData() {
        try {
            String file = Gdx.files.internal(MODEL_CACHE_PATH).readString();
            JSONObject model = new JSONObject(EncryptionMachine.dataDecode(file));
            em.load(model.getJSONObject("EmployeesMachine"));
            lm.load(model.getJSONObject("LoginMachine"));
            lm.reloadLogins(em);
            shm.load(model.getJSONObject("ShopsMachine"));
            pm.load(model.getJSONObject("PermissionsMachine"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
