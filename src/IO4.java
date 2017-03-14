import controllers.TestController;
import model.Model;
import views.TestView;

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
