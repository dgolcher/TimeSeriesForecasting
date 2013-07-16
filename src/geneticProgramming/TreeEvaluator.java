package geneticProgramming;

import org.uncommons.watchmaker.framework.FitnessEvaluator;
import geneticProgramming.terminal.Node;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Paulo
 * Date: 18/06/13
 * Time: 22:26
 */
public class TreeEvaluator implements FitnessEvaluator<Node>
{

    private final Map<double[], Double> data;

    public TreeEvaluator(Map<double[], Double> data)
    {
        this.data = data;
    }


    @Override
    public double getFitness(Node candidate, List<? extends Node> population)
    {
        double error = 0;
        for (Map.Entry<double[], Double> entry : data.entrySet()) {
            double actualValue = candidate.evaluate(entry.getKey());
            double diff        = actualValue - entry.getValue();
            error += Math.pow(diff, 2);
        }

        if (error != 0) {
            error += candidate.countNodes();
        }

        candidate.setFitnessValue(error);
        return error;
    }

    @Override
    public boolean isNatural()
    {
        return false;
    }

}
