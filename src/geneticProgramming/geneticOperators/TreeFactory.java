package geneticProgramming.geneticOperators;

import geneticProgramming.functions.Node;
import geneticProgramming.functions.function.basic.Addition;
import geneticProgramming.functions.function.basic.Divide;
import geneticProgramming.functions.function.basic.Multiplication;
import geneticProgramming.functions.function.basic.Subtraction;
import geneticProgramming.functions.function.complex.*;
import geneticProgramming.functions.function.logic.*;
import geneticProgramming.functions.function.statistical.*;
import geneticProgramming.functions.function.trigonometric.*;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;
import geneticProgramming.functions.terminal.*;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 17/06/13
 * Time: 14:00
 */
public class TreeFactory extends AbstractCandidateFactory<Node>
{

    /**
     * Identifiers to all kind of operators and terminals that can be used in this Genetic programming project.
     */
    public static final int BASIC_OPERATORS         = 1;
    public static final int COMPLEX_OPERATORS       = 2;
    public static final int LOGIC_OPERATORS         = 4;
    public static final int STATISTICAL_OPERATORS   = 8;
    public static final int TRIGONOMETRIC_OPERATORS = 16;
    public static final int TERMINALS               = 32;

    /**
     * Size of each one of these operators.
     */
    private static final int BASIC_OPERATORS_SIZE          = 4;
    private static final int COMPLEX_OPERATORS_SIZE        = 6;
    private static final int LOGIC_OPERATORS_SIZE          = 7;
    private static final int STATISTICAL_OPERATORS_SIZE    = 7;
    private static final int TRIGONOMETRIC__OPERATORS_SIZE = 6;
    private static final int TERMINALS_SIZE                = 4;

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
    /**
     * This integer variable is used to enable or disable some sets of functions (like basic, complex, logic or
     * trigonometric functions).
     * Each one of this groups of functions is represented as a binary number. When its necessary to know which of
     * them will be used in the genetic programing execution, this class will just compare its binary number, and search
     * if this number is enabled. For example, if just the basic operators will be set as enabled, then this variable
     * will have the number 1. In binary notation, this will be 000001. When it's necessary to know which set of
     * functions are enabled, it's just necessary search to find the values set as 1.
     */
    private int enabledFunctionSets;
    /**
     * Counts the number of valid operators.
     */
    private int numberOfEnabledSets;

    /**
     * Class' constructor.
     *
     * @param parameterCounter
     * @param maximumDepth
     * @param functionProbability
     * @param parameterProbability
     */
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
        this.enabledFunctionSets  = 0;
        this.numberOfEnabledSets  = 0;
    }

    /**
     * This method enables sets of functions. If a invalid operator is set to this method (this value is passed in
     * parameter), then this method will throw an Exception of invalid value.
     *
     * @param functionSet Identifier for the function set to be enabled.
     * @throws Exception
     */
    public void enableFunctionSet(int functionSet) throws Exception
    {
        if (functionSet != TreeFactory.BASIC_OPERATORS         &&
            functionSet != TreeFactory.COMPLEX_OPERATORS       &&
            functionSet != TreeFactory.LOGIC_OPERATORS         &&
            functionSet != TreeFactory.STATISTICAL_OPERATORS   &&
            functionSet != TreeFactory.TRIGONOMETRIC_OPERATORS &&
            functionSet != TreeFactory.TERMINALS) {
            throw new Exception("Invalid Option.");
        }

        this.enabledFunctionSets += functionSet;
        this.numberOfEnabledSets++;
    }

    /**
     * This method disables sets of functions. If a invalid operator is set to this method or a value higher than the
     * value already set as the enabled sets of functions (this value is passed in parameter), then this method will
     * throw an Exception of invalid value.
     *
     * @param functionSet Identifier for the function set to be disabled.
     * @throws Exception
     */
    public void disableFunctionSet(int functionSet) throws Exception
    {
        if (functionSet != TreeFactory.BASIC_OPERATORS         &&
            functionSet != TreeFactory.COMPLEX_OPERATORS       &&
            functionSet != TreeFactory.LOGIC_OPERATORS         &&
            functionSet != TreeFactory.STATISTICAL_OPERATORS   &&
            functionSet != TreeFactory.TRIGONOMETRIC_OPERATORS &&
            functionSet != TreeFactory.TERMINALS               &&
            functionSet >  this.enabledFunctionSets) {
            throw new Exception("Invalid Option.");
        }

        this.enabledFunctionSets -= functionSet;
        this.numberOfEnabledSets--;
    }

    /**
     * This method verifies if a function set is enabled or not.
     *
     * @param functionSet  Identifier for the function set to be verified.
     * @return return if the set of functions is or not enabled.
     * @throws Exception
     */
    public boolean isFunctionSetEnabled(int functionSet) throws Exception
    {
        if (functionSet != TreeFactory.BASIC_OPERATORS         &&
            functionSet != TreeFactory.COMPLEX_OPERATORS       &&
            functionSet != TreeFactory.LOGIC_OPERATORS         &&
            functionSet != TreeFactory.STATISTICAL_OPERATORS   &&
            functionSet != TreeFactory.TRIGONOMETRIC_OPERATORS &&
            functionSet != TreeFactory.TERMINALS) {
            throw new Exception("Invalid Option.");
        }

        if (functionSet > this.enabledFunctionSets) {
            return false;
        }

        String enabledFunctions = Integer.toBinaryString(this.enabledFunctionSets);
        String verifiedSet      = Integer.toBinaryString(functionSet);
        int    functionIndex    = enabledFunctions.length() - (verifiedSet.length());

        return enabledFunctions.charAt(functionIndex) == '1';
    }

    /**
     * This method will start all the generation of a tree of nodes.
     *
     * @param random random generator.
     * @return return a new candidate.
     */
    @Override
    public Node generateRandomCandidate(Random random)
    {
        return this.makeNode(random, this.maximumDepth, Node.ALL_TYPES);
    }

    /**
     * This method will start all the generation of a tree of nodes.
     *
     * @param random random generator.
     * @param nodeType type of node to be generated.
     * @return return a new candidate.
     */
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
     * @param maxDepth Maximum depth value of a tree.
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
                return this.makeLogicNode(rng, depth);
            } else {
                return rng.nextInt(2) == 1 ? this.makeDoubleNode(rng, depth) : this.makeLogicNode(rng, depth);
            }
        } else if (this.parameterProbability.nextEvent(rng) && nodeType == Node.ARITHMETIC_NODE) {
            return new Parameter(rng.nextInt(this.parameterCount));
        } else {
            return makeTerminalNode(rng, nodeType);
        }
    }

    /**
     * Make a parameter node.
     *
     * @param rng random generator. Used in boolean nodes, when we have to to constants, true or false.
     * @param nodeType which type this node to be created is: It can be boolean or arithmetic.
     * @return return a node containing a constant created.
     */
    private Node makeTerminalNode(Random rng, int nodeType)
    {
        if (nodeType == Node.ARITHMETIC_NODE) {
            return new Constant(rng.nextInt(11));
        } else {
            // This uses minus 2 because it isn't counting constants and parameters.
            switch (rng.nextInt(TreeFactory.TERMINALS_SIZE - 2)) {
                case 0:  return new True();
                default: return new False();
            }
        }
    }

    /**
     * This method is used to create double nodes. It will chose, randomly one kind of node. This node have to be
     * a double node.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeDoubleNode(Random random, int maxDepth)
    {
        try {
            // This while block is used in this way because it will stop naturally (returning a new Node), when a valid
            // operator set is chosen.
            while(true) {
                // 4 is the number of valid double sets of operators.
                int choice = (int) ((13.0f * (random.nextFloat() - 0.3f)) % 4);
                switch (choice) {
                    case 0:
                        if (this.isFunctionSetEnabled(TreeFactory.TRIGONOMETRIC_OPERATORS)) {
                            return this.makeArithmeticalTrigonometricNode(random, maxDepth);
                        }
                        break;
                    case 1:
                        if (this.isFunctionSetEnabled(TreeFactory.STATISTICAL_OPERATORS)) {
                            return this.makeArithmeticalStatisticalNode(random, maxDepth);
                        }
                        break;
                    case 2:
                        if (this.isFunctionSetEnabled(TreeFactory.COMPLEX_OPERATORS)) {
                            return this.makeArithmeticalComplexNode(random, maxDepth);
                        }
                        break;
                    default:
                        if (this.isFunctionSetEnabled(TreeFactory.BASIC_OPERATORS)) {
                            return this.makeArithmeticalBasicNode(random, maxDepth);
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method is used to create double nodes, using basic arithmetical operators. It will close, randomly one kind
     * of node. This node have to be a double node.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeArithmeticalBasicNode(Random random, int maxDepth)
    {
        switch (random.nextInt(TreeFactory.BASIC_OPERATORS_SIZE)) {
            case 0: return new Addition(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            case 1: return new Subtraction(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            case 2: return new Multiplication(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
            default: return new Divide(
                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE), makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
        }

    }

    /**
     * This method is used to create double nodes, using complex arithmetical operators. It will close, randomly one
     * kind of node. This node have to be a double node.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeArithmeticalComplexNode(Random random, int maxDepth)
    {
        switch (random.nextInt(TreeFactory.COMPLEX_OPERATORS_SIZE)) {
//            case 0: return new Abs(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 1: return new Log(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 2: return new Log10(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 3: return new Modulo(
//                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE),
//                this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
//            );
//            case 4: return new Pow(
//                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE),
//                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
//            );
            default: return new Root(
                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE),
                    this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE)
            );
        }
    }

    /**
     * This method is used to create double nodes, using statistical operators. It will close, randomly one kind of
     * node. This node have to be a double node.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeArithmeticalStatisticalNode(Random random, int maxDepth)
    {
        switch (random.nextInt(TreeFactory.STATISTICAL_OPERATORS_SIZE)) {
//            case 0: return new Kurtosis(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 1: return new Max(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 2: return new Mean(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 3: return new Min(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 4: return new Skewness(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            case 5: return new StandartDeviation(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
//            default: return new Variance(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
        }
        return null;
    }

    /**
     * This method is used to create double nodes, using trigonometric operators. It will close, randomly one kind of
     * node. This node have to be a double node.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeArithmeticalTrigonometricNode(Random random, int maxDepth)
    {
        switch (random.nextInt(TreeFactory.TRIGONOMETRIC__OPERATORS_SIZE)) {
            case 0: return new ArcCosine(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
            case 1: return new ArcSine(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
            case 2: return new ArcTangent(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
            case 3: return new Cosine(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
            case 4: return new Sine(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
            default: return new Tangent(this.makeNode(random, maxDepth, Node.ARITHMETIC_NODE));
        }
    }

    /**
     * This method is used to create double nodes, using complex arithmetical operators. It will close, randomly one
     * kind of node. This node have to be a double node.
     *
     * @param random random generator.
     * @param maxDepth Maximum value for depth.
     * @return return a node with double return.
     */
    private Node makeLogicNode(Random random, int maxDepth)
    {
        int nodeType          = random.nextInt(1) + 1;

        switch (random.nextInt(TreeFactory.LOGIC_OPERATORS_SIZE)) {
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

}
