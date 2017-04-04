package pl.io4;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.io4.controllers.TestController;
import pl.io4.model.Model;
import pl.io4.views.TestView;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class DesktopLauncher {
    public static void main(String[] args){
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = 640;
        cfg.height = 480;
        cfg.title = "NextGen";
        new LwjglApplication(new NextGen(), cfg);
    }
}
