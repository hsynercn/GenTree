package unittest.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.IFunction;
import model.genetic.tree.ITerminal;
import model.genetic.tree.SetPool;
import model.genetic.tree.functions.Div;
import model.genetic.tree.functions.Mul;
import model.genetic.tree.functions.Sub;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.terminals.Constant;
import model.genetic.tree.terminals.Variable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saruman on 11.07.2017.
 */
public class SetPoolTest extends TestCase {

    HashMap<String, IFunction> functionHashMap = new HashMap<String, IFunction>();
    HashMap<String, ITerminal> terminalHashMap = new HashMap<String, ITerminal>();

    public void setUp() throws Exception {
        super.setUp();
        IFunction sum = new Sum();
        IFunction sub = new Sub();
        IFunction mul = new Mul();
        IFunction div = new Div();

        functionHashMap.put(sum.getSymbol(), sum);
        functionHashMap.put(sub.getSymbol(), sub);
        functionHashMap.put(mul.getSymbol(), mul);
        functionHashMap.put(div.getSymbol(), div);

        terminalHashMap.put("c1", new Constant(1.0));
        terminalHashMap.put("c2", new Constant(2.0));
        terminalHashMap.put("A", new Variable(3.0, "A"));
        terminalHashMap.put("B", new Variable(4.0, "B"));
    }

    public void testClone() throws Exception {
        SetPool setPool = new SetPool(functionHashMap, terminalHashMap);
        SetPool clonePool = setPool.clone();

        for(String functionID: setPool.getIFuntionIDs()){
            assertNotSame(setPool.getIFunction(functionID), clonePool.getIFunction(functionID));
        }
        for(String terminalID: setPool.getITerminalIDs()){
            assertNotSame(setPool.getITerminal(terminalID), clonePool.getITerminal(terminalID));
        }
    }

    public void testGetIFunction() throws Exception {
        SetPool setPool = new SetPool(functionHashMap, terminalHashMap);
        assertNotNull(setPool.getIFunction(new Sum().getSymbol()));
    }

    public void testGetITerminal() throws Exception {
        SetPool setPool = new SetPool(functionHashMap, terminalHashMap);
        assertNotNull(setPool.getITerminal("A"));
    }

    public void testUpdateITerminalValue() throws Exception {
        SetPool setPool = new SetPool(functionHashMap, terminalHashMap);
        assertTrue(setPool.updateITerminalValue("A",10.0));
        assertEquals(10.0, setPool.getITerminal("A").getValue());
    }

    public void testGetITerminalIDs() throws Exception {
        SetPool setPool = new SetPool(functionHashMap, terminalHashMap);
        assertNotNull(setPool.getITerminalIDs());
    }

    public void testGetIFuntionIDs() throws Exception {
        SetPool setPool = new SetPool(functionHashMap, terminalHashMap);
        assertNotNull(setPool.getIFuntionIDs());
    }

}