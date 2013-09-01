package geneticProgramming.functions.function.complex;

import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.UnaryNode;
import geneticProgramming.functions.terminal.Constant;
import org.uncommons.maths.random.MersenneTwisterRNG;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 27/07/13
 * Time: 00:57
 */
public class Log10 extends UnaryNode
{

    public Log10(Node node)
    {
        super(node);
    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any child nodes) and return the fitness
     * value of this.
     * 
     * If its evaluated value is NaN or infinite, then this method will return Node.BAD_FITNESS_VALUE (since this node has an
     * invalid value, the engine will avoid to use this one).
     *
     * @param programParameters Program parameters, used by this node or its children.
     * @return Returns a double value representing this node's fitness value.
     */
    @Override
    public double evaluate(double[] programParameters) {
    	double returnValue = Math.log10(this.node.evaluate(programParameters));
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
        return "log10 (" + this.node + ")";
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     *
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify() {
        Node simplifiedNode = this.node.simplify();

        // If the value is invalid for Java's Log10, then this node will be mutated to the Log10 of a new random value.
        if (simplifiedNode instanceof Constant && simplifiedNode.evaluate(NO_ARGS) <= 0) {
            MersenneTwisterRNG random = new MersenneTwisterRNG();
            return new Log10(new Constant(random.nextDouble()));
        }

        if (simplifiedNode instanceof Constant) {
            return new Constant(Math.log10(simplifiedNode.evaluate(NO_ARGS)));
        } else if (simplifiedNode != this.node) {
            return new Log10(simplifiedNode);
        } else {
            return this;
        }

    }

}
