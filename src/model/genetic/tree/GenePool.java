package model.genetic.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by saruman on 3.07.2017.
 */
public class GenePool {

    public enum SelectionType{
        ALL_EQUAL,
        INCREMENTAL_REVERSE
    }

    private Random rand;
    private double mutationChange = 0.5;
    private int mutationMaxDepth = 5;
    private ArrayList<String> terminalIDS = null;
    private ArrayList<String> functionIDS = null;


    public GenePool(ArrayList<String> terminalIDS, ArrayList<String> functionIDS) {
        this.terminalIDS = terminalIDS;
        this.functionIDS = functionIDS;
        rand = new Random();
    }

    public Tree generateFullTree(int depth){
        return new Tree( this.generateFullTree(depth, null) );
    }

    private Node generateFullTree(int depth, Node parent)
    {
        if( depth <= 0 )
        {
            throw new IllegalArgumentException("Full UT.model.genetic.tree generation: Invalid UT.model.genetic.tree depth:"+depth);
        }
        if( depth == 1 )
        {
            return new Node(parent, this.getRandomTerminal(), Node.NodeType.LEAF);
        }

        Node node = new Node(parent, this.getRandomFunction(), Node.NodeType.NODE);
        Node leftNode = generateFullTree(depth-1, node);
        Node rightNode = generateFullTree(depth-1, node);
        node.setLeftChild(leftNode);
        node.setRightChild(rightNode);
        return node;
    }

    public Tree generateGrowTree(int depth){
        return new Tree( this.generateGrowTree(depth, null) );
    }

    private Node generateGrowTree(int depth, Node parent){

        if( depth <= 0 )
        {
            throw new IllegalArgumentException("Grow UT.model.genetic.tree generation: Invalid UT.model.genetic.tree depth:"+depth);
        }
        if( depth == 1 )
        {
            return new Node(parent, this.getRandomTerminal(), Node.NodeType.LEAF);
        }

        int selection = rand.nextInt( this.terminalIDS.size() + this.terminalIDS.size() );

        if(selection<this.terminalIDS.size()){
            return new Node(parent, this.getRandomTerminal(), Node.NodeType.LEAF);
        }else{

            Node node = new Node(parent, this.getRandomFunction(), Node.NodeType.NODE);
            Node leftNode = generateGrowTree(depth-1, node);
            Node rightNode = generateGrowTree(depth-1, node);
            node.setLeftChild(leftNode);
            node.setRightChild(rightNode);
            return node;
        }
    }

    private String getRandomFunction()
    {
        return this.functionIDS.get(rand.nextInt(this.functionIDS.size()));
    }
    private String getRandomTerminal()
    {
        return this.terminalIDS.get(rand.nextInt(this.terminalIDS.size()));
    }

    /**
     * Crossovers two trees, crossover points are selected randomly. Randomly selected leaf path is used for point
     * selection. Path's all nodes have same selection probability. Thus shallow trees' root selection probability is
     * higher than deeper trees.
     * @param a a UT.model.genetic.tree
     * @param b another UT.model.genetic.tree :D
     */
    public void crossover(Tree a, Tree b){
        Node sectionA = this.getRandomLocation(a.getRootNode(), SelectionType.ALL_EQUAL);
        Node sectionB = this.getRandomLocation(b.getRootNode(), SelectionType.ALL_EQUAL);

        if ( sectionA == a.getRootNode() ){
            a.setRootNode(sectionB);
        }else{
            sectionA.getParent().replaceChild(sectionA, sectionB);
        }

        if ( sectionB == b.getRootNode() ){
            b.setRootNode(sectionA);
        }else{
            sectionB.getParent().replaceChild(sectionB, sectionA);
        }

        Node tempParNodeA = sectionA.getParent();
        sectionA.setParent(sectionB.getParent());
        sectionB.setParent(tempParNodeA);

    }

    private Node getRandomLocation(Tree tree, SelectionType selectionType){
        return this.getRandomLocation(tree.getRootNode(), selectionType);
    }

    /**
     * Selects a random node from genetic UT.model.genetic.tree, can change the random node selection method with SelectionType enum.
     * @param node  root node of the genetic UT.model.genetic.tree
     * @param selectionType effects the random node selection process
     * @return  selects the randomly selected node from UT.model.genetic.tree
     */
    private Node getRandomLocation(Node node, SelectionType selectionType){

        int pathDepth = 0;
        while(node.getNodeType() != Node.NodeType.LEAF) {
            if ( rand.nextInt()%2 == 0 ){
                node = node.getLeftChild();
            }else {
                node = node.getRightChild();
            }
            pathDepth++;
        }

        int selected = pathDepth;
        switch (selectionType){
            case ALL_EQUAL:
                if(pathDepth != 0) {
                    selected = rand.nextInt(pathDepth);
                }
                break;
            case INCREMENTAL_REVERSE:
                while( ( this.rand.nextDouble() < this.mutationChange ) && (selected > 0) ){
                    selected--;
                }
                break;
        }

        int reverseLevel = pathDepth;
        while ( ( reverseLevel > selected ) && ( node.getParent() != null ) ) {
            node = node.getParent();
            reverseLevel--;
        }
        return node;
    }

    public void mutation(Tree tree){

        Node mutationPoint = this.getRandomLocation(tree, SelectionType.INCREMENTAL_REVERSE);
        if( mutationPoint == tree.getRootNode()){
            tree.setRootNode(this.generateGrowTree(this.mutationMaxDepth, null));
        }else {
            Node mutatedPart = this.generateGrowTree(this.mutationMaxDepth, null);
            mutationPoint.getParent().replaceChild(mutationPoint, mutatedPart);
            mutatedPart.setParent(mutationPoint.getParent());
            mutationPoint.setParent(null);
        }
    }
}
