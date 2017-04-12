package pl.io4;

import org.junit.Test;
import pl.io4.model.Model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Zax37 on 14.03.2017.
 *
 * Pierwszy test, testujący działanie testów. Test testów. :)
 */

public class IO4Test {
    @Test
    public void testStringTest(){
        Model model = new Model();
        assertThat(model.getTestString(), is("test"));
    }
}
