package geneticProgramming.functions.function.complex;

import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.BinaryNode;
import geneticProgramming.functions.terminal.Constant;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 28/07/13
 * Time: 17:51
 */
public class Modulo extends BinaryNode
{

    public static final String MODULO_SYMBOL = "%";

    public Modulo(Node rightNode, Node leftNode)
    {
        super(rightNode, leftNode, Modulo.MODULO_SYMBOL);
    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any child nodes) and return the fitness
     * value of this.
     * 
     * If the second parameter (rightNode) is zero, then this operation is invalid (division by zero). In this case, 
     * this method will return a fitness value equals Double.MAX_VALUE. 
     *
     * @param programParameters Program parameters, used by this node or its children.
     * @return Returns a double value representing this node's fitness value.
     */
    @Override
    public double evaluate(double[] programParameters)
    {
    	double rightValue = this.right.evaluate(programParameters);
    	if (rightValue == 0) {
    		return Double.MAX_VALUE;
    	}
    	
        return this.left.evaluate(programParameters) % rightValue;
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
            return new Constant(simplifiedLeft.evaluate(NO_ARGS) % simplifiedRight.evaluate(NO_ARGS));
        } else if (simplifiedLeft != this.left || simplifiedRight != this.right) {
            return new Modulo(simplifiedLeft, simplifiedRight);
        } else {
            return this;
        }
    }

}
