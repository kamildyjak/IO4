package pl.io4.controllers;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import pl.io4.NextGen;
import pl.io4.model.database.Database;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class TestController extends Controller {

    public TestController(NextGen app) throws NoSuchElementException {
        super(app);
        Database db = app.getModel().getDatabase();
        Thread thread = new Thread(){
            @Override
            public void run(){
                db.connect();
                try {
                    ((Label) getElement("testLabel")).setText(db.isConnect()?"connected to database":"NOT connected to database");
                } catch (NoSuchElementException e){

                }
            }
        };
        addButtonClickListener("testButton", new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                ((TextButton)actor).setText("Clicked!");
            }
        });
        thread.start();
    }
}
