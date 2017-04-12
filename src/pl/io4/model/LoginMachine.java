package pl.io4.model;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zax37 on 22.03.2017.
 */

public class LoginMachine {
    public enum loginMethod {
      threeFirstLetters;
    };

    Map<String, String> users;
    private final loginMethod method = loginMethod.threeFirstLetters;

    public LoginMachine(){
        users = new HashMap<String, String>();
    }

    public static String localPasswordEncryption(String password){
        return DigestUtils.sha1Hex(password);
    }

    public void pushLoginData(String login, String password) {
        users.put(login, localPasswordEncryption(password));
    }

    public boolean checkPassword(String login, String password) {
        if(!users.containsKey(login)) return false;
        return users.get(login).equals(localPasswordEncryption(password));
    }
}
