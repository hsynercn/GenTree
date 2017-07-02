package ut.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.IFunction;
import model.genetic.tree.ITerminal;
import model.genetic.tree.Node;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.terminals.Constant;

/**
 * Created by saruman on 2.07.2017.
 */
public class NodeTest extends TestCase {
    public void testCalculateNode() throws Exception {

        ITerminal terminal = new Constant(10.0);
        Node node = new Node(terminal);
        assertEquals(10.0, node.calculateNode());

    }

    public void testCalculateNode_1() throws Exception {

        ITerminal terminalLeft = new Constant(10.0);
        ITerminal terminalRight = new Constant(20.0);
        IFunction function = new Sum();

        Node left = new Node(terminalLeft);
        Node right = new Node(terminalRight);
        Node root = new Node(null, left, right, function);

        assertEquals(30.0, root.calculateNode());

    }

}