package ut.model.genetic.tree.terminals;

import junit.framework.TestCase;
import model.genetic.tree.terminals.Variable;

/**
 * Created by saruman on 2.07.2017.
 */
public class VariableTest extends TestCase {
    public void testGetValue() throws Exception {
        Variable variable = new Variable(10.0, "test");
        assertEquals(10.0, variable.getValue());
    }

    public void testGetSymbol() throws Exception {
        Variable variable = new Variable(10.0, "test");
        assertEquals("test", variable.getSymbol());
    }

    public void testSetValue() throws Exception {
        Variable variable = new Variable(10.0, "test");
        variable.setValue(20.0);
        assertEquals(20.0, variable.getValue());
    }

    public void testSetSymbol() throws Exception {
        Variable variable = new Variable(10.0, "test");
        variable.setSymbol("modified");
        assertEquals("modified", variable.getSymbol());
    }

}