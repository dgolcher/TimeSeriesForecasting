package geneticProgramming.geneticOperators;

import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import geneticProgramming.terminal.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 17/06/13
 * Time: 13:53
 */
public class TreeMutation implements EvolutionaryOperator<Node>
{

    private final TreeFactory treeFactory;

    private final Probability mutationProbability;


    public TreeMutation(TreeFactory treeFactory, Probability mutationProbability)
    {
        this.treeFactory         = treeFactory;
        this.mutationProbability = mutationProbability;
    }

    @Override
    public List<Node> apply(List<Node> selectedCandidate, Random rng)
    {
        List<Node> mutatedPopulation = new ArrayList<Node>(selectedCandidate.size());
        for (Node tree : selectedCandidate) {
            mutatedPopulation.add(tree.mutate(rng, mutationProbability, treeFactory));
        }

        return mutatedPopulation;
    }
}
