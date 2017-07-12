package unittest.model.genetic.tree.session;

import junit.framework.TestCase;
import model.genetic.tree.GenePool;
import model.genetic.tree.IFunction;
import model.genetic.tree.ITerminal;
import model.genetic.tree.SetPool;
import model.genetic.tree.functions.Div;
import model.genetic.tree.functions.Mul;
import model.genetic.tree.functions.Sub;
import model.genetic.tree.functions.Sum;
import model.genetic.tree.session.Population;
import model.genetic.tree.session.PopulationGenerator;
import model.genetic.tree.terminals.Constant;
import model.genetic.tree.terminals.Variable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by saruman on 13.07.2017.
 */
public class PopulationTest extends TestCase {

    private HashMap<String, IFunction> functionHashMap = new HashMap<String, IFunction>();
    private HashMap<String, ITerminal> terminalHashMap = new HashMap<String, ITerminal>();
    private ArrayList<String> functions = new ArrayList<String>();
    private ArrayList<String> terminals = new ArrayList<String>();
    private SetPool setPool;
    private GenePool genePool;

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
    }

    public void testCalculateTrees() throws Exception {
        Population population = PopulationGenerator.generate(PopulationGenerator.PopulationType.FULL,genePool,setPool,10,5);
        int resSize = population.calculateTrees().length;
        assertEquals(10,resSize);
    }

    public void testGetPopulationSize() throws Exception {
        int size = PopulationGenerator.generate(PopulationGenerator.PopulationType.FULL,genePool,setPool,10,5).getPopulationSize();
        assertEquals(10, size);
    }
}