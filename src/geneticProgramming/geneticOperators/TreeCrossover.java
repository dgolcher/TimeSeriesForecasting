package geneticProgramming.geneticOperators;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;
import geneticProgramming.functions.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 18/06/13
 * Time: 22:09
 */
public class TreeCrossover extends AbstractCrossover<Node>
{

    private int maxDepth;

    /**
     * Creates a single-point crossover operator.
     */
    public TreeCrossover()
    {
        super(1);
    }

    public TreeCrossover(int maxDepth)
    {
        super(1);
        this.maxDepth = maxDepth;
    }

    @Override
    protected List<Node> mate(Node node, Node node2, int numberOfCrossoverPoints, Random random)
    {
        List<Node> offspring = new ArrayList<Node>(2);
        Node offspring1 = node;
        Node offspring2 = node2;

        for (int i = 0; i < numberOfCrossoverPoints; i++) {
            int crossoverPoint1;
            Node subtree1;
            int crossoverPoint2;
            Node subtree2;

            // Trying to guarantee that aways a crossover will be made with nodes of same type. It's important
            // to produce consistent programs (avoid something like if (arg0) or TRUE + FALSE).
            boolean validCrossover = false;
            while (!validCrossover) {
                crossoverPoint1 = random.nextInt(node.countNodes());
                subtree1        = node.getNode(crossoverPoint1);

                crossoverPoint2 = random.nextInt(node2.countNodes());
                subtree2        = node.getNode(crossoverPoint2);

                if (subtree1.getType() == subtree2.getType()) {
                    validCrossover = true;
                } /*else {
                    offspring1 = node.replaceNode(crossoverPoint1, subtree2);
                    offspring2 = node2.replaceNode(crossoverPoint2, subtree1);
                    if (this.maxDepth > 0 &&
                        (offspring1.getDepth() > this.maxDepth || offspring2.getDepth() > this.maxDepth)) {
                        validCrossover = false;
                    }
                }*/
            }

        }

        offspring.add(offspring1);
        offspring.add(offspring2);
        return offspring;
    }
}
