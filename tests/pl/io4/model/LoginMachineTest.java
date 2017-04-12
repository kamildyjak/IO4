package pl.io4.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Zax37 on 22.03.2017.
 */
public class LoginMachineTest {

    static private LoginMachine loginMachine = new LoginMachine();
    static final String user = "user";
    static final String password = "password";
    static final String wrongPassword = "wrongPassword";
    static final String sha1 = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8";

    @Test
    public void localPasswordEncryptionTest(){
        assertThat(LoginMachine.localPasswordEncryption(password), is(sha1));
    }

    @Test
    public void loginMachineTest() {
        loginMachine.pushLoginData(user, password);
        assertTrue(testPassword(user, password));
        assertFalse(testPassword(user, wrongPassword));
    }

    public static boolean isLoginOrPasswordOK(String login){
        int length = login.length();
        if(length<3) return false; // za krotkie
        boolean lowercase = false, uppercase = false,
                digit = false, special = false;
        for(int i=0; i<length; i++){
            char c = login.charAt(i);
            if(c>='a' && c<='z') lowercase = true;
            else if(c>='A' && c<='Z') uppercase = true;
            else if(c>='0' && c<='9') digit = true;
            else special = true;
        }
        return lowercase && uppercase && digit && special;
    }

    public static boolean testPassword(String user, String password) {
        return loginMachine.checkPassword(user, password);
    }

}
