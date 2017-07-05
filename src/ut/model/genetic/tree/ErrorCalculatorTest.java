package ut.model.genetic.tree;

import junit.framework.TestCase;
import model.genetic.tree.ErrorCalculator;

import java.util.ArrayList;

/**
 * Created by saruman on 6.07.2017.
 */
public class ErrorCalculatorTest extends TestCase {

    private ArrayList<Double> target = new ArrayList<Double>();
    private ArrayList<Double> result = new ArrayList<Double>();

    public void setUp() throws Exception {
        super.setUp();
        target.add(1.0);
        target.add(2.0);
        target.add(3.0);

        result.add(0.0);
        result.add(1.0);
        result.add(2.0);
    }

    public void testRegressionErrorSum() throws Exception {
        double errorSum = ErrorCalculator.regressionErrorSum(this.result, this.target);
        assertEquals(3.0,errorSum);
    }

}