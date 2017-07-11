package unittest.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.Node;
import model.genetic.tree.terminals.Constant;
import model.genetic.tree.terminals.Variable;

/**
 * Created by saruman on 12.07.2017.
 */
public class NodeTest extends TestCase {

    private Node leaf = new Node(null, "function", Node.NodeType.LEAF);
    private Node parent = new Node(null, "function", Node.NodeType.NODE);
    public void setUp() throws Exception {
        super.setUp();

    }

    public void testSetLeftChild() throws Exception {

        Node node = new Node(null, "function", Node.NodeType.NODE);
        node.setLeftChild(leaf);
        assertEquals(leaf, node.getLeftChild());
    }

    public void testSetRightChild() throws Exception {
        Node node = new Node(null, "function", Node.NodeType.NODE);
        node.setRightChild(leaf);
        assertEquals(leaf, node.getRightChild());
    }

    public void testGetLeftChild() throws Exception {
        Node node = new Node(null, "function", Node.NodeType.NODE);
        node.setLeftChild(leaf);
        assertEquals(leaf, node.getLeftChild());
    }

    public void testGetRightChild() throws Exception {
        Node node = new Node(null, "function", Node.NodeType.NODE);
        node.setRightChild(leaf);
        assertEquals(leaf, node.getRightChild());
    }

    public void testGetNodeType() throws Exception {
        Node node = new Node(null, "function", Node.NodeType.NODE);
        assertEquals(Node.NodeType.NODE, node.getNodeType());
    }

    public void testSetParent() throws Exception {
        Node node = new Node(parent, "function", Node.NodeType.NODE);
        assertEquals(parent, node.getParent());
    }

    public void testGetiFunctionID() throws Exception {
        Node node = new Node(null, "function", Node.NodeType.NODE);
        assertEquals("function", node.getiFunctionID());
        Node node2 = new Node(null, "terminal", Node.NodeType.LEAF);
        assertEquals(null, node2.getiFunctionID());
    }

    public void testGetiTerminalDI() throws Exception {
        Node node = new Node(null, "terminal", Node.NodeType.LEAF);
        assertEquals("terminal", node.getiTerminalID());
        Node node2 = new Node(null, "function", Node.NodeType.NODE);
        assertEquals(null, node2.getiTerminalID());
    }

    public void testGetParent() throws Exception {
        Node node = new Node(parent, "terminal", Node.NodeType.LEAF);
        assertEquals(parent, node.getParent());
    }

    public void testReplaceChild() throws Exception {
        Node node = new Node(null, "terminal", Node.NodeType.LEAF);
        Node left = new Node(node, "terminal", Node.NodeType.LEAF);
        Node right = new Node(node, "terminal", Node.NodeType.LEAF);
        Node left2 = new Node(node, "terminal", Node.NodeType.LEAF);
        Node right2 = new Node(node, "terminal", Node.NodeType.LEAF);
        node.setLeftChild(left);
        node.setRightChild(right);
        node.replaceChild(left, left2);
        assertEquals(left2, node.getLeftChild());
        node.replaceChild(right, right2);
        assertEquals(right2, node.getRightChild());
    }

}