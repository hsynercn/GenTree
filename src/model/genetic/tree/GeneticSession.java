package model.genetic.tree;

import java.util.ArrayList;

/**
 * Created by saruman on 6.07.2017.
 */
public class GeneticSession {

    private ArrayList<Node> population = null;
    private double errorLimit;
    private int maxIteration;

    public GeneticSession(ArrayList<Node> initialPopulation, double errorLimit, int maxIteration) {
        this.population = initialPopulation;
        this.errorLimit = errorLimit;
        this.maxIteration = maxIteration;
    }

    //TODO Need to implement GeneUtil first, later add life cycle

}
