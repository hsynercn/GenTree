package model.genetic.tree.session;

import model.genetic.tree.Node;
import model.genetic.tree.SetPool;
import model.genetic.tree.Tree;

import java.util.ArrayList;

/**
 * Created by saruman on 10.07.2017.
 */
public class Population {

    private ArrayList<Tree> trees = null;
    private SetPool setPool = null;

    private double[] resultTable;

    public Population(ArrayList<Tree> trees, SetPool setPool) {
        this.trees = trees;
        this.setPool = setPool;
        resultTable = new double[trees.size()];
    }

    public double[] calculateTrees() throws Exception {
        int i = 0;
        for(Tree tree:this.trees){
            this.resultTable[i] = this.calculateTree(tree);
            i++;
        }
        return resultTable;
    }

    private double calculateTree(Tree tree) throws Exception {
        return this.calculateNode(tree.getRootNode());
    }

    public int getPopulationSize(){
        return this.trees.size();
    }

    public SetPool getSetPool() {
        return setPool;
    }

    private double calculateNode(Node node) throws Exception {

        if( node.getNodeType() == Node.NodeType.LEAF )
        {
            return this.setPool.getITerminal(node.getiTerminalID()).getValue();
        }
        else if( node.getNodeType() == Node.NodeType.NODE )
        {
            return this.setPool.getIFunction(node.getiFunctionID()).execute(this.calculateNode(node.getLeftChild()), this.calculateNode(node.getRightChild()));
        }
        else
        {
            throw new Exception("Invalid node state, can`t calculate");
        }
    }
}
