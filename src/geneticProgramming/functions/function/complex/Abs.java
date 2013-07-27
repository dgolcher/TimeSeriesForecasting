package geneticProgramming.functions.function.complex;

import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.UnaryNode;
import geneticProgramming.functions.terminal.*;

/**
 * Absolute operator.
 *
 * This is an implementation of absolute operator.
 *
 * This class must receive one parameter (This param must be a numeric class of this package), and return its absolute
 * value.
 *
 * This class will evaluate numeric results.
 *
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 13/07/13
 * Time: 00:25
 */
public class Abs extends UnaryNode
{

    public Abs(Node node)
    {
        super(node);
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print() {
        return "abs(" + this.node.print()  + ")";
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify() {
        Node simplifiedNode = this.node.simplify();

        if (simplifiedNode instanceof Constant) {
            return new Constant(Math.abs(simplifiedNode.evaluate(new double[0])));
        }

        if (this != simplifiedNode) {
            return simplifiedNode.simplify();
        } else {
            return this;
        }
    }
}
