package pl.io4.model;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zax37 on 22.03.2017.
 */

public final class LoginMachine {
    public enum LoginMethod {
      threeFirstLetters
    }

    private Map<String, String> users;
    private final LoginMethod method = LoginMethod.threeFirstLetters;

    public LoginMachine() {
        users = new HashMap<String, String>();
    }

    public static String localPasswordEncryption(String password) {
        return DigestUtils.sha1Hex(password);
    }

    public void pushLoginData(String login, String password) {
        users.put(login, localPasswordEncryption(password));
    }

    public boolean checkPassword(String login, String password) {
        return users.containsKey(login) && users.get(login).equals(localPasswordEncryption(password));
    }
}
