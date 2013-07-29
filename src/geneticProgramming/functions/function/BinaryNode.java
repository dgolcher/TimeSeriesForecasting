package geneticProgramming.functions.function;

import geneticProgramming.functions.Node;
import org.uncommons.maths.random.Probability;
import org.uncommons.util.reflection.ReflectionUtils;
import geneticProgramming.geneticOperators.TreeFactory;

import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * Abstract class used to represent all binary operations.
 *
 * This class is an abstraction of mathematical binary operations.
 * a binary operation on a set is a calculation involving two elements of the set (called operands) and producing
 * another element of the set (more formally, an operation whose arity is two)
 * (Font: http://en.wikipedia.org/wiki/Binary_operation), addition, subtraction, multiplication, etc.
 * This class is used to define some methods that must be equals for all (or almost all) of the binary operators.
 *
 * @author Paulo Fernandes
 *
 * User: Paulo
 * Date: 18/06/13
 * Time: 00:46
 */
abstract public class BinaryNode implements Node
{

    protected static final double[] NO_ARGS = new double[0];

    protected final Node left;
    protected final Node right;
    private final String symbol;
    private double fitnessValue;

    public BinaryNode(Node left, Node right, String symbol)
    {
        this.left         = left;
        this.right        = right;
        this.symbol       = symbol;
        this.fitnessValue = Double.MAX_VALUE;
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
        return 0;
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print()
    {
        StringBuilder buffer = new StringBuilder("(");
        buffer.append(left.print());
        buffer.append(' ');
        buffer.append(symbol);
        buffer.append(' ');
        buffer.append(right.print());
        buffer.append(')');
        return buffer.toString();
    }

    /**
     *
     * @return A short String representing the function or value of the tree routed by this node.
     */
    @Override
    public String getLabel()
    {
        return String.valueOf(this.symbol);
    }

    /**
     * The arity of a binary node is 2.
     *
     * @return value of arity (how many parameters this node will use. Zero, if is not a function).
     */
    @Override
    public int getArity()
    {
        return 2;
    }

    /**
     *
     * @return Number of levels of nodes that make up this tree.
     */
    @Override
    public int getDepth()
    {
        return 1 + Math.max(this.left.getDepth(), this.right.getDepth());
    }

    /**
     *
     * @return A number representing the width of a node. Note that, a leaf has the width of 1. A node that has children
     * has the the width equals the sum of width of its children.
     */
    @Override
    public int getWidth()
    {
        return this.left.getWidth() + this.right.getWidth();
    }

    /**
     *
     * @return Returns the count of nodes existing in this sub-tree.
     */
    @Override
    public int countNodes()
    {
        return 1 + this.left.countNodes() + this.right.countNodes();
    }

    /**
     * Returns which type this node represents.
     *
     * @return returns the type of this node.
     */
    @Override
    public int getType() {
        return Node.ARITHMETIC_NODE;
    }

    /**
     * Returns the value of fitness set to this node. It will be used by the root of a tree.
     *
     * @return value of fitness of this tree.
     */
    @Override
    public Double getFitnessValue() {
        return this.fitnessValue;
    }

    /**
     * Sets the value of fitness value of this tree.
     */
    @Override
    public void setFitnessValue(double fitnessValue) {
        this.fitnessValue = fitnessValue;
    }

    /**
     * Returns a sub-node for this tree.
     *
     * @param index A key for any node. The key zero is set to the root of a sub-tree. The other ones are numbered
     *              using a depth-first, right-to-left strategy.
     * @return The id of the wanted node.
     */
    @Override
    public Node getNode(int index)
    {
        if (index == 0) {
            return this;
        }

        int leftNode = this.left.countNodes();

        if (index <= leftNode) {
            return this.left.getNode(index - 1);
        } else {
            return this.right.getNode(index - 1);
        }
    }

    /**
     * Retrieves a direct sub-node from this tree.
     *
     * @param index Index of the children. The number zero is used to the first child from right. Grandchildren are not
     *              included.
     * @return The node represented by this id parameter.
     */
    @Override
    public Node getChild(int index)
    {
        {
            switch (index) {
                case 0: return this.left;
                case 1: return this.right;
                default: throw new IndexOutOfBoundsException("Invalid child index: " + index);
            }
        }
    }

    /**
     *
     * A node of "GreaterThan" can't have parameters of boolean type. The problem is, if this verification is made after
     * calculating the probability of crossover, and the repetition of prohibitions can reduce the use of crossover.
     *
     *
     * Returns the instance of this node, but with one of its sub-nodes replaced by the one in parameter.
     *
     * @param index Index of the one that will be replaced
     * @param newNode The new instance of node that this tree will assume.
     * @return The node (as its sub-nodes) with a node replaced.
     */
    @Override
    public Node replaceNode(int index, Node newNode)
    {
        if (index == 0) {
            return newNode;
        }

        if (!this.checkConstructionConstraints(newNode)) {
            return this;
        }

        int leftNodes = this.left.countNodes();
        if (index <= leftNodes) {
            return this.newInstance(this.left.replaceNode(index - 1, newNode), this.right);
        } else {
            return this.newInstance(this.left, this.right.replaceNode(index - leftNodes - 1, newNode));
        }
    }

    /**
     * Helper method for the {@link geneticProgramming.geneticOperators.TreeMutation} evolutionary operator
     *
     * @param rng A source from randomness.
     * @param mutationProbability The probability of the mutation process occur.
     * @param treeFactory A factory for creating a new sub-tree needed for mutation.
     * @return The mutated node (or the same node if nothing occurs).
     */
    @Override
    public Node mutate(Random rng, Probability mutationProbability, TreeFactory treeFactory)
    {
        if (mutationProbability.nextEvent(rng)) {
            return treeFactory.generateRandomCandidate(rng, this.getType());
//            return treeFactory.generateRandomCandidate(rng, Node.ALL_TYPES);
        } else {
            Node newLeft  = this.left.mutate(rng, mutationProbability, treeFactory);
            Node newRight = this.right.mutate(rng, mutationProbability, treeFactory);

            if (newLeft != this.left && newRight != this.right) {
                return this.newInstance(newLeft, newRight);
            } else {
                // Tree has not been changed.
                return this;
            }
        }
    }

    private Node newInstance(Node leftNode, Node rightNode)
    {
        Constructor<? extends BinaryNode> constructor = ReflectionUtils.findKnownConstructor(
                this.getClass(), Node.class, Node.class
        );

        return ReflectionUtils.invokeUnchecked(constructor, leftNode, rightNode);
    }

    @Override
    public String toString()
    {
        return this.print();
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify()
    {
        return null;
    }

    /**
     * Verify all conditions that have to be respected to construct a new node of certain type.
     *
     * @return true, if all the conditions are satisfied. False, otherwise.
     */
    @Override
    public boolean checkConstructionConstraints(Node node)
    {
        // Generally, binary nodes have arithmetic nodes as children.
        return node.getType() == Node.ARITHMETIC_NODE;
    }

}
