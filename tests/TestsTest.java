import model.Model;

/**
 * Created by Zax37 on 14.03.2017.
 *
 * Pierwszy test, testujący działanie testów. Test testów. :)
 */

public class TestsTest {
    public static void main(String[] args){
        Model model = new Model();
        System.out.print("Test0: ...");
        if(model.getTestString()=="test") System.out.println("OK!");
        else System.out.println("FAILED!");
    }
}
