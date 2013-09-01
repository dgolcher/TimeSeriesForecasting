package geneticProgramming.functions.function.trigonometric;

import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.UnaryNode;
import geneticProgramming.functions.terminal.Constant;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 27/07/13
 * Time: 00:17
 */
public class ArcCosine extends UnaryNode
{

    public ArcCosine(Node node)
    {
        super(node);
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
        final double returnValue = Math.acos(this.node.evaluate(programParameters));
        return (Double.isNaN(returnValue) || Double.isInfinite(returnValue)) ? Node.BAD_FITNESS_VALUE : returnValue;
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print()
    {
        return "acos (" + this.node + ")";
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     *
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify() {
        Node simplifiedNode = this.node.simplify();

        if (simplifiedNode instanceof Constant) {
            return new Constant(Math.acos(simplifiedNode.evaluate(NO_ARGS)));
        } else if (simplifiedNode != this.node) {
            return new ArcCosine(simplifiedNode);
        } else {
            return this;
        }

    }

}
