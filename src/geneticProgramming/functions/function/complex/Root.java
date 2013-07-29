package geneticProgramming.functions.function.complex;

import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.BinaryNode;
import geneticProgramming.functions.terminal.Constant;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 28/07/13
 * Time: 17:38
 */
public class Root extends BinaryNode
{

    public static final String ROOT_SYMBOL = "^";

    public Root(Node rightNode, Node leftNode)
    {
        super(rightNode, leftNode, Root.ROOT_SYMBOL);
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
        return Math.pow(this.left.evaluate(programParameters), 1.0 / this.right.evaluate(programParameters));
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print() {
        return "root(" + this.left + ", " + this.right + ")";
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

        if (simplifiedLeft instanceof Constant && simplifiedRight instanceof Constant) {
            return new Constant(Math.pow(simplifiedLeft.evaluate(NO_ARGS), 1.0 / simplifiedRight.evaluate(NO_ARGS)));
        } else if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new Pow(simplifiedLeft, simplifiedRight);
        } else {
            return this;
        }
    }

}
