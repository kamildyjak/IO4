package pl.io4.controllers;

import pl.io4.model.Model;
import pl.io4.views.TestView;

/**
 * Created by Zax37 on 14.03.2017.
 *
 * Kontroler stanowi najważniejszy element, łączy pl.io4.model z widokiem.
 */

public class TestController {
    private TestView view;
    private Model model;

    public TestController(TestView view, Model model){
        this.view = view;
        this.model = model;

        view.setTestText(model.getTestString());
    }
}
