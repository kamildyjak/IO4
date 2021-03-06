package pl.io4.model.machines;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import pl.io4.model.Model;
import pl.io4.model.cachable.CachableEnumSet;
import pl.io4.model.cachable.CachableObject;
import pl.io4.model.entities.Employee;
import pl.io4.model.exceptions.IncorrectEmployeeDataException;
import pl.io4.model.exceptions.UnknownMethodException;

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
    private Instant loginBlockedAt;
    private int errorsCount;

    private static final int MAX_ERRORS_COUNT = 1;
    private static final int INVALID_LOGINS_TIMEOUT_MINUTES = 1;

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
            } else if (++errorsCount >= MAX_ERRORS_COUNT) {
                loginBlockedAt = Instant.now();
                errorsCount = 0;
            }
        }
        return false;
    }

    public boolean isBlocked() {
        if (loginBlockedAt == null) {
            return false;
        }

        Instant now = Instant.now();
        Duration timeSinceLastLoginError = Duration.between(loginBlockedAt, now);

        return timeSinceLastLoginError.toMinutes() <= INVALID_LOGINS_TIMEOUT_MINUTES;
    }

    public void addLoginMethod(LoginMethod loginMethod) {
        legalMethods.add(loginMethod);
    }

    public void setLoginMethod(LoginMethod loginMethod) {
        legalMethods.clear();
        legalMethods.add(loginMethod);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoginMachine that = (LoginMachine) o;
        return legalMethods.equals(that.legalMethods); // && correctLogins.equals(that.correctLogins);
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
