package geneticProgramming.geneticOperators;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;
import geneticProgramming.function.*;
import geneticProgramming.terminal.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 17/06/13
 * Time: 14:00
 */
public class TreeFactory extends AbstractCandidateFactory<Node>
{

//    private static final int NUMBER_OF_OPERATORS = 5;

    /**
     * The number of program parameter that each program tree will be provided.
     */
    private final int parameterCount;
    /**
     * The maximum depth of a program tree. No function nodes will be created bellow this depth (branches will be
     * terminated with parameters or constants).
     */
    private final int maximumDepth;
    /**
     * Probability that a created node is a function rather than a value node.
     */
    private final Probability functionProbability;
    /**
     * Probability that a value(non-function) node is a parameter node rather than a constant node.
     */
    private final Probability parameterProbability;


    public TreeFactory(int parameterCounter, int maximumDepth,
                       Probability functionProbability, Probability parameterProbability)
    {
        if (parameterCounter < 0) {
            throw new IllegalArgumentException("Parameter counter must be greater than or equal zero.");
        }

        if (maximumDepth < 1) {
            throw new IllegalArgumentException("Maximum depth must be at least 1.");
        }

        this.parameterCount       = parameterCounter;
        this.maximumDepth         = maximumDepth;
        this.functionProbability  = functionProbability;
        this.parameterProbability = parameterProbability;
    }

    @Override
    public Node generateRandomCandidate(Random random)
    {
        return this.makeNode(random, this.maximumDepth, Node.ALL_TYPES).simplify();
    }

    public Node generateRandomCandidate(Random random, int nodeType)
    {
        return this.makeNode(random, this.maximumDepth, nodeType);
    }


    /**
     * This method is used to create new nodes.
     * This method will decide, randomly which type of node will be created. Although randomly, this method follow some
     * rules to create nodes. For example, a node can have only arithmetic nodes as children. In this way, this method
     * will create only arithmetic nodes. The same can be said about boolean nodes.
     * Other possibility is that a tree (a set of nodes) have trespassed its maximum depth. In this way, this method
     * will create a Constant or a Parameter node.
     *
     * @param rng random generator.
     * @param maxDepth Maximum value of depth of a tree.
     * @param nodeType type of node that will be made.
     * @return node.
     */
    private Node makeNode(Random rng, int maxDepth, int nodeType)
    {
        if (this.functionProbability.nextEvent(rng) && maxDepth > 1) {
            // Max depth for sub-trees is one less than max depth for this node.
            int depth = maxDepth - 1;

            // If the type is equals Node.ARITHMETIC_NODE, then only double nodes can be used.
            // If the type is equals Node.BOOLEAN_NODE, then only boolean nodes can be used.
            // If the type is Node.ALL_TYPES, then the type of node to be produced will be selected randomly.
            if (nodeType == Node.ARITHMETIC_NODE) {
                return this.makeDoubleNode(rng, depth);
            } else if (nodeType == Node.BOOLEAN_NODE) {
                return this.makeBooleanNode(rng, depth);
            } else {
                return rng.nextInt(2) == 1 ? this.makeDoubleNode(rng, depth) : this.makeBooleanNode(rng, depth);
            }
        } else if (this.parameterProbability.nextEvent(rng) && nodeType == Node.ARITHMETIC_NODE) {
            return new Parameter(rng.nextInt(this.parameterCount));
        } else {
            return makeConstant(rng, nodeType);
        }
    }

    /**
     * Make a parameter node.
     *
     * @param rng random generator. Used in boolean nodes, when we have to to constants, true or false.
     * @param nodeType which type this node to be created is: It can be boolean or arithmetic.
     * @return return a node containing a constant created.
     */
    private Node makeConstant(Random rng, int nodeType) {
        if (nodeType == Node.ARITHMETIC_NODE) {
            return new Constant(rng.nextInt(11));
        } else {
            int numberOfOperators = 2;

            switch (rng.nextInt(numberOfOperators)) {
                case 0:  return new True();
                default: return new False();
            }
        }
    }

    /**
     * This method is used to create boolean nodes. It will chose, randomly one kind of node. This node have to be
     * a boolean.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeBooleanNode(Random random, int maxDepth)
    {
        int numberOfOperators = 6;
        int nodeType          = random.nextInt(1) + 1;

        switch (random.nextInt(numberOfOperators)) {
            case 0: return new Equals(
                this.makeNode(random, maxDepth, nodeType), this.makeNode(random, maxDepth, nodeType)
            );
            case 1: return new Not(this.makeNode(random, maxDepth, Node.BOOLEAN_NODE));
            case 2: return new And(
                this.makeNode(random, maxDepth, Node.BOOLEAN_NODE), this.makeNode(random, maxDepth, Node.BOOLEAN_NODE)
            );
            case 3: return new Or(
                this.makeNode(random, maxDepth, Node.BOOLEAN_NODE), this.makeNode(random, maxDepth, Node.BOOLEAN_NODE)
            );
            case 4: return new LesserThan(
                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE),
                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            default: return new GreaterThan(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE),
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
        }
    }

    /**
     * This method is used to create boolean nodes. It will chose, randomly one kind of node. This node have to be
     * a boolean.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeDoubleNode(Random random, int maxDepth)
    {
        int numberOfOperators = 7;
        switch (random.nextInt(numberOfOperators)) {
            case 0: return new Addition(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            case 1: return new Subtraction(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            case 2: return new Multiplication(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            case 3: return new Divide(
                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            case 4: return new Abs(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
            case 5: return new Pow(
                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE),
                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            default: return new IfThenElse(
                this.makeNode(random, maxDepth, Node.BOOLEAN_NODE),
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE),
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
        }
    }

}
