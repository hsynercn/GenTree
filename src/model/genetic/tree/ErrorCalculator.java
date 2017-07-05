package model.genetic.tree;

import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by saruman on 6.07.2017.
 */
public class ErrorCalculator {

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
