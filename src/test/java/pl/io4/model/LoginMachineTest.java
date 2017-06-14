package pl.io4.model;

import org.junit.Test;
import pl.io4.model.entities.Employee;
import pl.io4.model.machines.EmployeesMachine;
import pl.io4.model.machines.LoginMachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zax37 on 22.03.2017.
 */
public class LoginMachineTest {
    static final String password = "password";
    static final String sha1 = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";
    static final String md5 = "5f4dcc3b5aa765d61d8327deb882cf99";
    static final String wrongPassword = "wrongPassword";

    static final Employee testEmployee = new Employee(
            "12345678910", "Andrzej", "Testowy",
            "test@gmail.com", sha1, md5 );

    static final LoginMachine lm = new LoginMachine();
    static final EmployeesMachine em = new EmployeesMachine();

    private void setLoginMethod(LoginMachine.LoginMethod method) {
        lm.setLoginMethod(method);
        try {
            lm.reloadLogins(em);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginMachineTestLoginMethods() {
        em.getEmployees().add(testEmployee);
        setLoginMethod(LoginMachine.LoginMethod.threeFirstLettersFiveLastPeselDigits);
        assertTrue(lm.tryToLogIn("AndTes78910", password));
        assertFalse(lm.tryToLogIn("Andrzej Testowy", password));
        assertFalse(lm.tryToLogIn("AndTes78910", wrongPassword));
        setLoginMethod(LoginMachine.LoginMethod.withFullName);
        assertTrue(lm.tryToLogIn("Andrzej Testowy", password));
        assertFalse(lm.tryToLogIn("AndTes78910", password));
        setLoginMethod(LoginMachine.LoginMethod.withEMAIL);
        assertTrue(lm.tryToLogIn(testEmployee.getEmail(), password));
        setLoginMethod(LoginMachine.LoginMethod.withPESEL);
        assertTrue(lm.tryToLogIn(testEmployee.getPesel(), password));
    }

    public static boolean isPasswordOK(String password){
        int length = password.length();
        if (length<3) {
            return false; // za krotkie
        }
        boolean lowercase = false, uppercase = false,
                digit = false, special = false;
        for (int i=0; i<length; i++){
            char c = password.charAt(i);
            if(c>='a' && c<='z') lowercase = true;
            else if(c>='A' && c<='Z') uppercase = true;
            else if(c>='0' && c<='9') digit = true;
            else special = true;
        }
        return lowercase && uppercase && digit && special;
    }

}
