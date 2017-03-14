package controllers;

import model.Model;
import views.TestView;

/**
 * Created by Zax37 on 14.03.2017.
 *
 * Kontroler stanowi najważniejszy element, łączy model z widokiem.
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
