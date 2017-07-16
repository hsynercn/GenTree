package unittest.model.genetic.tree.session;

import junit.framework.TestCase;
import model.genetic.tree.session.Input;

/**
 * Created by saruman on 17.07.2017.
 */
public class InputTest extends TestCase {

    private double[] inputSet = new double[]{ 1.0, 2.0 ,3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 };
    private String id = "A";

    public void testGetiTerminalId() throws Exception {
        Input input = new Input(id, inputSet);
        assertEquals(input.getiTerminalId(), "A");
    }

    public void testGetValues() throws Exception {
        Input input = new Input(id, inputSet);
        assertEquals(input.getValues(), this.inputSet);
    }

}