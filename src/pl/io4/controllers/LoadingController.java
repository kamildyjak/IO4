package pl.io4.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import pl.io4.NextGen;
import pl.io4.model.Model;
import pl.io4.model.StringsMachine;
import pl.io4.model.database.Database;
import pl.io4.views.SaleTransactionView;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class LoadingController extends Controller {

    private void changeInfoString(String text){
        try {
            ((Label) getElement("testLabel")).setText(text);
        } catch (NoSuchElementException e){

        }
    }

    public LoadingController(NextGen app) throws NoSuchElementException {
        super(app);
        Database db = app.getModel().getDatabase();
        db.start(new Runnable() { // Polaczono z baza danych
            @Override
            public void run() {
                changeInfoString(Model.getString("DATABASE_CONNECT_SUCCESS"));
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        app.switchTo(SaleTransactionView.class,
                                SaleTransactionController.class);
                    }
                });
            }
        }, new Runnable() { // Nie udalo sie polaczyc z baza danych
            @Override
            public void run() {
                changeInfoString(Model.getString("DATABASE_CONNECT_FAILURE"));
            }
        });
    }
}