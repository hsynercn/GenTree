package unittest.model.genetic.tree.terminals;

import junit.framework.TestCase;
import model.genetic.tree.IFunction;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.terminals.Constant;

/**
 * Created by saruman on 2.07.2017.
 */
public class ConstantTest extends TestCase {
    public void testGetValue() throws Exception {
        Constant myConstant = new Constant(10.0);
        assertEquals(10.0, myConstant.getValue());
    }

    public void testSetValue() throws Exception {
        Constant myConstant = new Constant(10.0);
        assertFalse( myConstant.setValue(15.0) );
    }

    public void testGetSymbol() throws Exception {
        Constant myConstant = new Constant(10.0);
        assertEquals(String.format("%f",10.0), myConstant.getSymbol());
    }

    public void testClone() throws Exception {
        Constant myConstant = new Constant(10.0);
        assertTrue(myConstant.clone() instanceof Constant);
    }

}