package geneticProgramming.functions.terminal;

import geneticProgramming.functions.Node;

/**
 * Constant class
 *
 * This class represents the boolean constant of true.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 22/06/13
 * Time: 23:15
 */
public class True extends Constant
{

    public True()
    {
        super(true);
    }

    /**
     * Returns which type this node represents. In this case, it represents true.
     *
     * @return returns the type of this node.
     */
    @Override
    public int getType()
    {
        return Node.BOOLEAN_NODE;
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
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print() {
        return "TRUE";
    }
}
