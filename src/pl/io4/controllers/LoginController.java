package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.LoginMachine;
import pl.io4.views.LoginView;

/**
 * Created by kamil on 12.04.2017.
 */
public class LoginController extends Controller {

    private String login;
    private String password;
    private LoginView view;
    private LoginMachine loginMachine;
    private boolean succes;
    private int count;


    public LoginController(NextGen app) throws NoSuchElementException{
        super(app);
        view = (LoginView) super.view;
        loginMachine = new LoginMachine();
        count = 0;

        addButtonClickListener("submit", new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if(count > 4){
                    view.TooManyErrors();
                    return;
                }
                login = view.GetLogin();
                password = view.GetPassword();
                if(login == null || password == null){
                    view.EmptyInput();
                    count++;
                }else{
                    succes = loginMachine.checkPassword(login, password);
                    if(succes){
                        view.Succes();
                    }else{
                        view.LoginError();
                        count++;
                    }
                }
            }
        });

    }


}
