package unittest.model.genetic.tree.session;

import junit.framework.TestCase;
import model.genetic.tree.*;
import model.genetic.tree.functions.Div;
import model.genetic.tree.functions.Mul;
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
        IFunction div = new Div();
        IFunction mul = new Mul();


        functionHashMap.put(sum.getSymbol(), sum);
        functionHashMap.put(sub.getSymbol(), sub);
        functionHashMap.put(div.getSymbol(), div);
        functionHashMap.put(mul.getSymbol(), mul);

        functions.add(sum.getSymbol());
        functions.add(sub.getSymbol());
        functions.add(div.getSymbol());
        functions.add(mul.getSymbol());

        terminalHashMap.put("A", new Variable(3.0, "A"));
        terminalHashMap.put("B", new Variable(4.0, "B"));

        terminals.add("A");
        terminals.add("B");

        setPool = new SetPool(functionHashMap, terminalHashMap);
        genePool = new GenePool(terminals, functions);

        inputs.add(input);
        inputs.add(input2);

        this.trainingSet = new TrainingSet(inputs, target);
    }

    public void testRunFull() throws Exception {
        Population population =  PopulationGenerator.generate(PopulationGenerator.PopulationType.FULL,genePool,setPool,10,5);
        FitnessRunnable runnable = new FitnessRunnable(population, this.trainingSet);
        for(Tree tree: population.getTrees()){
            System.out.println(">" + tree.getTreeExpression());
        }
        runnable.populationError(population, this.trainingSet);
    }

    public void testRunGrow() throws Exception {
        Population population =  PopulationGenerator.generate(PopulationGenerator.PopulationType.GROW,genePool,setPool,10,5);
        FitnessRunnable runnable = new FitnessRunnable(population, this.trainingSet);
        for(Tree tree: population.getTrees()){
            System.out.println(">" + tree.getTreeExpression());
        }
        runnable.populationError(population, this.trainingSet);
    }

    public void testRunRamped() throws Exception {
        Population population =  PopulationGenerator.generate(PopulationGenerator.PopulationType.RAMPED_HALF_AND_HALF,genePool,setPool,10,5);
        FitnessRunnable runnable = new FitnessRunnable(population, this.trainingSet);
        for(Tree tree: population.getTrees()){
            System.out.println(">" + tree.getTreeExpression());
        }
        runnable.populationError(population, this.trainingSet);
    }

    public void testGetErrors() throws Exception {

    }

    public void testRegressionErrorSum() throws Exception {

    }

}