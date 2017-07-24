package model.genetic.tree.session;

import model.genetic.tree.error.TreeError;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by saruman on 6.07.2017.
 */
public class FitnessRunnable implements Runnable {

    private Population population;
    private TrainingSet trainingSet;
    private boolean resultReady;
    private TreeError[] errors = null;

    public FitnessRunnable(Population population, TrainingSet trainingSet) {
        this.population = population;
        this.trainingSet = trainingSet;
        this.resultReady = false;
        double[] errros = new double[population.getPopulationSize()];
    }

    @Override
    public void run() {
        this.resultReady = false;
        this.errors = this.populationError(this.population,this.trainingSet);
        this.resultReady = true;
    }

    public TreeError[] getErrors() {
        return errors;
    }

    public TreeError[] populationError(Population population, TrainingSet trainingSet){

        double[] errors = new double[population.getPopulationSize()];
        double[] tempResults = new double[population.getPopulationSize()];
        TreeError[] treeErrors = new TreeError[population.getPopulationSize()];

        int sampeCount = trainingSet.getSampeCount();
        int i = 0;
        while (i<sampeCount){
            this.updateInputValues(population,trainingSet,i);

            try {
                tempResults = population.calculateTrees();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int j = 0; j<errors.length; j++){
                errors[j] = errors[j] + ( trainingSet.getTargetValue(i) - tempResults[j] );
            }
            i++;
        }

        for( int j=0; j<population.getPopulationSize(); j++){
            treeErrors[j] = new TreeError(population.getTrees().get(j) , errors[j]);
        }

        return treeErrors;
    }

    private void updateInputValues(Population population, TrainingSet trainingSet, int valueIndex){
        boolean check = true;
        for(Input input: trainingSet.getInputs()){
                check = population.getSetPool().updateITerminalValue(input.getiTerminalId(), input.getValues()[valueIndex]);
                if( check == false){
                    throw new IllegalArgumentException("Fitness calculation: Can't update the terminal value, invalid terminal id.");
                }
        }
    }

    public static double regressionErrorSum(ArrayList<Double> results, ArrayList<Double> target){

        double error = 0.0;

        if(results.size() != target.size())
        {
            throw new IllegalArgumentException("Regression error, can't calculate the error.");
        }
        for (int i=0 ; i<results.size();i++){
            error += abs(results.get(i) - target.get(i));
        }
        return error;
    }

}
