package model.genetic.tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by saruman on 3.07.2017.
 */
public class GeneUtil {

    private ArrayList<ITerminal> terminals = new ArrayList<ITerminal>();
    private ArrayList<IFunction> functions = new ArrayList<IFunction>();
    private Random rand = new Random();

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
            return new Node(this.getRandomTerminal());
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
            return new Node(this.getRandomTerminal());
        }

        int selection = rand.nextInt( this.terminals.size() + this.functions.size() );

        if(selection<this.terminals.size()){
            return new Node(this.getRandomTerminal());
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

    public void crossover(Tree a, Tree b){
        Node sectionA = this.getRandomLocation(a.getRootNode());
        Node sectionB = this.getRandomLocation(b.getRootNode());

        if ( sectionA == a.getRootNode() ){
            a.setRootNode(sectionB);
        }
        if ( sectionB == b.getRootNode() ){
            b.setRootNode(sectionA);
        }

        sectionA.getParent().replaceChild(sectionA, sectionB);
        sectionB.getParent().replaceChild(sectionB, sectionA);

        Node tempParNodeA = sectionA.getParent();
        sectionA.setParent(sectionB.getParent());
        sectionB.setParent(tempParNodeA.getParent());

    }

    private Node getRandomLocation(Node node){

        int pathDepth = 0;
        while(node.getNodeType() != NodeType.LEAF) {
            if ( rand.nextInt()%2 == 0 ){
                node = node.getLeftChild();
            }else {
                node = node.getRightChild();
            }
            pathDepth++;
        }

        int selected = rand.nextInt()%pathDepth;

        while ( selected > -1 ) {
            node = node.getParent();
            selected--;
        }
        return node;
    }

}
