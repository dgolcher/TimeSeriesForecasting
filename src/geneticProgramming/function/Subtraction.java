package geneticProgramming.function;

import geneticProgramming.terminal.BinaryNode;
import geneticProgramming.terminal.Constant;
import geneticProgramming.terminal.Node;

/**
 * Subtraction operator.
 *
 * This is an implementation of subtraction operator.
 *
 * This class must subtract two values. This two parameters must be  arithmetic classes' instances.
 *
 * This class will evaluate numeric results.
 *
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 18/06/13
 * Time: 23:11
 */
public class Subtraction extends BinaryNode
{

    public static final String SUBTRACTION_SYMBOL = "-";

    public Subtraction(Node left, Node right)
    {
        super(left, right, Subtraction.SUBTRACTION_SYMBOL);
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
        return this.left.evaluate(programParameters) - this.right.evaluate(programParameters);
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

        if (simplifiedLeft.equals(simplifiedRight)) {
            return new Constant(0);
        }

        // Adding zero is pointless, the expression can be reduced to its other argument.
        if (simplifiedLeft instanceof Constant && simplifiedLeft.evaluate(Addition.NO_ARGS) == 0) {
            return simplifiedLeft;
        }

        if (simplifiedRight instanceof Constant && simplifiedRight.evaluate(Addition.NO_ARGS) == 0) {
            return simplifiedRight;
        }

        // If the two arguments are constants, it can be reduced to its final value, since constant values will never
        // change.
        if (simplifiedLeft instanceof Constant && simplifiedRight instanceof Constant) {
            return new Constant(simplifiedLeft.evaluate(Addition.NO_ARGS) - simplifiedRight.evaluate(Addition.NO_ARGS));
        } else if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new Subtraction(simplifiedLeft, simplifiedRight);
        } else {
            return this;
        }
    }
}
