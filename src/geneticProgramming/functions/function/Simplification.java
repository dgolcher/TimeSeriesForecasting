package geneticProgramming.functions.function;

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

    public Simplification()
    {
        this(Probability.ONE);
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
            evolved.add(probability.nextEvent(random) ? node.simplify() : node);
        }

        return evolved;
    }
}
