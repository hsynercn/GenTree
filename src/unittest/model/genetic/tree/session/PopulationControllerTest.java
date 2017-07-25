package unittest.model.genetic.tree.session;

import junit.framework.TestCase;
import model.genetic.tree.*;
import model.genetic.tree.error.TreeError;
import model.genetic.tree.functions.Sub;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.session.PopulationController;
import model.genetic.tree.terminals.Variable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saruman on 13.07.2017.
 */
public class PopulationControllerTest extends TestCase {

    private HashMap<String, IFunction> functionHashMap = new HashMap<String, IFunction>();
    private HashMap<String, ITerminal> terminalHashMap = new HashMap<String, ITerminal>();
    private ArrayList<String> functions = new ArrayList<String>();
    private ArrayList<String> terminals = new ArrayList<String>();
    private SetPool setPool;
    private GenePool genePool;

    private Node leaf = new Node(null, "function", Node.NodeType.LEAF);
    private Tree tree = new Tree(leaf);

    public void setUp() throws Exception {
        super.setUp();
        IFunction sum = new Sum();
        IFunction sub = new Sub();

        functionHashMap.put(sum.getSymbol(), sum);
        functionHashMap.put(sub.getSymbol(), sub);

        functions.add(sum.getSymbol());
        functions.add(sub.getSymbol());

        terminalHashMap.put("A", new Variable(3.0, "A"));
        terminalHashMap.put("B", new Variable(4.0, "B"));

        terminals.add("A");
        terminals.add("B");

        setPool = new SetPool(functionHashMap, terminalHashMap);
        genePool = new GenePool(terminals, functions);
    }

    public void testGenerateFull() throws Exception {
        assertEquals(PopulationController.generate(PopulationController.PopulationType.FULL,genePool,setPool,10,5).getPopulationSize(),10);
    }

    public void testGenerateGrow() throws Exception {
        assertEquals(PopulationController.generate(PopulationController.PopulationType.GROW,genePool,setPool,10,5).getPopulationSize(),10);
    }

    public void testGenerateRampedHalfAndHalf() throws Exception {
        assertEquals(PopulationController.generate(PopulationController.PopulationType.RAMPED_HALF_AND_HALF,genePool,setPool,10,5).getPopulationSize(),10);
    }

    public void testSortError() throws Exception {
        TreeError[] treeErrors = new TreeError[5];
        treeErrors[0] = new TreeError(tree, 5.0);
        treeErrors[1] = new TreeError(tree, 9.0);
        treeErrors[2] = new TreeError(tree, 2.0);
        treeErrors[3] = new TreeError(tree, 7.0);
        treeErrors[4] = new TreeError(tree, 1.0);

        TreeError[] sorted = PopulationController.sortError(treeErrors);

        for(int i=0;i<treeErrors.length-1;i++){
            if(treeErrors[i].getError()<treeErrors[i+1].getError()){
                fail("Sorted tree error sequence is not in expected condition.");
            }
        }

    }

    public void testRankSelect() throws Exception {

        //TODO implement rank selection test
    }

    public void testSelect() throws Exception {

        //TODO implement population selection test
    }
}