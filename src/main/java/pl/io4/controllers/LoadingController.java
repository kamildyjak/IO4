package pl.io4.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import pl.io4.NextGen;
import pl.io4.model.Model;
import pl.io4.model.database.Database;
import pl.io4.views.LoginView;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class LoadingController extends Controller {
    private Queue<Runnable> queue;
    private static final int SHORT_DELAY_MS = 500;

    private void changeInfoString(String text) {
        Label testLabel = getElement("testLabel");
        if (testLabel != null) {
            testLabel.setText(text);
        }
    }

    private void shortDelay() {
        long cT = System.currentTimeMillis();
        while (System.currentTimeMillis() - cT < SHORT_DELAY_MS) {
            cT = cT; // Nie rób nic
        }
    }

    public LoadingController(NextGen app) throws NoSuchElementException {
        super(app);
        queue = new LinkedBlockingQueue<Runnable>();

        queue.add(new Runnable() { // Odczyt zapisanych danych
            @Override
            public void run() {
                changeInfoString(Model.getString("CACHE_LOADING"));
                shortDelay();
                if (Model.loadData()) {
                    changeInfoString(Model.getString("CACHE_SUCCESS"));
                } else {
                    changeInfoString(Model.getString("CACHE_FAILURE"));
                }
                shortDelay();
            }
        });

        queue.add(new Runnable() { // Próba połączenia z bazą danych
            @Override
            public void run() {
                changeInfoString(Model.getString("DATABASE_CONNECTING"));
                Database db = Model.getDatabase();
                db.start(new Runnable() { // Udało się połączyć
                    @Override
                    public void run() {
                        db.end();
                        changeInfoString(Model.getString("DATABASE_CONNECT_SUCCESS"));
                        shortDelay();
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                app.switchTo(LoginView.class,
                                        LoginController.class);
                            }
                        });
                    }
                }, new Runnable() { // Nie udało się połączyć
                    @Override
                    public void run() {
                        changeInfoString(Model.getString("DATABASE_CONNECT_FAILURE"));
                        shortDelay();
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                app.switchTo(LoginView.class,
                                        LoginController.class);
                            }
                        });
                    }
                });
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    Runnable task = queue.poll();
                    if (task == null) {
                        break;
                    }
                    task.run();
                } while (true);
            }
        }).start();
    }
}
