package pl.io4;

import com.badlogic.gdx.Game;
import pl.io4.controllers.Controller;
import pl.io4.controllers.LoadingController;
import pl.io4.model.Model;
import pl.io4.views.LoadingView;
import pl.io4.views.View;

/**
 * Created by Zax37 on 29.03.2017.
 *
 * Główna aplikacja
 */
public final class NextGen extends Game {
    private View view;
    private Controller controller;

    public <T extends View> T getView() {
        return (T)view;
    }

    public <T extends Controller> T getController() {
        return (T)controller;
    }

    public static final int V_WIDTH = 800;
    public static final int V_HEIGHT = 600;

    public void create() {
        switchTo(LoadingView.class,
                LoadingController.class);
    }

    public <T1 extends View, T2 extends Controller>
    void switchTo(Class<T1> view, Class<T2> controller) {
        try {
            this.view = view.newInstance();
            this.controller = controller.getDeclaredConstructor(NextGen.class)
                    .newInstance(this);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void resize(int width, int height) {
        view.resize(width, height);
    }

    public void dispose() {
        view.dispose();
        Model.cacheData();
    }
}
