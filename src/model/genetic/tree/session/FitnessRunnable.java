package model.genetic.tree.session;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by saruman on 6.07.2017.
 */
public class FitnessRunnable implements Runnable {

    private Population population;
    private TrainingSet trainingSet;
    private boolean resultReady;
    private double[] errors = null;

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

    public double[] getErrors() {
        return errors;
    }

    public double[] populationError(Population population, TrainingSet trainingSet){
        double[] errros = new double[population.getPopulationSize()];
        double[] tempResults = new double[population.getPopulationSize()];
        int sampeCount = trainingSet.getSampeCount();
        int i = 0;
        while (i<sampeCount){
            this.updateInputValues(population,trainingSet,i);

            try {
                tempResults = population.calculateTrees();
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (int j = 0; j<errros.length; j++){
                errros[j] = errros[j] + ( trainingSet.getTargetValue(i) - tempResults[j] );
            }
            i++;
        }

        return errros;
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
