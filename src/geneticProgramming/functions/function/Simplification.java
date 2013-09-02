package geneticProgramming.functions.function;

import geneticProgramming.functions.terminal.Constant;
import geneticProgramming.geneticOperators.TreeFactory;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import geneticProgramming.functions.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 18/06/13
 * Time: 22:19
 */
public class Simplification implements EvolutionaryOperator<Node>
{

    private final Probability probability;
    /**
     * This factory will be used in cases that the node to be simplified is an invalid node. In this way, this method
     * will create a new node and simplify it.
     */
    private TreeFactory factory;

    public Simplification(TreeFactory factory)
    {
        this(Probability.ONE);
        this.factory = factory;
    }

    public Simplification(Probability probability)
    {
        this.probability = probability;
    }

    @Override
    public List<Node> apply(List<Node> selectedCandidates, Random random)
    {
        List<Node> evolved = new ArrayList<Node>(selectedCandidates.size());
        for (Node node : selectedCandidates) {
            evolved.add(this.getIndividual(random, node));
        }

        return evolved;
    }

    /**
     * This method will define, randomly, if the node is or not simplified. In both cases, the node will be evaluated
     * and, if its value is invalid, a new node will be created.
     *
     * @param random Random factory.
     * @param node   Node to be evaluated.
     * @return Returns an individual simplified for the population.
     */
    private Node getIndividual(Random random, Node node) {
        Node nodeToReturn = probability.nextEvent(random) ? node.simplify() : node;

        if (nodeToReturn instanceof Constant && Double.isNaN(nodeToReturn.evaluate(new double[0]))) {
            // nodeToReturn = this.factory.generateRandomCandidate(random, node.getType());
            nodeToReturn = new Constant(random.nextDouble());
            nodeToReturn = nodeToReturn.simplify();
            nodeToReturn = getIndividual(random, nodeToReturn);
        }

        return nodeToReturn;
    }
}
