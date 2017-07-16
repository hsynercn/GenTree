package model.genetic.tree.session;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.ArrayList;

/**
 * Created by saruman on 11.07.2017.
 */
public class TrainingSet {
    ArrayList<Input> inputs = null;
    private double[] target = null;

    public TrainingSet(ArrayList<Input> inputs, double[] target) {
        for (Input temp: inputs){
            if(target.length != temp.getValues().length){
                throw new IllegalArgumentException("TrainingSet: Can't construct training set, input sets dimensoins are incompitable.");
            }
        }
        this.inputs = inputs;
        this.target = target;
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    public double[] getTarget() {
        return target;
    }

    public double getTargetValue(int index) {
        return target[index];
    }

    public int getSampeCount(){
        return this.target.length;
    }

}
