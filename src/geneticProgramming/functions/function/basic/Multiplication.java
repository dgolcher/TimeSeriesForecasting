package geneticProgramming.functions.function.basic;

import geneticProgramming.functions.function.BinaryNode;
import geneticProgramming.functions.function.complex.Pow;
import geneticProgramming.functions.terminal.Constant;
import geneticProgramming.functions.Node;

/**
 * Multiplication operator.
 *
 * This is an implementation of multiplication operator.
 *
 * This class must sum two values. This two parameters must be  arithmetic classes' instances.
 *
 * This class will evaluate numeric results.
 *
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 18/06/13
 * Time: 23:25
 */
public class Multiplication extends BinaryNode
{

    public static final String MULTIPLICATION_SYMBOL = "*";

    public Multiplication(Node left, Node right)
    {
        super(left, right, Multiplication.MULTIPLICATION_SYMBOL);
    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any child nodes) and return the fitness
     * value of this.
     *
     * @param programParameters Program parameters, used by this node or its children.
     * @return Returns a double value representing this node's fitness value.
     */
    @Override
    public double evaluate(double[] programParameters)
    {
        final double returnValue = this.left.evaluate(programParameters) * this.right.evaluate(programParameters);
        return (Double.isNaN(returnValue) || Double.isInfinite(returnValue)) ? Double.MAX_VALUE : returnValue;
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify()
    {
        Node simplifiedLeft  = this.left.simplify();
        Node simplifiedRight = this.right.simplify();

        if (simplifiedLeft instanceof Constant && simplifiedLeft.evaluate(NO_ARGS) == 1) {
            return simplifiedRight;
        }

        if (simplifiedRight instanceof Constant && simplifiedRight.evaluate(NO_ARGS) == 1) {
            return simplifiedLeft;
        }

        if ((simplifiedLeft instanceof Constant && simplifiedLeft.evaluate(NO_ARGS) == 0) ||
            (simplifiedRight instanceof Constant && simplifiedRight.evaluate(NO_ARGS) == 0)) {
            return new Constant(0);
        }

        if (simplifiedLeft == simplifiedRight) {
            return new Pow(simplifiedRight, new Constant(2));
        }

        // Case both of them, left and right nodes are constants, it can be simplified to its result.
        if (simplifiedLeft instanceof Constant && simplifiedRight instanceof Constant) {
            return new Constant(simplifiedLeft.evaluate(NO_ARGS) * simplifiedRight.evaluate(NO_ARGS));
        } else if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new Multiplication(simplifiedLeft, simplifiedRight);
        } else {
            return this;
        }
    }

}
