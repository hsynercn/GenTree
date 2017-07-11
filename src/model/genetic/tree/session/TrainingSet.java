package model.genetic.tree.session;

import java.util.ArrayList;

/**
 * Created by saruman on 11.07.2017.
 */
public class TrainingSet {
    ArrayList<Input> inputs = null;
    private ArrayList<Double> target = null;

    public TrainingSet(ArrayList<Input> inputs, ArrayList<Double> target) {
        this.inputs = inputs;
        this.target = target;
    }

    public ArrayList<Input> getInputs() {
        return inputs;
    }

    public ArrayList<Double> getTarget() {
        return target;
    }

    public int getSampeCount(){
        return this.target.size();
    }

}
