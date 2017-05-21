package pl.io4.model.machines;

import com.badlogic.gdx.Gdx;
import org.json.JSONObject;

/**
 * Created by Zax37 on 19.05.2017.
 */
public final class StringsMachine {
    private JSONObject strings;

    public StringsMachine() {
        setLanguage("en");
    }

    public String getString(String string) {
        return strings.get(string).toString();
    }

    public void setLanguage(String lang) {
        String file = Gdx.files.internal("lang/" + lang + ".json")
                .readString();
        strings = new JSONObject(file);
    }
}
