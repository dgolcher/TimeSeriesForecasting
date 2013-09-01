package test.function;

import geneticProgramming.functions.function.trigonometric.ArcSine;
import geneticProgramming.functions.terminal.Constant;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * If the argument is NaN or its absolute value is greater than 1, then the result is NaN.
 * If the argument is zero, then the result is a zero with the same sign as the argument.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 25/08/13
 * Time: 01:40
 */
public class ArcSineTest extends TestCase {

    double[] args = new double[0];

    @Test
    public void testEvaluateWithNaNParam() throws Exception
    {
        ArcSine arcSine = new ArcSine(
            new Constant(Double.NaN)
        );

        assertEquals(arcSine.evaluate(args), ArcSine.BAD_FITNESS_VALUE);
    }

    @Test
    public void testEvaluateWithParamGreaterThanOne() throws Exception
    {
        ArcSine arcSine = new ArcSine(
            new Constant(2)
        );

        assertEquals(arcSine.evaluate(args), ArcSine.BAD_FITNESS_VALUE);
    }
}
