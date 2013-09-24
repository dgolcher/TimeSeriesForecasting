package geneticProgramming.functions;

import org.uncommons.maths.random.Probability;
import geneticProgramming.geneticOperators.TreeFactory;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 17/06/13
 * Time: 13:25
 */
public interface Node
{

    /**
     * Constants used to identify which type of node this one represents. It is important to deploy the nodes, since
     * it will make possible select better which node will be used in which type of operator.
     */
    public static final int BOOLEAN_NODE    = 1;
    public static final int ARITHMETIC_NODE = 2;
    public static final int ALL_TYPES       = 3;

    /**
     * This constant is used when the evaluation of a node returns an invalid (or not desired) result. In these cases,
     * the individual that has this node must be eliminated. In this way, the individual will have a really bad fitness
     * value (which will make it completely unfitted).
     */
    public static final int BAD_FITNESS_VALUE = 99;


    /**
     * Recursively evaluates the (sub-)tree represented by this node (including any child nodes) and return the fitness
     * value of this.
     *
     * @param programParameters Program parameters, used by this node or its children.
     * @return Returns a double value representing this node's fitness value.
     */
    double evaluate(double[] programParameters);

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    String print();

    /**
     *
     * @return A short String representing the function or value of the tree routed by this node.
     */
    String getLabel();

    /**
     * If it is a function (non-leaves) node, how many arguments does it take? For leaves the answer is zero.
     *
     * @return The number of parameters for this node, if it is a function.
     */
    int getArity();

    /**
     *
     * @return Number of levels of nodes that make up this tree.
     */
    int getDepth();

    /**
     *
     * @return A number representing the width of a node. Note that, a leaf has the width of 1. A node that has children
     * has the the width equals the sum of width of its children.
     */
    int getWidth();

    /**
     *
     * @return Returns the count of nodes existing in this sub-tree.
     */
    int countNodes();

    /**
     * Returns which type this node represents.
     *
     * @return returns the type of this node.
     */
    int getType();

    /**
     * Returns the value of fitness set to this node. It will be used by the root of a tree.
     *
     * @return value of fitness of this tree.
     */
    double getFitnessValue();

    /**
     * Sets the value of fitness value of this tree.
     */
    void setFitnessValue(double fitnessValue);

    /**
     * Returns a sub-node for this tree.
     *
     * @param index A key for any node. The key zero is set to the root of a sub-tree. The other ones are numbered
     *              using a depth-first, right-to-left strategy.
     * @return The id of the wanted node.
     */
    Node getNode(int index);

    /**
     * Retrieves a direct sub-node from this tree.
     *
     * @param index Index of the children. The number zero is used to the first child from right. Grandchildren are not
     *              included.
     * @return The node represented by this id parameter.
     */
    Node getChild(int index);

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
    Node replaceNode(int index, Node newNode);

    /**
     * Helper method for the {@link geneticProgramming.geneticOperators.TreeMutation} evolutionary operator
     *
     * @param rng A source from randomness.
     * @param mutationProbability The probability of the mutation process occur.
     * @param treeFactory A factory for creating a new sub-tree needed for mutation.
     * @return The mutated node (or the same node if nothing occurs).
     */
    Node mutate(Random rng, Probability mutationProbability, TreeFactory treeFactory);

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    Node simplify();

    /**
     * Verify all conditions that have to be respected to construct a new node of certain type.
     *
     * @return true, if all the conditions are satisfied. False, otherwise.
     */
    boolean checkConstructionConstraints(Node node);

}