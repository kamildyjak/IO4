package pl.io4.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

/**
 * Created by kamil on 12.04.2017.
 */
public class LoginView extends View {
    private Table inner;
    private TextField login;
    private TextField password;
    private final String[] data = new String[2];
    private TextButton submit;

    public String GetLogin(){
        return data[0];
    }

    public String GetPassword(){
        return data[1];
    }

    public void TooManyErrors(){
        data[0] = data[1] = null;
        inner.clear();
        inner.add("Przekroczyles limit prob").center();
    }

    public void EmptyInput(){
        data[0] = data[1] = null;
        inner.clear();
        inner.add("Wypełnij wszystkie pola").center();
        inner.row();
        LoginInputs();
    }

    public void LoginError(){
        data[0] = data[1] = null;
        inner.clear();
        inner.add("Wprowadziłeś błędne dane").center();
        inner.row();
        LoginInputs();
    }

    public void Succes(){
        inner.clear();
        inner.add("Poprawnie zalogowałeś się do systemu");
    }

    @Override
    protected void addViewElements() {
        inner = new Table(skin).background("gray");
        inner.defaults().pad(10);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom().width((int)(V_WIDTH)).height(V_HEIGHT);

        skin.add("textfieldback", new Texture("assets/raw/bg.png"));
        skin.add("cursor", new Texture("assets/raw/cursor.png"));
        skin.add("selection", new Texture("assets/raw/selection.png"));
        submit = new TextButton("Zaloguj", skin);
        LoginInputs();
    }

    public void LoginInputs(){
        TextField.TextFieldStyle textfieldstyle = new TextField.TextFieldStyle();
        textfieldstyle.background = skin.getDrawable("textfieldback");
        textfieldstyle.disabledFontColor= Color.BLACK;
        textfieldstyle.font = skin.getFont("default");
        textfieldstyle.fontColor = Color.WHITE;
        textfieldstyle.cursor = skin.getDrawable("cursor");
        textfieldstyle.selection = skin.getDrawable("selection");

        login = new TextField("", textfieldstyle);
        login.setMessageText("Login");
        login.setMaxLength(20);
        login.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                data[0] = textField.getText();
            }
        });

        password = new TextField("", textfieldstyle);
        password.setMessageText("Password");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        password.setMaxLength(20);

        password.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char key) {
                data[1] = textField.getText();
            }
        });

        inner.add(login).width(300).height(30);
        inner.row();
        inner.add(password).width(300).height(30);
        inner.row();
        inner.add(submit).center();
        if(!interactiveElements.containsValue("submit"))
            interactiveElements.put("submit", submit);
    }
}
