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
    	double returnValue = Math.abs(this.node.evaluate(programParameters));
        return Double.isNaN(returnValue) || Double.isInfinite(returnValue) ? Double.MAX_VALUE : returnValue;
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
