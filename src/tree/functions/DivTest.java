package tree.functions;

import junit.framework.TestCase;
import model.genetic.tree.functions.Div;

/**
 * Created by saruman on 2.07.2017.
 */
public class DivTest extends TestCase {
    public void testExecute() throws Exception {
        Div myDiv = new Div();

        assertEquals(5.0, myDiv.execute(10.0, 2.0));
        assertEquals(Double.MAX_VALUE, myDiv.execute(10.0, 0.0));

    }

    public void testGetSymbol() throws Exception {
        Div myDiv = new Div();

        assertEquals("Div", myDiv.getSymbol());
    }

}