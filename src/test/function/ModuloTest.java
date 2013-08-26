package test.function;

import geneticProgramming.functions.function.complex.Modulo;
import geneticProgramming.functions.function.trigonometric.Cosine;
import geneticProgramming.functions.terminal.Constant;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 25/08/13
 * Time: 01:41
 */
public class ModuloTest extends TestCase {

    double[] args = new double[0];

    @Test
    public void testEvaluateWithParamNaN() throws Exception {
        Modulo modulo = new Modulo(
            new Constant(Double.NaN),
            new Constant(Double.NaN)
        );

        assertEquals(modulo.evaluate(args), Double.MAX_VALUE);
    }
}
