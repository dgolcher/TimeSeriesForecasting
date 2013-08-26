package test.function;

import geneticProgramming.functions.function.trigonometric.ArcSine;
import geneticProgramming.functions.function.trigonometric.Cosine;
import geneticProgramming.functions.terminal.Constant;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * If the argument is NaN or an infinity, then the result is NaN.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 25/08/13
 * Time: 01:39
 */
public class CosineTest extends TestCase {

    double[] args = new double[0];

    @Test
    public void testEvaluateWithParamNaN() throws Exception
    {
        Cosine cosine = new Cosine(
            new Constant(Double.NaN)
        );

        assertEquals(cosine.evaluate(args), Double.MAX_VALUE);
    }

    @Test
    public void testEvaluateWithParamInfinite() throws Exception
    {
        Cosine cosine = new Cosine(
                new Constant(Double.POSITIVE_INFINITY)
        );

        assertEquals(cosine.evaluate(args), Double.MAX_VALUE);
    }

}
