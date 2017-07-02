package ut.model.genetic.tree.functions;

import junit.framework.TestCase;
import model.genetic.tree.functions.Mul;

/**
 * Created by saruman on 2.07.2017.
 */
public class MulTest extends TestCase {
    public void testExecute() throws Exception {
        Mul myMul = new Mul();

        assertEquals(20.0, myMul.execute(10.0, 2.0));

    }

    public void testGetSymbol() throws Exception {
        Mul myMul = new Mul();

        assertEquals("Mul", myMul.getSymbol());
    }

}