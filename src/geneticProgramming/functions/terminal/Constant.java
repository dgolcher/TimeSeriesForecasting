package geneticProgramming.functions.terminal;

import java.text.DecimalFormat;

/**
 * Constant class
 *
 * This class represents a constant number.
 *
 * @author Paulo Fernandes
 *
 * User: Paulo
 * Date: 18/06/13
 * Time: 13:15
 */
public class Constant extends LeafNode
{

    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("######0.##");

    private double constant;
    private String label;

    public Constant(double constant)
    {
        this.constant = constant;
        this.label    = NUMBER_FORMAT.format(constant);
        this.setFitnessValue(Constant.BAD_FITNESS_VALUE);
    }

    public Constant(boolean constant)
    {
        this.constant = constant ? 1 : 0;
        this.label    = constant ? "TRUE" : "FALSE";
        this.setFitnessValue(Constant.BAD_FITNESS_VALUE);
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
        return (Double.isNaN(constant) || Double.isInfinite(constant)) ? Constant.BAD_FITNESS_VALUE : constant;
    }

    /**
     *
     * @return A short String representing the function or value of the tree routed by this node.
     */
    @Override
    public String getLabel()
    {
        return this.label;
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print()
    {
        return String.valueOf(this.constant);
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        return Double.compare(((Constant) other).constant, constant) == 0;
    }

    public int hashCode()
    {
        Long temp = this.constant != +0.0d ? Double.doubleToLongBits(constant) : 0L;
        return (int) (temp ^ (temp >>> 32));
    }

}
