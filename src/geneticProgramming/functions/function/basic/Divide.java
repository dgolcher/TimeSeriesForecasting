package geneticProgramming.functions.function.basic;

import geneticProgramming.functions.function.BinaryNode;
import geneticProgramming.functions.terminal.Constant;
import geneticProgramming.functions.Node;

/**
 *
 * Division operator.
 *
 * This is an implementation of division operator.
 *
 * This class must divide two values. This class must receive two parameters and divide the first by the second.
 * This two parameters must be  arithmetic classes' instances.
 *
 * This class will evaluate numeric results.
 *
 * Obs.: In case of division by zero (which is invalid), this class will consider that the node will suffer a mutation,
 * and the result will be the value found on its numerator.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 13/07/13
 * Time: 01:14
 */
public class Divide extends BinaryNode
{

    public static final String DIVIDE_SYMBOL = "*";

    public Divide(Node left, Node right)
    {
        super(left, right, Divide.DIVIDE_SYMBOL);
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
        return this.left.evaluate(programParameters) / this.right.evaluate(programParameters);
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

        if (simplifiedRight instanceof Constant && simplifiedRight.evaluate(NO_ARGS) == 1) {
            return simplifiedLeft;
        }

        // If the denominator of the division is produced as zero, this algorithm considers that it will
        // return the value of numerator.
        if (simplifiedRight instanceof Constant && simplifiedRight.evaluate(NO_ARGS) == 0) {
            return simplifiedLeft;
        }

        // Case both of them, left and right nodes are constants, it can be simplified to its result.
        if (simplifiedLeft instanceof Constant && simplifiedRight instanceof Constant) {
            return new Constant(simplifiedLeft.evaluate(NO_ARGS) / simplifiedRight.evaluate(NO_ARGS));
        } else if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new Multiplication(simplifiedLeft, simplifiedRight);
        } else {
            return this;
        }
    }

}
