package pl.io4.views;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import pl.io4.NextGen;
import pl.io4.model.Model;

import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

/**
 * Created by kamil on 12.04.2017.
 */
public final class LoginView extends View {
    private static final int INPUT_WIDTH = NextGen.V_WIDTH / 2;
    private static final int INPUT_HEIGHT = 30;
    private static final int INPUT_MAX_LENGTH = 20;
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
        inner.add(Model.getString("LOGIN_TO_MANY_ERRORS")).center();
    }

    public void inputsNotFilled() {
        inner.clear();
        inner.add(Model.getString("LOGIN_INPUTS_NOT_FILLED")).center();
        inner.row();
        createLoginInputs();
    }

    public void loginInvalid() {
        inner.clear();
        inner.add(Model.getString("LOGIN_INVALID")).center();
        inner.row();
        createLoginInputs();
    }

    public void loginSucces() {
        inner.clear();
        inner.add(Model.getString("LOGIN_SUCCESS"));
    }

    @Override
    protected void addViewElements() {
        Skin skin = getSkin();
        Table table = getTable();
        inner = new Table(skin).background("gray");
        inner.defaults().pad(PAD_SMALL);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom().width(V_WIDTH).height(V_HEIGHT);

        submit = new TextButton(Model.getString("LOGIN"), skin);
        createLoginInputs();
    }

    private void createLoginInputs() {
        Skin skin = getSkin();

        login = new TextField("", skin);
        login.setMessageText(Model.getString("USERNAME"));
        login.setMaxLength(INPUT_MAX_LENGTH);
        login.getStyle().background.setLeftWidth(PAD_SMALL);

        password = new TextField("", skin);
        password.setMessageText(Model.getString("PASSWORD"));
        password.setPasswordMode(true);
        password.setPasswordCharacter('*');
        password.setMaxLength(INPUT_MAX_LENGTH);

        inner.add(login).width(INPUT_WIDTH).height(INPUT_HEIGHT);
        inner.row();
        inner.add(password).width(INPUT_WIDTH).height(INPUT_HEIGHT);
        inner.row();
        inner.add(submit).width(INPUT_WIDTH);
        if (!getInteractiveElements().containsKey("submit")) {
            addElement("submit", submit);
        }
    }
}
