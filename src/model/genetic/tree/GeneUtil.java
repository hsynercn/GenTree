package model.genetic.tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by saruman on 3.07.2017.
 */
public class GeneUtil {

    public enum SelectionType{
        ALL_EQUAL,
        INCREMENTAL_REVERSE
    }


    private ArrayList<ITerminal> terminals = new ArrayList<ITerminal>();
    private ArrayList<IFunction> functions = new ArrayList<IFunction>();
    private Random rand = new Random();
    private double mutationChange = 0.5;

    public GeneUtil(ArrayList<ITerminal> terminals, ArrayList<IFunction> functions) {
        this.terminals = terminals;
        this.functions = functions;
    }

    public Tree generateFullTree(int depth){
        return new Tree( this.generateFullTree(depth, null) );
    }

    private Node generateFullTree(int depth, Node parent)
    {
        if( depth <= 0 )
        {
            throw new IllegalArgumentException("Full tree generation: Invalid tree depth:"+depth);
        }
        if( depth == 1 )
        {
            return new Node(parent, this.getRandomTerminal());
        }

        Node node = new Node(parent, this.getRandomFunction());
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
            throw new IllegalArgumentException("Grow tree generation: Invalid tree depth:"+depth);
        }
        if( depth == 1 )
        {
            return new Node(parent, this.getRandomTerminal());
        }

        int selection = rand.nextInt( this.terminals.size() + this.functions.size() );

        if(selection<this.terminals.size()){
            return new Node(parent, this.getRandomTerminal());
        }else{

            Node node = new Node(parent, this.getRandomFunction());
            Node leftNode = generateGrowTree(depth-1, node);
            Node rightNode = generateGrowTree(depth-1, node);
            node.setLeftChild(leftNode);
            node.setRightChild(rightNode);
            return node;
        }
    }

    private IFunction getRandomFunction()
    {
        return this.functions.get(rand.nextInt(this.functions.size()));
    }
    private ITerminal getRandomTerminal()
    {
        return this.terminals.get(rand.nextInt(this.terminals.size()));
    }

    public ArrayList<Tree> rampedHalfAndHalf(int populationSize, int maxDepth){
        ArrayList<Tree> trees = new ArrayList<Tree>();

        for (int i=0;i<populationSize;i++){
            if( rand.nextInt()%2 == 0 ){
                trees.add(this.generateFullTree(maxDepth));
            }
            else {
                trees.add(this.generateGrowTree(maxDepth));
            }
        }
        return trees;
    }

    /**
     * Crossovers two trees, crossover points are selected randomly. Randomly selected leaf path is used for point
     * selection. Path's all nodes have same selection probability. Thus shallow trees' root selection probability is
     * higher than deeper trees.
     * @param a a tree
     * @param b another tree :D
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
        sectionB.setParent(tempParNodeA.getParent());

    }

    public Node getRandomLocation(Tree tree, SelectionType selectionType){
        return this.getRandomLocation(tree.getRootNode(), selectionType);
    }

    /**
     * Selects a random node from genetic tree, can change the random node selection method with SelectionType enum.
     * @param node  root node of the genetic tree
     * @param selectionType effects the random node selection process
     * @return  selects the randomly selected node from tree
     */
    private Node getRandomLocation(Node node, SelectionType selectionType){

        int pathDepth = 0;
        while(node.getNodeType() != NodeType.LEAF) {
            if ( rand.nextInt()%2 == 0 ){
                node = node.getLeftChild();
            }else {
                node = node.getRightChild();
            }
            pathDepth++;
        }

        int selected = 0;
        switch (selectionType){
            case ALL_EQUAL:
                selected = 0;
                if(pathDepth != 0) {
                    selected = rand.nextInt(pathDepth);
                }
                break;
            case INCREMENTAL_REVERSE:
                selected = pathDepth;
                while( ( this.rand.nextDouble() < this.mutationChange ) && (selected > 0) ){
                    selected--;
                }
                break;
        }

        while ( ( selected > 0 ) && ( node.getParent() != null ) ) {
            node = node.getParent();
            selected--;
        }
        return node;
    }

    public void mutation(Tree tree){
        //TODO reverse selected added, use it
    }

}
