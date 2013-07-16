package geneticProgramming.function;

import geneticProgramming.terminal.*;

/**
 * Equals operator.
 *
 * This is an implementation of equals operator.
 *
 * This class must compare the value of two sentences, and verify if they are equal or not. This class must receive
 * two parameters and compare them. This two parameters can be of any type (in this package, it can receive both
 * arithmetic classes' instances or boolean classes' instances), since both are in the same type.
 *
 * This class will evaluate boolean results.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 19/06/13
 * Time: 23:58
 */
public class Equals extends BinaryNode
{
    private static final String EQUALS_SYMBOL = "=";

    public Equals(Node left, Node right)
    {
        super(left, right, Equals.EQUALS_SYMBOL);
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
        return this.left.evaluate(programParameters) == this.right.evaluate(programParameters) ? 1 : 0;
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

        if (simplifiedLeft instanceof True) {
            return simplifiedRight;
        }

        if (simplifiedRight instanceof True) {
            return simplifiedLeft;
        }

        if (simplifiedLeft instanceof False) {
            return new Not(simplifiedRight);
        }

        if (simplifiedRight instanceof False) {
            return new Not(simplifiedLeft);
        }

        if (simplifiedLeft instanceof Constant && simplifiedRight instanceof Constant) {
            if (simplifiedLeft.evaluate(NO_ARGS) == simplifiedRight.evaluate(NO_ARGS)) {
                return new True();
            } else {
                return new False();
            }
        } else if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new Equals(simplifiedLeft, simplifiedRight);
        } else {
            return this;
        }
    }

    /**
     * Returns which type this node represents.
     *
     * @return returns the type of this node.
     */
    @Override
    public int getType()
    {
        return Node.BOOLEAN_NODE;
    }
}
