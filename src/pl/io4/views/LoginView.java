package pl.io4.views;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

/**
 * Created by kamil on 12.04.2017.
 */
public class LoginView extends View {
    Table inner;


    public void loginSucceeded(){
        inner.add("Zostałeś zalogowany do systemu!").center();

    }

    public void loginEroor(){
        inner.add("Przekroczyłeś ilość błędnych prób!").center();
    }

    public void loginCancelled(){
        inner.add("Anulowałeś logowanie do systemu").center();
    }

    @Override
    protected void addViewElements() {
        inner = new Table(skin).background("white");
        inner.defaults().pad(10);
        ScrollPane scroll = new ScrollPane(inner);
        table.add(scroll).expand().left().bottom().width((int)(V_WIDTH)).height(V_HEIGHT);

    }
}
