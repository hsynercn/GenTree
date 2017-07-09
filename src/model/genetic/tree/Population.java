package model.genetic.tree;

import java.util.ArrayList;

/**
 * Created by saruman on 10.07.2017.
 */
public class Population {

    public enum PopulationType{
        GROW,
        FULL,
        RAMPED_HALF_AND_HALF
    }

    private ArrayList<Tree> trees = null;
    private PopulationType populationType = null;
    private GeneHandler geneHandler = null;
    private SetPool setPool = null;

    public Population(PopulationType populationType, GeneHandler geneHandler,SetPool setPool, int populationSize, int depth){

        this.geneHandler = geneHandler;
        this.populationType = populationType;
        this.setPool = setPool;

        ArrayList<Tree> newPopulation = new ArrayList<Tree>();

        if(populationSize < 0){
            throw new IllegalArgumentException("Population initialization: Population size can't be smaller than 0.");
        }

        switch (populationType){
            case GROW:
                for(int i=0;i<populationSize;i++){
                    newPopulation.add(geneHandler.generateGrowTree(depth));
                }
                break;
            case FULL:
                for(int i=0;i<populationSize;i++){
                    newPopulation.add(geneHandler.generateFullTree(depth));
                }
                break;
            case RAMPED_HALF_AND_HALF:
                if(populationSize<2){
                    throw new IllegalArgumentException("Population initialization: Population size can't be smaller than" +
                            " 2 for ramped half and half.");
                }
                int i = 0;
                while(i<(populationSize/2)){
                    newPopulation.add(geneHandler.generateGrowTree(depth));
                    i++;
                }
                while(i<populationSize){
                    newPopulation.add(geneHandler.generateFullTree(depth));
                    i++;
                }
                break;
        }
        trees = newPopulation;
    }

    public ArrayList<Double> calculateTrees() throws Exception {
        ArrayList<Double> results = new ArrayList<Double>();
        for(Tree tree:this.trees){
            results.add(this.calculateTree(tree));
        }
        return results;
    }

    private double calculateTree(Tree tree) throws Exception {
        return this.calculateNode(tree.getRootNode());
    }

    private double calculateNode(Node node) throws Exception {

        if( node.getNodeType() == Node.NodeType.LEAF )
        {
            return this.setPool.getITerminal(node.getiTerminalDI()).getValue();
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
