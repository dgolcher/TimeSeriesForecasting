package geneticProgramming.function;

import geneticProgramming.terminal.*;

/**
 * Not operator.
 *
 * This is an implementation of not operator.
 *
 * This class must negate a sentence.
 * This two parameters must be instances of boolean classes.
 *
 * This class will evaluate boolean results.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 23/06/13
 * Time: 00:56
 */
public class Not extends UnaryNode
{
    public Not(Node node) {
        super(node);
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print() {
        return "!(" + this.node.print()  + ")";
    }

    /**
     * Returns which type this node represents.
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
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify()
    {
        Node simplifiedNode = this.node.simplify();

        if (simplifiedNode instanceof True) {
            return new False();
        }

        if (simplifiedNode instanceof False) {
            return new True();
        }

        if (this.node instanceof Not) {
            return this.node.getChild(0).simplify();
        }

        if (this != simplifiedNode) {
            return simplifiedNode.simplify();
        } else {
            return this;
        }
    }
}
