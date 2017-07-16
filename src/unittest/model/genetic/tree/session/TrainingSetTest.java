package unittest.model.genetic.tree.session;

import junit.framework.TestCase;
import model.genetic.tree.session.Input;
import model.genetic.tree.session.TrainingSet;

import java.util.ArrayList;

/**
 * Created by saruman on 17.07.2017.
 */
public class TrainingSetTest extends TestCase {

    private double[] inputSet = new double[]{ 1.0, 2.0 ,3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 };
    private String id = "A";
    private double[] inputSet2 = new double[]{ 1.0, 2.0 ,3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 };
    private String id2 = "B";

    private double[] target = new double[]{ 2.0, 4.0 ,6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 9.0, 20.0 };

    Input input = new Input(id, inputSet);
    Input input2 = new Input(id2, inputSet2);

    private ArrayList<Input> inputs = new ArrayList<Input>();

    public void setUp() throws Exception {
        super.setUp();
        inputs.add(input);
        inputs.add(input2);
    }

    public void testGetInputs() throws Exception {
        TrainingSet trainingSet = new TrainingSet(inputs, target);
        assertEquals(inputs, trainingSet.getInputs());

    }

    public void testGetTarget() throws Exception {
        TrainingSet trainingSet = new TrainingSet(inputs, target);
        assertEquals(target, trainingSet.getTarget());
    }

    public void testGetTargetValue() throws Exception {
        TrainingSet trainingSet = new TrainingSet(inputs, target);
        assertEquals(target[0], trainingSet.getTargetValue(0));
    }

    public void testGetSampeCount() throws Exception {
        TrainingSet trainingSet = new TrainingSet(inputs, target);
        assertEquals(target.length, trainingSet.getSampeCount());
    }

}