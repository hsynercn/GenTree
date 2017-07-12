package unittest.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.*;
import model.genetic.tree.functions.Div;
import model.genetic.tree.functions.Mul;
import model.genetic.tree.functions.Sub;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.terminals.Constant;
import model.genetic.tree.terminals.Variable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saruman on 12.07.2017.
 */
public class GenePoolTest extends TestCase {

    ArrayList<String> functions = new ArrayList<String>();
    ArrayList<String> terminals = new ArrayList<String>();

    public void setUp() throws Exception {

        functions.add("Sum");
        functions.add("Mul");

        terminals.add("A");
        terminals.add("B");
    }

    public void testGenerateFullTree() throws Exception {
        GenePool genePool = new GenePool( terminals, functions );
        Tree testTree = genePool.generateFullTree(1);
        Tree testTree1 = genePool.generateFullTree(2);
        Tree testTree2 = genePool.generateFullTree(3);
        Tree testTree3 = genePool.generateFullTree(4);
        assertEquals(1, testTree.getNodeCount());
        assertEquals(3, testTree1.getNodeCount());
        assertEquals(7, testTree2.getNodeCount());
        assertEquals(15, testTree3.getNodeCount());
    }

    public void testGenerateGrowTree() throws Exception {
        GenePool genePool = new GenePool( terminals, functions );
        Tree testTree = genePool.generateGrowTree(5);
        assertTrue(testTree.getNodeCount()>0);
    }

    public void testCrossover() throws Exception {
        GenePool genePool = new GenePool( terminals, functions );
        Tree testTreeA = genePool.generateGrowTree(5);
        Tree testTreeB = genePool.generateFullTree(5);

        int nodeCount = testTreeA.getNodeCount();
        nodeCount += testTreeB.getNodeCount();

        genePool.crossover(testTreeA, testTreeB);

        int nodeCountCross = testTreeA.getNodeCount();
        nodeCountCross += testTreeB.getNodeCount();

        assertEquals(nodeCount, nodeCountCross);
    }

    public void testMutation() throws Exception {

    }

}