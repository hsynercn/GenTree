package ut.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.IFunction;
import model.genetic.tree.ITerminal;
import model.genetic.tree.Node;
import model.genetic.tree.TreeGenerator;
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
public class TreeGeneratorTest extends TestCase {

    private ArrayList<ITerminal> terminals = new ArrayList<ITerminal>();
    private ArrayList<IFunction> functions = new ArrayList<IFunction>();

    public void setUp() throws Exception {
        super.setUp();

        terminals.add(new Constant(1.0));
        terminals.add(new Constant(2.0));
        terminals.add(new Constant(3.0));
        terminals.add(new Constant(5.0));

        terminals.add(new Variable(2.0, "A"));
        terminals.add(new Variable(3.0, "B"));

        functions.add(new Div());
        functions.add(new Mul());
        functions.add(new Sub());
        functions.add(new Sum());

    }

    public void testGenerateFullTree() throws Exception {

        TreeGenerator treeGenerator = new TreeGenerator(terminals, functions);
        Node node = treeGenerator.generateFullTree(5);
        assertNotNull(node.getSymbol());
        assertNotNull(node.calculateNode());
    }

    public void testGenerateGrowTree() throws Exception {

        TreeGenerator treeGenerator = new TreeGenerator(terminals, functions);
        Node node = treeGenerator.generateGrowTree(5);
        assertNotNull(node.getSymbol());
        assertNotNull(node.calculateNode());
        System.out.println("res:"+node.getSymbol());
    }



}