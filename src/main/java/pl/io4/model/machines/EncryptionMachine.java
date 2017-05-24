package pl.io4.model.machines;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import static pl.io4.model.machines.EncryptionMachine.EncryptionMethod.MD5;
import static pl.io4.model.machines.EncryptionMachine.EncryptionMethod.SHA1;
import pl.io4.model.entities.Employee;
import pl.io4.model.exceptions.UnknownMethodException;

/**
 * Created by Zax37 on 21.05.2017.
 */
public class EncryptionMachine {
    public enum EncryptionMethod {
        SHA1, MD5
    }
    public static final EncryptionMethod LOCAL_ENCRYPTION_METHOD = SHA1;
    public static final EncryptionMethod REMOTE_ENCRYPTION_METHOD = MD5;

    private EncryptionMachine() {
        //Utility class - nie powinna być instancjonowana
    }

    public static String encrypt(String string, EncryptionMethod method)
    throws UnknownMethodException {
        switch (method) {
            case SHA1:
                return DigestUtils.sha1Hex(string);
            case MD5:
                return DigestUtils.md5Hex(string);
            default:
                throw new UnknownMethodException("Unknown encryption method.");
        }
    }

    public static String encryptLocal(String string) {
        try {
            return encrypt(string, LOCAL_ENCRYPTION_METHOD);
        } catch (UnknownMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptRemote(String string) throws UnknownMethodException {
        try {
            return encrypt(string, REMOTE_ENCRYPTION_METHOD);
        } catch (UnknownMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getEmployeePasswordHash(Employee employee, EncryptionMethod method)
    throws UnknownMethodException {
        switch (method) {
            case SHA1:
                return employee.getHashSha1();
            case MD5:
                return employee.getHashMd5();
            default:
                throw new UnknownMethodException("Unknown encryption method.");
        }
    }

    public static String getEmployeePasswordLocalHash(Employee employee) {
        try {
            return getEmployeePasswordHash(employee, LOCAL_ENCRYPTION_METHOD);
        } catch (UnknownMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getEmployeePasswordRemoteHash(Employee employee) {
        try {
            return getEmployeePasswordHash(employee, REMOTE_ENCRYPTION_METHOD);
        } catch (UnknownMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String dataEncode(String data) {
        byte[] bytesEncoded = Base64.encodeBase64(data.getBytes());
        return new String(bytesEncoded);
    }

    public static String dataDecode(String data) {
        byte[] bytesDecoded = Base64.decodeBase64(data.getBytes());
        return new String(bytesDecoded);
    }
}
