package model.genetic.tree.session;

import model.genetic.tree.GenePool;
import model.genetic.tree.SetPool;
import model.genetic.tree.Tree;

import java.util.ArrayList;

/**
 * Created by saruman on 11.07.2017.
 */
public class PopulationGenerator {

    public enum PopulationType{
        GROW,
        FULL,
        RAMPED_HALF_AND_HALF
    }

    public  Population generate(PopulationType populationType, GenePool genePool, SetPool setPool, int populationSize, int depth){

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
}
