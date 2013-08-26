package test.function;

import junit.framework.TestCase;
import org.junit.Test;

/**
 *
 * If the second argument is positive or negative zero, then the result is 1.0.
 * If the second argument is 1.0, then the result is the same as the first argument.
 * If the second argument is NaN, then the result is NaN.
 * If the first argument is NaN and the second argument is nonzero, then the result is NaN.
 * If
 *   the absolute value of the first argument is greater than 1 and the second argument is positive infinity, or
 *   the absolute value of the first argument is less than 1 and the second argument is negative infinity,
 *   then the result is positive infinity.
 * If
 *   the absolute value of the first argument is greater than 1 and the second argument is negative infinity, or
 *   the absolute value of the first argument is less than 1 and the second argument is positive infinity,
 *   then the result is positive zero.
 * If the absolute value of the first argument equals 1 and the second argument is infinite, then the result is NaN.
 * If
 *   the first argument is positive zero and the second argument is greater than zero, or
 *   the first argument is positive infinity and the second argument is less than zero,
 *   then the result is positive zero.
 * If
 *   the first argument is positive zero and the second argument is less than zero, or
 *   the first argument is positive infinity and the second argument is greater than zero,
 *   then the result is positive infinity.
 * If
 *   the first argument is negative zero and the second argument is greater than zero but not a finite odd integer, or
 *   the first argument is negative infinity and the second argument is less than zero but not a finite odd integer,
 *   then the result is positive zero.
 * If
 *   the first argument is negative zero and the second argument is a positive finite odd integer, or
 *   the first argument is negative infinity and the second argument is a negative finite odd integer,
 *   then the result is negative zero.
 * If
 *   the first argument is negative zero and the second argument is less than zero but not a finite odd integer, or
 *   the first argument is negative infinity and the second argument is greater than zero but not a finite odd integer,
 *   then the result is positive infinity.
 * If
 *   the first argument is negative zero and the second argument is a negative finite odd integer, or
 *   the first argument is negative infinity and the second argument is a positive finite odd integer,
 *   then the result is negative infinity.
 * If the first argument is finite and less than zero
 *   if the second argument is a finite even integer, the result is equal to the result of raising the absolute value of the first argument to the power of the second argument
 *   if the second argument is a finite odd integer, the result is equal to the negative of the result of raising the absolute value of the first argument to the power of the second argument
 *   if the second argument is finite and not an integer, then the result is NaN.
 *   If both arguments are integers, then the result is exactly equal to the mathematical result of raising the first argument to the power of the second argument if that result can in fact be represented exactly as a double value.
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 25/08/13
 * Time: 01:41
 */
public class PowTest extends TestCase {
    @Test
    public void testEvaluate() throws Exception {

    }
}
