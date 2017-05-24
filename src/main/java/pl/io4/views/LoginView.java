package pl.io4.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

/**
 * Created by kamil on 12.04.2017.
 */
public final class LoginView extends View {
    private static final int INPUT_WIDTH = 300;
    private static final int INPUT_HEIGHT = 30;
    private static final int INPUT_MAX_LENGTH = 20;
    private static final int PAD_SMALL = 10;
    private Table inner;
    private TextField login;
    private TextField password;
    private TextButton submit;

    public String getLogin() {
        return login.getText();
    }

    public String getPassword() {
        return password.getText();
    }

    public void tooManyErrors() {
        inner.clear();
        inner.add("Przekroczyles limit prob").center();
    }

    public void inputsNotFilled() {
        inner.clear();
        inner.add("Wypełnij wszystkie pola").center();
        inner.row();
        createLoginInputs();
    }

    public void loginError() {
        inner.clear();
        inner.add("Wprowadziłeś błędne dane").center();
        inner.row();
        createLoginInputs();
    }

    public void loginSucces() {
        inner.clear();
        inner.add("Poprawnie zalogowałeś się do systemu");
    }

    @Override
    protected void addViewElements() {
        Skin skin = getSkin();
        Table table = getTable();
        inner = new Table(skin).background("gray");
        inner.defaults().pad(PAD_SMALL);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom().width(V_WIDTH).height(V_HEIGHT);

        skin.add("textfieldback", new Texture("raw/bg.png"));
        skin.add("cursor", new Texture("raw/cursor.png"));
        skin.add("selection", new Texture("raw/selection.png"));
        submit = new TextButton("Zaloguj", skin);
        createLoginInputs();
    }

    private void createLoginInputs() {
        Skin skin = getSkin();
        TextField.TextFieldStyle textfieldstyle = new TextField.TextFieldStyle();
        textfieldstyle.background = skin.getDrawable("textfieldback");
        textfieldstyle.disabledFontColor = Color.BLACK;
        textfieldstyle.font = skin.getFont("default");
        textfieldstyle.fontColor = Color.BLACK;
        textfieldstyle.cursor = skin.getDrawable("cursor");
        textfieldstyle.selection = skin.getDrawable("selection");

        login = new TextField("", textfieldstyle);
        login.setMessageText("Login");
        login.setMaxLength(INPUT_MAX_LENGTH);

        password = new TextField("", textfieldstyle);
        password.setMessageText("Password");
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        password.setMaxLength(INPUT_MAX_LENGTH);

        inner.add(login).width(INPUT_WIDTH).height(INPUT_HEIGHT);
        inner.row();
        inner.add(password).width(INPUT_WIDTH).height(INPUT_HEIGHT);
        inner.row();
        inner.add(submit).center();
        if (!getInteractiveElements().containsKey("submit")) {
            addElement("submit", submit);
        }
    }
}
