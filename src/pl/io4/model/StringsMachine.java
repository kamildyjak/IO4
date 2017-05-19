package pl.io4.model;

import com.badlogic.gdx.Gdx;
import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by Zax37 on 19.05.2017.
 */
public class StringsMachine {
    JSONObject strings;

    StringsMachine() {
        setLanguage("en");
    }

    public String getString(String string) {
        return strings.get(string).toString();
    }

    public void setLanguage(String lang) {
        String file = Gdx.files.internal("lang/"+lang+".json").readString();
        strings = new JSONObject(file);
    }
}
