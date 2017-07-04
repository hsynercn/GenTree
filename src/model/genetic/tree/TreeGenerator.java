package model.genetic.tree;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by saruman on 3.07.2017.
 */
public class TreeGenerator {

    private ArrayList<ITerminal> terminals = new ArrayList<ITerminal>();
    private ArrayList<IFunction> functions = new ArrayList<IFunction>();
    private Random rand = new Random();

    public TreeGenerator(ArrayList<ITerminal> terminals, ArrayList<IFunction> functions) {
        this.terminals = terminals;
        this.functions = functions;
    }

    public Node generateFullTree(int depth){
        return this.generateFullTree(depth, null);
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

    public Node generateGrowTree(int depth){
        return this.generateGrowTree(depth, null);
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

    public ArrayList<Node> rampedHalfAndHalf(int populationSize, int maxDepth){
        ArrayList<Node> nodes = new ArrayList<Node>();

        for (int i=0;i<populationSize;i++){
            if( rand.nextInt()%2 == 0 ){
                nodes.add(this.generateFullTree(maxDepth));
            }
            else {
                nodes.add(this.generateGrowTree(maxDepth));
            }
        }
        return nodes;
    }


}
