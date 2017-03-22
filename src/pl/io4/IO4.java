package pl.io4;

import pl.io4.controllers.TestController;
import pl.io4.model.Model;
import pl.io4.views.TestView;

/**
 * Created by Zax37 on 14.03.2017.
 */

public class IO4 {
    public static void main(String[] args){
        TestView view = new TestView();
        Model model = new Model();
        TestController controller = new TestController(view, model);
        view.setVisible(true);
    }
}
