package ut.model.genetic.tree.functions;

import junit.framework.TestCase;
import model.genetic.tree.functions.Sub;

/**
 * Created by saruman on 2.07.2017.
 */
public class SubTest extends TestCase {
    public void testExecute() throws Exception {
        Sub mySub = new Sub();

        assertEquals(8.0, mySub.execute(10.0, 2.0));

    }

    public void testGetSymbol() throws Exception {
        Sub mySub = new Sub();

        assertEquals("Sub", mySub.getSymbol());
    }

}