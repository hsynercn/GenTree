package model.genetic.tree.session;

import model.genetic.tree.GenePool;
import model.genetic.tree.SetPool;
import model.genetic.tree.Tree;
import model.genetic.tree.error.ErrorComparator;
import model.genetic.tree.error.TreeError;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by saruman on 11.07.2017.
 */
public class PopulationController {

    public enum PopulationType{
        GROW,
        FULL,
        RAMPED_HALF_AND_HALF
    }

    public enum SelectionType{
        RANK,
        ROULETTE_WHEEL
    }

    public  static Population generate(PopulationType populationType, GenePool genePool, SetPool setPool, int populationSize, int depth){

        ArrayList<Tree> individuals = new ArrayList<Tree>();

        if(populationSize < 0){
            throw new IllegalArgumentException("Population initialization: Population size can't be smaller than 0.");
        }

        switch (populationType){
            case GROW:
                for(int i=0;i<populationSize;i++){
                    individuals.add(genePool.generateGrowTree(depth));
                }
                break;
            case FULL:
                for(int i=0;i<populationSize;i++){
                    individuals.add(genePool.generateFullTree(depth));
                }
                break;
            case RAMPED_HALF_AND_HALF:
                if(populationSize<2){
                    throw new IllegalArgumentException("Population initialization: Population size can't be smaller than" +
                            " 2 for ramped half and half.");
                }
                int i = 0;
                while(i<(populationSize/2)){
                    individuals.add(genePool.generateGrowTree(depth));
                    i++;
                }
                while(i<populationSize){
                    individuals.add(genePool.generateFullTree(depth));
                    i++;
                }
                break;
        }
        return new Population(individuals, setPool);
    }

    public  static Population select(TreeError[] treeErrors, GenePool genePool, SetPool setPool, SelectionType selectionType, int nextGenerationSize){
        ArrayList<Tree> individuals = new ArrayList<Tree>();

        TreeError[] sortedTreeErrors = PopulationController.sortError(treeErrors);

        Tree[] sortedTrees = new Tree[sortedTreeErrors.length];

        for (int i=0;i<sortedTreeErrors.length;i++){
            sortedTrees[i] = sortedTreeErrors[i].getTree();
        }

        ArrayList<Tree> nextGenParents = new ArrayList<Tree>();
        ArrayList<Tree> nextGeneration = new ArrayList<Tree>();

        switch (selectionType){
            case RANK:
                nextGenParents = PopulationController.rankSelect(sortedTrees, nextGenerationSize);

        }

        for(int i=0;i<nextGenParents.size();i++)
        {
            for(int j=i;j<nextGenParents.size();j++){
                Tree a = nextGenParents.get(i).clone();
                Tree b = nextGenParents.get(i).clone();
                genePool.crossover( a , b );
                nextGeneration.add(a);
                nextGeneration.add(b);
            }
        }
        

        //TODO mutation

        return new Population(individuals, setPool);
    }

    public static TreeError[] sortError(TreeError[] treeErrors){

        TreeError[] treeErrorList = treeErrors;
        Arrays.sort( treeErrorList, new ErrorComparator() );
        return treeErrorList;
    }

    public static ArrayList<Tree> rankSelect(Tree[] trees, int selectCount){

        if(trees.length<selectCount){
            throw new IllegalArgumentException("Rank Selection: Selection can't exceed");
        }

        ArrayList<Tree> treeArrayList = new ArrayList<Tree>();

        int[] selectionPortitions = new int[trees.length];
        int sum = trees.length * (trees.length - 1) / 2;

        //0 location is the most successful, it has largest portion
        for(int i=0;i<selectionPortitions.length;i++){
            selectionPortitions[i] = selectionPortitions.length - i + selectionPortitions[i];
        }

        int selectedCount = 0;
        while( selectedCount < selectCount){
            Random rand = new Random();
            int randSel = rand.nextInt(sum);
            int randSelLocator = 0;
            while(randSel>selectionPortitions[randSelLocator]){
                randSelLocator++;
            }
            treeArrayList.add(trees[randSelLocator]);
        }

        return treeArrayList;
    }

}
