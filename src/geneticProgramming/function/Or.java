package geneticProgramming.function;

import geneticProgramming.terminal.*;

/**
 * Or operator.
 *
 * This is an implementation of Or operator.
 *
 * This class must join two sentences in an "or" operation. This class must receive two parameters and compare them.
 * This two parameters must be instances of boolean classes.
 *
 * This class will evaluate boolean results.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 25/06/13
 * Time: 09:13
 */
public class Or extends BinaryNode
{
    public static final String OR_SYMBOL = "||";

    public Or(Node left, Node right) {
        super(left, right, Or.OR_SYMBOL);
    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any child nodes) and return the fitness
     * value of this.
     *
     * @param programParameters Program parameters, used by this node or its children.
     * @return Returns a double value representing this node's fitness value.
     */
    @Override
    public double evaluate(double[] programParameters) {
        return this.left.evaluate(programParameters) == new True().evaluate(programParameters) ||
                this.right.evaluate(programParameters) == new True().evaluate(programParameters) ? 1 : 0;
    }

    /**
     * Verify all conditions that have to be respected to construct a new node of certain type.
     *
     * @return true, if all the conditions are satisfied. False, otherwise.
     */
    @Override
    public boolean checkConstructionConstraints(Node node) {
        return node.getType() == Node.BOOLEAN_NODE;
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify() {
        Node simplifiedLeft  = this.left.simplify();
        Node simplifiedRight = this.right.simplify();

        if (simplifiedLeft instanceof True || simplifiedRight instanceof True) {
            return new True();
        }

        if (simplifiedLeft instanceof False && simplifiedRight instanceof False) {
            return new False();
        }

        if (simplifiedLeft instanceof False) {
            return simplifiedRight;
        }

        if (simplifiedRight instanceof False) {
            return simplifiedLeft;
        }

        if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new And(simplifiedLeft, simplifiedRight);
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
    public int getType() {
        return Node.BOOLEAN_NODE;
    }
}
