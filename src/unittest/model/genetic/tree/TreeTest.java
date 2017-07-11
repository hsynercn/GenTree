package unittest.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.Node;
import model.genetic.tree.Tree;

/**
 * Created by saruman on 12.07.2017.
 */
public class TreeTest extends TestCase {

    private Node node = new Node(null, "function", Node.NodeType.NODE);

    public void testGetRootNode() throws Exception {
        Tree tree = new Tree(node);
        assertEquals(node, tree.getRootNode());
    }

    public void testSetRootNode() throws Exception {
        Tree tree = new Tree(node);
        Node node2 = new Node(null, "function", Node.NodeType.NODE);
        tree.setRootNode(node2);
        assertEquals(node2, tree.getRootNode());
    }

}