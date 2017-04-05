package pl.io4;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

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
