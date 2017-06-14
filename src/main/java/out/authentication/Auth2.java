package out.authentication;

import pl.io4.model.Model;
import pl.io4.model.exceptions.AuthConnectionException;
import pl.io4.model.exceptions.CardNoCashException;
import pl.io4.model.exceptions.InvalidPinException;

/**
 * Created by Marcin on 10.05.2017.
 */
public class Auth2 {

    private static int count = 0;
    private static int maxCount = 2;

    private static boolean isConnected(){
        if(true){
            return true;
        }else{
            return false;
        }
    }

    private static boolean readCard(){
        if(true){
            return true;
        }else{
            return false;
        }
    }

    private static boolean checkPIN(int pin){
        if(false){
            return true;
        }else{
            count++;
            return false;
        }
    }

    private static boolean checkIfCash(double price){
        if(true){
            return true;
        }else{
            return false;
        }
    }

    public static boolean authorize(int id, int pin, double price) throws InvalidPinException, CardNoCashException, AuthConnectionException {
        if(!isConnected()){
            throw  new AuthConnectionException(Model.getString("AUTH_CONNECTION_FAILURE"));
        }
        if(!readCard()){
            throw new AuthConnectionException(Model.getString("CARD_READ_FAILURE"));
        }
        if(!checkPIN(pin)) {
            if(count == maxCount){
                count = 0;
                throw  new AuthConnectionException(Model.getString("CARD_TO_MANY_ERRORS"));
            }
            throw new InvalidPinException(Model.getString("CARD_INVALID_PIN"));
        }
        if(!checkIfCash(price)){
            throw new CardNoCashException(Model.getString("CARD_EMPTY"));
        }

        return price < 30;
    }
}
