package pl.io4;

import com.badlogic.gdx.Game;
import pl.io4.controllers.Controller;
import pl.io4.controllers.TestController;
import pl.io4.model.Model;
import pl.io4.views.TestView;
import pl.io4.views.View;

/**
 * Created by Zax37 on 29.03.2017.
 */
public class NextGen extends Game {
    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

    private Model model;
    private View view;
    private Controller controller;

    public void create () {
        model = new Model();
        view = new TestView();
        try {
            controller = new TestController(this);
            setScreen(view);
        } catch (Controller.NoSuchElementException e){
            System.out.println(e.toString());
        }
    }

    public void resize (int width, int height) {
        view.resize(width, height);
    }

    public void dispose() {
        view.dispose();
    }
}
