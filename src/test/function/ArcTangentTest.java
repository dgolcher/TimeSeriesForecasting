package test.function;

import geneticProgramming.functions.function.trigonometric.ArcTangent;
import geneticProgramming.functions.terminal.Constant;
import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * If the argument is NaN, then the result is NaN.
 * If the argument is zero, then the result is a zero with the same sign as the argument.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 25/08/13
 * Time: 01:39
 */
public class ArcTangentTest extends TestCase {
    double[] args = new double[0];

    @Test
    public void testEvaluateWithNaNParam() throws Exception
    {
        ArcTangent arcTangent = new ArcTangent(
                new Constant(Double.NaN)
        );

        assertEquals(arcTangent.evaluate(args), Double.MAX_VALUE);
    }

    @Test
    public void testEvaluateWithParamZero() throws Exception
    {
        ArcTangent arcTangent = new ArcTangent(
                new Constant(0)
        );

        assertEquals(arcTangent.evaluate(args), 0.0);
    }

}
