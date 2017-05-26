package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import pl.io4.NextGen;
import pl.io4.views.View;

import java.util.HashMap;

/**
 * Created by Zax37 on 29.03.2017.
 */

public class Controller {
    private HashMap<String, Actor> interactiveElements;
    private NextGen app;

    Controller(NextGen app) {
        this.app = app;
        View view = app.getView();
        interactiveElements = view.getInteractiveElements();
        app.setScreen(view);
    }

    public final <T1 extends View, T2 extends Controller>
    void switchTo(Class<T1> view, Class<T2> controller) {
        app.switchTo(view, controller);
    }

    static class NoSuchElementException extends Exception {
    }

    final void addButtonClickListener(String name, EventListener listener)
    throws NoSuchElementException {
        Actor actor = interactiveElements.get(name);
        if (actor == null) {
            throw new NoSuchElementException();
        }
        actor.addListener(listener);
    }

    final <T extends Actor> T getElement(String name) {
        return (T) interactiveElements.get(name);
    }

    final <T extends View> T getView() {
        return app.getView();
    }
}
