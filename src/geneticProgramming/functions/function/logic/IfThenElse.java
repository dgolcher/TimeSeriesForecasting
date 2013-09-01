package geneticProgramming.functions.function.logic;

import org.uncommons.maths.random.Probability;
import geneticProgramming.functions.Node;
import geneticProgramming.geneticOperators.TreeFactory;
import geneticProgramming.functions.terminal.Constant;

import java.util.Random;

/**
 * IfThenElse operator.
 *
 * This class implements a conditional operation. It has an boolean class condition and two numeric results: One of then
 * will be used when the condition is true, the other, when is false.
 *
 * This class will evaluate boolean results.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 19/06/13
 * Time: 13:03
 */
public class IfThenElse implements Node
{

    private final Node condition;
    private final Node then;
    private final Node otherwise;
    private double fitnessValue;


    public IfThenElse(Node condition, Node then, Node otherwise) {
        this.condition    = condition;
        this.then         = then;
        this.otherwise    = otherwise;
        this.fitnessValue = Node.BAD_FITNESS_VALUE;
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
        final double returnValue = condition.evaluate(programParameters) > 0 // If...
                                    ? then.evaluate(programParameters)   // Then...
                                    : otherwise.evaluate(programParameters);  // Else...
        return (Double.isNaN(returnValue) || Double.isInfinite(returnValue)) ? Node.BAD_FITNESS_VALUE : returnValue;
    }

    /**
     * Recursively  builds a String representing the tree routed by this node.
     *
     * @return String representing this tree.
     */
    @Override
    public String print() {
        // Ternary Operator.
//        return "(" + condition.print() + " ? " + then.print() + " : " + otherwise.print() + ")";
        // If then Else, java notation.
        return "if (" + condition.print() + ") {" + then.print() + "} else {" + otherwise.print() + "}";
        // Excel notation.
//        return "se(("+condition.print() + "); " + then.print() + "; " + otherwise.print() + ")";
    }

    /**
     * @return A short String representing the function or value of the tree routed by this node.
     */
    @Override
    public String getLabel() {
        return "if";
    }

    /**
     * If it is a function (non-leaves) node, how many arguments does it take? For leaves the answer is zero.
     *
     * @return The number of parameters for this node, if it is a function.
     */
    @Override
    public int getArity() {
        return 3;
    }

    /**
     * @return Number of levels of nodes that make up this tree.
     */
    @Override
    public int getDepth() {
        return 1 + Math.max(this.condition.getDepth(), Math.max(this.then.getDepth(), this.otherwise.getDepth()));
    }

    /**
     * @return A number representing the width of a node. Note that, a leaf has the width of 1. A node that has children
     *         has the the width equals the sum of width of its children.
     */
    @Override
    public int getWidth() {
        return this.condition.getWidth() + this.then.getWidth() + this.otherwise.getWidth();
    }

    /**
     * @return Returns the count of nodes existing in this sub-tree.
     */
    @Override
    public int countNodes() {
        return 1 + this.condition.countNodes() + this.then.countNodes() + this.otherwise.countNodes();
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

    @Override
    public Double getFitnessValue() {
        return this.fitnessValue;
    }

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
    public Node getNode(int index) {
        if (index == 0) {
            return this;
        }

        int conditionNodes = condition.countNodes();
        if (index <= conditionNodes) {
            return condition.getNode(index - 1);
        } else {
            int thenNodes = then.countNodes();
            if (index <= conditionNodes + thenNodes) {
                return then.getNode(index - conditionNodes - 1);
            } else {
                return otherwise.getNode(index - conditionNodes - thenNodes - 1);
            }
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
    public Node getChild(int index) {
        switch (index) {
            case 0: return condition;
            case 1: return then;
            case 2 : return otherwise;
            default: throw new IndexOutOfBoundsException("Invalid child index: " + index);
        }
    }

    /**
     * Returns the instance of this node, but with one of its sub-nodes replaced by the one in parameter.
     *
     * @param index   Index of the one that will be replaced
     * @param newNode The new instance of node that this tree will assume.
     * @return The node (as its sub-nodes) with a node replaced.
     */
    @Override
    public Node replaceNode(int index, Node newNode)
    {
        if (index == 0) {
            return newNode;
        }

        int conditionNodes = condition.countNodes();
        if (index <= conditionNodes) {
            if (newNode.getType() != Node.BOOLEAN_NODE) {
                return this;
            }

            return new IfThenElse(condition.replaceNode(index - 1, newNode), then, otherwise);
        } else {
            if (newNode.getType() != Node.ARITHMETIC_NODE) {
                return this;
            }

            int thenNodes = then.countNodes();
            if (index <= conditionNodes + thenNodes) {
                return new IfThenElse(condition, then.replaceNode(index - conditionNodes - 1, newNode), otherwise);
            } else {
                return new IfThenElse(
                    condition, then, otherwise.replaceNode(index - conditionNodes - thenNodes - 1, newNode)
                );
            }
        }
    }

    /**
     * Helper method for the {@link geneticProgramming.geneticOperators.TreeMutation} evolutionary operator
     *
     * @param rng                 A source from randomness.
     * @param mutationProbability The probability of the mutation process occur.
     * @param treeFactory         A factory for creating a new sub-tree needed for mutation.
     * @return The mutated node (or the same node if nothing occurs).
     */
    @Override
    public Node mutate(Random rng, Probability mutationProbability, TreeFactory treeFactory) {
        if (mutationProbability.nextEvent(rng)) {
            return treeFactory.generateRandomCandidate(rng, this.getType());
        } else {
            Node newCondition = condition.mutate(rng, mutationProbability, treeFactory);
            Node newThen = then.mutate(rng, mutationProbability, treeFactory);
            Node newOtherwise = otherwise.mutate(rng, mutationProbability, treeFactory);
            if (newCondition != condition || newThen != then || newOtherwise != otherwise) {
                return new IfThenElse(newCondition, newThen, newOtherwise);
            } else {
                // Tree has not changed.
                return this;
            }
        }
    }

    @Override
    public String toString()
    {
        return this.print();
    }

    /**
     * Reduces this program tree to this simplest equivalent representation form.
     *
     * @return A tree representing this one simplified, or this, if it cannot be done.
     */
    @Override
    public Node simplify() {
        Node simplifiedCondition = condition.simplify();

        // If the condition is constant then the expression can be replaced by the branch that
        // always gets evaluated.
        if (simplifiedCondition instanceof Constant) {
            return simplifiedCondition.evaluate(null) > 0 ? this.then.simplify() : this.otherwise.simplify();
        } else {
            Node simplifiedThen      = then.simplify();
            Node simplifiedOtherwise = otherwise.simplify();
            // If both branches are identical, the condition is irrelevant.
            if (simplifiedThen.equals(simplifiedOtherwise)) {
                return simplifiedThen;
            }
            // Only create a new node if something has actually changed, otherwise return the existing node.
            if (simplifiedCondition != condition || simplifiedThen != then || simplifiedOtherwise != otherwise) {
                return new IfThenElse(simplifiedCondition, simplifiedThen, simplifiedOtherwise);
            } else {
                return this;
            }
        }
    }

    /**
     * Verify all conditions that have to be respected to construct a new node of certain type.
     *
     * @return true, if all the conditions are satisfied. False, otherwise.
     */
    @Override
    public boolean checkConstructionConstraints(Node node) {
        return false;
    }

}
