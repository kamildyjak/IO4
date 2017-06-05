package pl.io4.model.machines;

import org.json.JSONObject;
import pl.io4.model.Model;
import pl.io4.model.cachable.CachableEnumSet;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Employee;
import pl.io4.model.exceptions.IncorrectEmployeeDataException;
import pl.io4.model.exceptions.UnknownMethodException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zax37 on 22.03.2017.
 */

public final class LoginMachine extends CachableObject {
    public enum LoginMethod {
        threeFirstLettersFiveLastPeselDigits,
        withPESEL, withEMAIL, withFullName
    }
    private CachableEnumSet<LoginMethod> legalMethods = new CachableEnumSet(LoginMethod.class);
    private Map<String, Employee> correctLogins;

    private static final int NAME_LETTERS_NB = 3;
    private static final int PESEL_DIGITS_NB = 5;

    public LoginMachine() {
        correctLogins = new HashMap<String, Employee>();
    }

    public static String createLogin(Employee employee, LoginMethod method)
    throws IncorrectEmployeeDataException, UnknownMethodException {
        switch (method) {
            case threeFirstLettersFiveLastPeselDigits:
                String firstName = employee.getFirstName(),
                        lastName = employee.getLastName(),
                        pesel = employee.getPesel();
                if (firstName.length() < NAME_LETTERS_NB
                        || lastName.length() < NAME_LETTERS_NB
                        || pesel.length() < PESEL_DIGITS_NB) {
                    throw new IncorrectEmployeeDataException("");
                }
                return firstName.substring(0, NAME_LETTERS_NB)
                        + lastName.substring(0, NAME_LETTERS_NB)
                        + pesel.substring(pesel.length() - PESEL_DIGITS_NB);
            case withEMAIL:
                return employee.getEmail();
            case withFullName:
                return employee.getFirstName() + " " + employee.getLastName();
            case withPESEL:
                return employee.getPesel();
            default:
                throw new UnknownMethodException("Unknown login method.");
        }
    }

    public void reloadLogins(EmployeesMachine employeesMachine)
    throws IncorrectEmployeeDataException, UnknownMethodException {
        correctLogins.clear();
        for (Employee employee : employeesMachine.getEmployees()) {
            for (LoginMethod method : legalMethods) {
                correctLogins.put(createLogin(employee, method), employee);
            }
        }
    }

    public boolean tryToLogIn(String login, String password) {
        if (correctLogins.containsKey(login)) {
            Employee employee = correctLogins.get(login);
            if (EncryptionMachine.getEmployeePasswordLocalHash(employee)
                .equals(EncryptionMachine.encryptLocal(password))) {
                Model.setCurrentlyLoggedInUser(employee);
                return true;
            }
        }
        return false;
    }

    public boolean checkLogin(String login){
        if (correctLogins.containsKey(login)) {
            return true;
        }else{
            return false;
        }
    }

    public boolean checkIfLoginBlocked(String login){
        EmployeesMachine employeesMachine = new EmployeesMachine();
        Employee employee = employeesMachine.getEmployee(login);

        String lastLoginError = employee.getLoginBlocked();

        int lastLoginErrorMinute = Integer.parseInt(lastLoginError);

        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date();
        String currentTime = dateFormat.format(date);

        int currentMinute = Integer.parseInt(currentTime);

        if(currentMinute < lastLoginErrorMinute || currentMinute - lastLoginErrorMinute > 30){
            return false;
        }else{
            return true;
        }
    }

    public void blockLogin(String login){
        EmployeesMachine employeesMachine = new EmployeesMachine();
        Employee employee = employeesMachine.getEmployee(login);

        DateFormat dateFormat = new SimpleDateFormat("mm");
        Date date = new Date();
        String currentTime = dateFormat.format(date);

        employee.setLoginBlocked(currentTime);
    }

    public void addLoginMethod(LoginMethod loginMethod) {
        legalMethods.add(loginMethod);
    }

    public void setLoginMethod(LoginMethod loginMethod) {
        legalMethods.clear();
        legalMethods.add(loginMethod);
    }

    @Override
    public JSONObject cache() {
        JSONObject ret = new JSONObject();
        ret.put("legalMethods", legalMethods.cache());
        return ret;
    }

    @Override
    public void load(JSONObject data) {
        legalMethods.load(data.getJSONArray("legalMethods"));
    }
}
