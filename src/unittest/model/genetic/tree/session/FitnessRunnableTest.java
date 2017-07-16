package unittest.model.genetic.tree.session;

import junit.framework.TestCase;
import model.genetic.tree.GenePool;
import model.genetic.tree.IFunction;
import model.genetic.tree.ITerminal;
import model.genetic.tree.SetPool;
import model.genetic.tree.functions.Sub;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.session.*;
import model.genetic.tree.terminals.Variable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saruman on 17.07.2017.
 */
public class FitnessRunnableTest extends TestCase {

    private HashMap<String, IFunction> functionHashMap = new HashMap<String, IFunction>();
    private HashMap<String, ITerminal> terminalHashMap = new HashMap<String, ITerminal>();
    private ArrayList<String> functions = new ArrayList<String>();
    private ArrayList<String> terminals = new ArrayList<String>();
    private SetPool setPool;
    private GenePool genePool;
    private Population population;

    private double[] inputSet = new double[]{ 1.0, 2.0 ,3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 };
    private String id = "A";
    private double[] inputSet2 = new double[]{ 1.0, 2.0 ,3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0 };
    private String id2 = "B";
    private double[] target = new double[]{ 2.0, 4.0 ,6.0, 8.0, 10.0, 12.0, 14.0, 16.0, 9.0, 20.0 };

    Input input = new Input(id, inputSet);
    Input input2 = new Input(id2, inputSet2);
    private ArrayList<Input> inputs = new ArrayList<Input>();

    private TrainingSet trainingSet;

    public void setUp() throws Exception {
        super.setUp();
        IFunction sum = new Sum();
        IFunction sub = new Sub();

        functionHashMap.put(sum.getSymbol(), sum);
        functionHashMap.put(sub.getSymbol(), sub);

        functions.add(sum.getSymbol());
        functions.add(sub.getSymbol());

        terminalHashMap.put("A", new Variable(3.0, "A"));
        terminalHashMap.put("B", new Variable(4.0, "B"));

        terminals.add("A");
        terminals.add("B");

        setPool = new SetPool(functionHashMap, terminalHashMap);
        genePool = new GenePool(terminals, functions);

        this.population = PopulationGenerator.generate(PopulationGenerator.PopulationType.FULL,genePool,setPool,10,5);

        inputs.add(input);
        inputs.add(input2);

        this.trainingSet = new TrainingSet(inputs, target);
    }

    public void testRun() throws Exception {
        FitnessRunnable runnable = new FitnessRunnable(this.population, this.trainingSet);
        runnable.populationError(this.population, this.trainingSet);
    }

    public void testGetErrors() throws Exception {

    }

    public void testRegressionErrorSum() throws Exception {

    }

}