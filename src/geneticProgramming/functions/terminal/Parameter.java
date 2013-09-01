package geneticProgramming.functions.terminal;

/**
 * Parameter.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 18/06/13
 * Time: 21:22
 */
public class Parameter extends LeafNode
{
    private int parameterIndex;

    public Parameter(int parameterIndex)
    {
        this.parameterIndex = parameterIndex;
        this.setFitnessValue(Parameter.BAD_FITNESS_VALUE);
    }

    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any child nodes) and return the fitness
     * value of this.
     *
     * @param programParameter Program parameters, used by this node or its children.
     * @return Returns a double value representing this node's fitness value.
     */
    @Override
    public double evaluate(double[] programParameter)
    {
        if (this.parameterIndex >= programParameter.length) {
            throw new IllegalArgumentException("Invalid parameter index: " + parameterIndex);
        }

        final double returnValue = programParameter[parameterIndex];
        return (Double.isNaN(returnValue) || Double.isInfinite(returnValue)) ? Parameter.BAD_FITNESS_VALUE : returnValue;
    }

    /**
     *
     * @return A short String representing the function or value of the tree routed by this node.
     */
    @Override
    public String getLabel()
    {
        return "P" + this.parameterIndex;
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print()
    {
        return "arg" + this.parameterIndex;
    }

    @Override
    public boolean equals(Object other)
    {
        if (this == other) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        return parameterIndex == ((Parameter) other).parameterIndex;
    }

    @Override
    public int hashCode()
    {
        return parameterIndex;
    }

}
