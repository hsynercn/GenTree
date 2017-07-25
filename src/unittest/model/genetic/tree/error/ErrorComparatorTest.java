package unittest.model.genetic.tree.error;

import junit.framework.TestCase;
import model.genetic.tree.Node;
import model.genetic.tree.Tree;
import model.genetic.tree.error.ErrorComparator;
import model.genetic.tree.error.TreeError;

/**
 * Created by saruman on 26.07.2017.
 */
public class ErrorComparatorTest extends TestCase {

    private Node leaf = new Node(null, "function", Node.NodeType.LEAF);
    private Tree tree = new Tree(leaf);

    public void testCompareSmaller() throws Exception {
        TreeError e1 = new TreeError(tree, 10.0);
        TreeError e2 = new TreeError(tree, 15.0);
        ErrorComparator errorComparator = new ErrorComparator();
        assertEquals(1, errorComparator.compare(e1, e2));
    }

    public void testCompareEqual() throws Exception {
        TreeError e1 = new TreeError(tree, 15.0);
        TreeError e2 = new TreeError(tree, 15.0);
        ErrorComparator errorComparator = new ErrorComparator();
        assertEquals(0, errorComparator.compare(e1, e2));
    }

    public void testCompareGreater() throws Exception {
        TreeError e1 = new TreeError(tree, 15.0);
        TreeError e2 = new TreeError(tree, 10.0);
        ErrorComparator errorComparator = new ErrorComparator();
        assertEquals(-1, errorComparator.compare(e1, e2));
    }

}