package pl.io4.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import pl.io4.NextGen;
import pl.io4.views.LoginView;

/**
 * Created by kamil on 12.04.2017.
 */
public class LoginController extends Controller implements  Input.TextInputListener{

    boolean login = true;
    boolean succes = false;
    String logintxt;
    String passwordtxt;
    String goodLogin = "Kamil";
    String goodPassword = "Dyju";
    int count = 0;
    private LoginView view;


    public LoginController(NextGen app) throws NoSuchElementException{
        super(app);
        view = (LoginView) super.view;

        Gdx.input.getTextInput(this, "Login", "", "Wprowadź login");

    }

    @Override
    public void input(String text){
        if(count > 4){
            view.loginEroor();
            return;
        }
        if(login){
            logintxt = text;
            System.out.println("Login: "+text);
            if(text.equals(goodLogin)) {
                login = false;
                Gdx.input.getTextInput(this, "Hasło", "", "Wprowadź hasło");
            }else{
                Gdx.input.getTextInput(this, "Błąd loginu", "", "Wprowadż ponownie login");
                count++;
            }
        }else{
            passwordtxt = text;
            System.out.println("Hasło: "+text);
            if(text.equals(goodPassword)) {
                succes = true;
                view.loginSucceeded();
            }else{
                Gdx.input.getTextInput(this, "Błąd hasła", "", "Wprowadż ponownie hasło");
            }
        }
    }

    @Override
    public void canceled() {
        view.loginCancelled();
    }
}
