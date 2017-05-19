package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import pl.io4.NextGen;
import pl.io4.model.Model;
import pl.io4.views.View;

import java.util.HashMap;

/**
 * Created by Zax37 on 29.03.2017.
 */

public class Controller {
    protected HashMap<String, Actor> interactiveElements;
    protected View view;
    protected NextGen app;

    public Controller(NextGen app){
        view = app.getView();
        interactiveElements = view.getInteractiveElements();
        app.setScreen(view);
    }

    public <T1 extends View, T2 extends Controller>
    void switchTo(Class<T1> view, Class<T2> controller) {
        app.switchTo(view, controller);
    }

    public static class NoSuchElementException extends Exception{}

    protected void addButtonClickListener(String name, EventListener listener) throws NoSuchElementException {
        Actor actor = interactiveElements.get(name);
        if(actor == null) throw new NoSuchElementException();
        actor.addListener(listener);
    }

    protected Actor getElement(String name) throws NoSuchElementException {
        Actor actor = interactiveElements.get(name);
        if(actor == null) throw new NoSuchElementException();
        return actor;
    }
}
