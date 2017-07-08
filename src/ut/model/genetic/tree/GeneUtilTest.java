package ut.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.*;
import model.genetic.tree.functions.Div;
import model.genetic.tree.functions.Mul;
import model.genetic.tree.functions.Sub;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.terminals.Constant;
import model.genetic.tree.terminals.Variable;

import java.util.ArrayList;

/**
 * Created by saruman on 3.07.2017.
 */
public class GeneUtilTest extends TestCase {

    private ArrayList<ITerminal> terminals = new ArrayList<ITerminal>();
    private ArrayList<IFunction> functions = new ArrayList<IFunction>();

    public void setUp() throws Exception {
        super.setUp();

        terminals.add(new Constant(1.0));
        terminals.add(new Constant(2.0));
        terminals.add(new Variable(2.0, "A"));
        terminals.add(new Variable(3.0, "B"));

        functions.add(new Div());
        functions.add(new Mul());
        functions.add(new Sub());
        functions.add(new Sum());

    }

    public void testGenerateFullTree() throws Exception {

        GeneUtil geneUtil = new GeneUtil(terminals, functions);
        Tree tree = geneUtil.generateFullTree(5);
        assertNotNull(tree.getRootNode().getSymbol());
        assertNotNull(tree.getRootNode().calculateNode());
    }

    public void testGenerateGrowTree() throws Exception {

        GeneUtil geneUtil = new GeneUtil(terminals, functions);
        Tree tree = geneUtil.generateGrowTree(5);
        assertNotNull(tree.getRootNode().getSymbol());
        assertNotNull(tree.getRootNode().calculateNode());
    }

    public void testCrossover() throws Exception {
        GeneUtil geneUtil = new GeneUtil(terminals, functions);
        Tree a = geneUtil.generateFullTree(5);
        Tree b = geneUtil.generateGrowTree(5);

        geneUtil.crossover(a, b);

        assertNotNull(a.getRootNode().calculateNode());
        assertNotNull(b.getRootNode().calculateNode());
        assertNotNull(a.getRootNode().getSymbol());
        assertNotNull(b.getRootNode().getSymbol());
    }

    public void testGetRandomLocation(){
        GeneUtil geneUtil = new GeneUtil(terminals, functions);
        Tree a = geneUtil.generateFullTree(5);
        Node sel = geneUtil.getRandomLocation(a, GeneUtil.SelectionType.INCREMENTAL_REVERSE);
        assertNotNull(sel);
    }

}