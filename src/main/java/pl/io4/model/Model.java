package pl.io4.model;

import com.badlogic.gdx.Gdx;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import org.json.JSONObject;
import pl.io4.model.database.Database;
import pl.io4.model.entities.Employee;
import pl.io4.model.entities.Permissions;
import pl.io4.model.entities.Shop;
import pl.io4.model.exceptions.EmployeePermissionException;
import pl.io4.model.machines.CategoriesMachine;
import pl.io4.model.machines.DiscountsMachine;
import pl.io4.model.machines.EmployeesMachine;
import pl.io4.model.machines.EncryptionMachine;
import pl.io4.model.machines.LoginMachine;
import pl.io4.model.machines.PermissionsMachine;
import pl.io4.model.machines.ProductsMachine;
import pl.io4.model.machines.ShopsMachine;
import pl.io4.model.machines.LocalizationMachine;
import pl.io4.model.machines.TaxesMachine;
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
    private static LocalizationMachine stm = new LocalizationMachine();
    private static ShopsMachine shm = new ShopsMachine();
    private static PermissionsMachine pem = new PermissionsMachine();
    private static CategoriesMachine cm = new CategoriesMachine();
    private static ProductsMachine prm = new ProductsMachine();
    private static DiscountsMachine dm = new DiscountsMachine();
    private static TaxesMachine tm = new TaxesMachine();
    private static boolean changes = true;

    private static Employee currentlyLoggedInUser;
    private static List<Permissions> currentUserPermissions;
    private static Shop currentlyChosenShop;

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
        return pem;
    }

    public static CategoriesMachine getCategoriesMachine() {
        return cm;
    }

    public static ProductsMachine getProductsMachine() {
        return prm;
    }

    public static TaxesMachine getTaxesMachine() {
        return tm;
    }

    public static DiscountsMachine getDiscountsMachine() {
        return dm;
    }

    public static String getString(String string) {
        return stm.getString(string);
    }

    public static Employee getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }

    public static Shop getCurrentlyChosenShop() {
        return currentlyChosenShop;
    }

    public static void setCurrentlyLoggedInUser(Employee currentlyLoggedInUser) {
        Model.currentlyLoggedInUser = currentlyLoggedInUser;
    }

    public static void updatePermissions() throws EmployeePermissionException {
        currentUserPermissions = pem.getPermissionsOf(currentlyLoggedInUser);
        if(currentUserPermissions.isEmpty()) {
            throw new EmployeePermissionException(getString("EMPLOYEE_NOT_ASSIGNED_TO_SHOP"));
        } else if(currentUserPermissions.size() == 1) {
            currentlyChosenShop = currentUserPermissions.get(0).getShop();
        }
    }

    public static boolean cacheData() {
        if(!changes) return false;

        JSONObject model = new JSONObject();
        model.put("LocalizationMachine", stm.cache());
        model.put("EmployeesMachine", em.cache());
        model.put("LoginMachine", lm.cache());
        model.put("ShopsMachine", shm.cache());
        model.put("PermissionsMachine", pem.cache());
        model.put("CategoriesMachine", cm.cache());
        model.put("ProductsMachine", prm.cache());
        model.put("DiscountsMachine", dm.cache());
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
            stm.load(model.getJSONObject("LocalizationMachine"));
            em.load(model.getJSONObject("EmployeesMachine"));
            lm.load(model.getJSONObject("LoginMachine"));
            lm.reloadLogins(em);
            shm.load(model.getJSONObject("ShopsMachine"));
            pem.load(model.getJSONObject("PermissionsMachine"));
            cm.load(model.getJSONObject("CategoriesMachine"));
            prm.load(model.getJSONObject("ProductsMachine"));
            dm.load(model.getJSONObject("DiscountsMachine"));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean needsShopChoosing() {
        return currentlyChosenShop == null;
    }
}
