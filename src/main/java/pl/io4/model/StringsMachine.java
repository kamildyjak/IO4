package pl.io4.model;

import com.badlogic.gdx.Gdx;
import org.json.JSONObject;

/**
 * Created by Zax37 on 19.05.2017.
 */
final class StringsMachine {
    private JSONObject strings;

    StringsMachine() {
        setLanguage("en");
    }

    String getString(String string) {
        return strings.get(string).toString();
    }

    void setLanguage(String lang) {
        String file = Gdx.files.internal("lang/" + lang + ".json")
                .readString();
        strings = new JSONObject(file);
    }
}
