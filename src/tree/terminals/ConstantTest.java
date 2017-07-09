package tree.terminals;

import junit.framework.TestCase;
import model.genetic.tree.terminals.Constant;

/**
 * Created by saruman on 2.07.2017.
 */
public class ConstantTest extends TestCase {
    public void testGetValue() throws Exception {
        Constant myConstant = new Constant(10.0);
        assertEquals(10.0, myConstant.getValue());

    }

    public void testGetSymbol() throws Exception {
        Constant myConstant = new Constant(10.0);
        assertEquals(String.format("%f",10.0), myConstant.getSymbol());
    }

}