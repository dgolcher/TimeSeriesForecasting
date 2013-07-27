package geneticProgramming.functions.terminal;

import geneticProgramming.functions.Node;
import org.uncommons.maths.random.Probability;
import geneticProgramming.geneticOperators.TreeFactory;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 18/06/13
 * Time: 13:16
 */
public class LeafNode implements Node
{
    private double fitnessvalue;

    public LeafNode()
    {
        this.fitnessvalue = Double.MAX_VALUE;
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
        return 0;
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print() {
        return null;
    }

    /**
     *
     * @return A short String representing the function or value of the tree routed by this node.
     */
    @Override
    public String getLabel() {
        return null;
    }

    /**
     * If it is a function (non-leaves) node, how many arguments does it take? For leaves the answer is zero.
     *
     * @return The number of parameters for this node, if it is a function.
     */
    @Override
    public int getArity() {
        return 0;
    }

    /**
     *
     * @return Number of levels of nodes that make up this tree.
     */
    @Override
    public int getDepth() {
        return 1;
    }

    /**
     *
     * @return A number representing the width of a node. Note that, a leaf has the width of 1. A node that has children
     * has the the width equals the sum of width of its children.
     */
    @Override
    public int getWidth() {
        return 1;
    }

    /**
     *
     * @return Returns the count of nodes existing in this sub-tree.
     */
    @Override
    public int countNodes() {
        return 1;
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
        return this.fitnessvalue;
    }

    /**
     * Sets the value of fitness value of this tree.
     */
    @Override
    public void setFitnessValue(double fitnessValue) {
        this.fitnessvalue = fitnessValue;
    }

    /**
     * Returns a sub-node for this tree.
     *
     * @param index A key for any node. The key zero is set to the root of a sub-tree. The other ones are numbered
     *              using a depth-first, right-to-left strategy.
     * @return The id of the wanted node.
     */
    @Override
    public Node getNode(int index) {
//        if (index != 0) {
//            throw new IndexOutOfBoundsException("Invalid node index " + index);
//        }

        return this;
    }

    public Node getNode() {
        return this.getNode(0);
    }

    /**
     * Retrieves a direct sub-node from this tree.
     *
     * @param index Index of the children. The number zero is used to the first child from right. Grandchildren are not
     *              included.
     * @return The node represented by this id parameter.
     */
    @Override
    public Node getChild(int index) {
        throw new IndexOutOfBoundsException("Leaf nodes have no children.");
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
    public Node replaceNode(int index, Node newNode) {
        if (index != 0) {
            throw new IndexOutOfBoundsException("Invalid node index: " + index);
        }

        if (!this.checkConstructionConstraints(newNode)) {
            return this;
        }

        return newNode;
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
        } else {
            // Node is unchanged.
            return this;
        }
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify()
    {
        return this;
    }

    /**
     * Verify all conditions that have to be respected to construct a new node of certain type.
     *
     * @return true, if all the conditions are satisfied. False, otherwise.
     */
    @Override
    public boolean checkConstructionConstraints(Node node) {
        return node.getType() == Node.ARITHMETIC_NODE;
    }

    @Override
    public String toString()
    {
        return this.print();
    }

}
