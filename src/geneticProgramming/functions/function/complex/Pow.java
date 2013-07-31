package geneticProgramming.functions.function.complex;

import geneticProgramming.functions.function.BinaryNode;
import geneticProgramming.functions.terminal.Constant;
import geneticProgramming.functions.Node;

/**
 * Power operator.
 *
 * This is an implementation of power operator.
 *
 * This class must power a value by another one. This two parameters must be  arithmetic classes' instances.
 *
 * This class will evaluate numeric results.
 *
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 12/07/13
 * Time: 22:38
 */
public class Pow extends BinaryNode
{

    public static final String POW_SYMBOL = "^";

    public Pow(Node rightNode, Node leftNode)
    {
        super(rightNode, leftNode, Pow.POW_SYMBOL);
    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any child nodes) and return the fitness
     * value of this.
     * 
     * If its evaluated value is NaN or infinite, then this method will return Double.MAX_VALUE (since this node has an
     * invalid value, the engine will avoid to use this one).
     *
     * @param programParameters Program parameters, used by this node or its children.
     * @return Returns a double value representing this node's fitness value.
     */
    @Override
    public double evaluate(double[] programParameters) {
    	double returnValue = Math.pow(this.left.evaluate(programParameters), this.right.evaluate(programParameters));
        return Double.isNaN(returnValue) || Double.isInfinite(returnValue) ? Double.MAX_VALUE : returnValue;
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify()
    {
        Node simplifiedRight = this.right.simplify();
        Node simplifiedLeft  = this.left.simplify();

//        // If the left node is 0 or 1, then the value of its power is the same.
//        if (simplifiedLeft instanceof Constant &&
//            (simplifiedLeft.evaluate(NO_ARGS) == 0 || simplifiedLeft.evaluate(NO_ARGS) == 1)) {
//            return simplifiedLeft;
//        }
//
//        // If the right node is equals 1, then the value is equals the left node's value.
//        if (simplifiedRight instanceof Constant && simplifiedRight.evaluate(NO_ARGS) == 1) {
//            return simplifiedLeft;
//        }

        // If the right node is equals 0, then the value is equals 1.
        if (simplifiedRight instanceof Constant && simplifiedRight.evaluate(NO_ARGS) == 0) {
            return new Constant(1);
        }

        if (simplifiedLeft instanceof Constant && simplifiedRight instanceof Constant) {
            return new Constant(Math.pow(simplifiedLeft.evaluate(NO_ARGS), simplifiedRight.evaluate(NO_ARGS)));
        } else if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new Pow(simplifiedLeft, simplifiedRight);
        } else {
            return this;
        }
    }
}
