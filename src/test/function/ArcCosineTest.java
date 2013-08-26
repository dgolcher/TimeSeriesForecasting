package test.function;

import geneticProgramming.functions.function.trigonometric.ArcCosine;
import geneticProgramming.functions.terminal.Constant;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * If the argument is NaN or its absolute value is greater than 1, then the result is NaN.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 25/08/13
 * Time: 01:40
 */
public class ArcCosineTest extends TestCase {

    double[] args = new double[0];

    @Test
    public void testEvaluateWithNaNParam() throws Exception
    {
        ArcCosine arcCosine = new ArcCosine(
            new Constant(Double.NaN)
        );

        assertEquals(arcCosine.evaluate(args), Double.MAX_VALUE);
    }

    @Test
    public void testEvaluateWithParamGreaterThanOne() throws Exception
    {
        ArcCosine arcCosine = new ArcCosine(
            new Constant(2)
        );

        assertEquals(arcCosine.evaluate(args), Double.MAX_VALUE);
    }

}
