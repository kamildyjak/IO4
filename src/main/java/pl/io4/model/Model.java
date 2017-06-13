package pl.io4.model;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import pl.io4.model.machines.LocalizationMachine;
import pl.io4.model.machines.LoginMachine;
import pl.io4.model.machines.PermissionsMachine;
import pl.io4.model.machines.ProductsMachine;
import pl.io4.model.machines.ShopsMachine;
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
    private static EmployeesMachine employeesMachine = new EmployeesMachine();
    private static LoginMachine loginMachine = new LoginMachine();
    private static LocalizationMachine localizationMachine = new LocalizationMachine();
    private static ShopsMachine shopsMachine = new ShopsMachine();
    private static PermissionsMachine permissionsMachine = new PermissionsMachine();
    private static CategoriesMachine categoriesMachine = new CategoriesMachine();
    private static ProductsMachine productsMachine = new ProductsMachine();
    private static DiscountsMachine discountsMachine = new DiscountsMachine();
    private static TaxesMachine tm = new TaxesMachine();
    private static boolean changes = true;

    private static Employee currentlyLoggedInUser;
    private static List<Permissions> currentUserPermissions;
    private static Shop currentlyChosenShop;

    private static final String PATH_PART_CACHE = "cache";
    private static final String PATH_PART_LANG = "lang";
    private static final String PATH_PART_MODEL = "model";

    private Model() {
        //Utility class - nie powinna być instancjonowana
    }

    public static EmployeesMachine getEmployeesMachine() {
        return employeesMachine;
    }

    public static Database getDatabase() {
        return db;
    }

    public static TransactionRegister getTransactionRegister() {
        return tr;
    }

    public static LoginMachine getLoginMachine() {
        return loginMachine;
    }

    public static ShopsMachine getShopsMachine() {
        return shopsMachine;
    }

    public static PermissionsMachine getPermissionsMachine() {
        return permissionsMachine;
    }

    public static CategoriesMachine getCategoriesMachine() {
        return categoriesMachine;
    }

    public static ProductsMachine getProductsMachine() {
        return productsMachine;
    }

    public static TaxesMachine getTaxesMachine() {
        return tm;
    }

    public static DiscountsMachine getDiscountsMachine() {
        return discountsMachine;
    }

    public static String getString(String string) {
        return localizationMachine.getString(string);
    }

    public static Employee getCurrentlyLoggedInUser() {
        return currentlyLoggedInUser;
    }

    public static Shop getCurrentlyChosenShop() {
        return currentlyChosenShop;
    }

    public static void setCurrentlyChosenShop(Shop shop) {
        currentlyChosenShop = shop;
    }

    public static void setCurrentlyLoggedInUser(Employee currentlyLoggedInUser) {
        Model.currentlyLoggedInUser = currentlyLoggedInUser;
    }

    public static List<Shop> getAvaibleShops() {
        List<Shop> shops = new ArrayList<>();
        if (currentUserPermissions != null) {
            for (Permissions permission : currentUserPermissions) {
                shops.add(permission.getShop());
            }
        }
        return shops;
    }

    public static void updatePermissions() throws EmployeePermissionException {
        currentUserPermissions = permissionsMachine.getPermissionsOf(currentlyLoggedInUser);
        if (currentUserPermissions.isEmpty()) {
            throw new EmployeePermissionException(getString("EMPLOYEE_NOT_ASSIGNED_TO_SHOP"));
        } else if (currentUserPermissions.size() == 1) {
            currentlyChosenShop = currentUserPermissions.get(0).getShop();
        }
    }

    public static boolean cacheData(String path) {
        if (!changes) {
            return false;
        }

        if (path == null) {
            path = PATH_PART_CACHE;
        }

        JSONObject model = new JSONObject();
        model.put("LocalizationMachine", localizationMachine.cache());
        model.put("EmployeesMachine", employeesMachine.cache());
        model.put("LoginMachine", loginMachine.cache());
        model.put("ShopsMachine", shopsMachine.cache());
        model.put("PermissionsMachine", permissionsMachine.cache());
        model.put("CategoriesMachine", categoriesMachine.cache());
        model.put("ProductsMachine", productsMachine.cache());
        model.put("DiscountsMachine", discountsMachine.cache());
        try {
            FileOutputStream fos = new FileOutputStream(path + "/" + PATH_PART_MODEL);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            osw.write(EncryptionMachine.dataEncode(model.toString()));
            osw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loadData(String path) {
        try {
            if (path == null) {
                path = PATH_PART_CACHE;
            }
            String lang = new String(Files.readAllBytes(Paths.get(path + "/" + PATH_PART_LANG)));
            localizationMachine.setLanguage(lang);
            String file = new String(Files.readAllBytes(Paths.get(path + "/" + PATH_PART_MODEL)));
            JSONObject model = new JSONObject(EncryptionMachine.dataDecode(file));
            employeesMachine.load(model.getJSONObject("EmployeesMachine"));
            loginMachine.load(model.getJSONObject("LoginMachine"));
            loginMachine.reloadLogins(employeesMachine);
            shopsMachine.load(model.getJSONObject("ShopsMachine"));
            permissionsMachine.load(model.getJSONObject("PermissionsMachine"));
            categoriesMachine.load(model.getJSONObject("CategoriesMachine"));
            productsMachine.load(model.getJSONObject("ProductsMachine"));
            discountsMachine.load(model.getJSONObject("DiscountsMachine"));
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
