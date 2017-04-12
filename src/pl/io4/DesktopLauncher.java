package pl.io4;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import static pl.io4.NextGen.V_HEIGHT;
import static pl.io4.NextGen.V_WIDTH;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class DesktopLauncher {
    public static void main(String[] args){
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = V_WIDTH;
        cfg.height = V_HEIGHT;
        cfg.title = "LoginView";
        new LwjglApplication(new NextGen(), cfg);
    }
}
