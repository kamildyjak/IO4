package pl.io4.model.database.responses;

import java.util.List;

/**
 * Created by Zax37 on 22.03.2017.
 *
 * Odpowiedź testowa.
 */
public class TestResponse extends Response {
    private List<String> names;

    public TestResponse(List<String> names) {
        super(true);
        this.names = names;
    }
}
