package geneticProgramming.geneticOperators;

import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;

/**
 * Termination condition used for counting generations
 *
 * This class is used as an alternative for counting generations Termination condition, when using islands.
 * This is necessary because the island strategy implemented by Watchmaker only executes the termination conditions in
 * the final of an epoch. When a new epoch starts, the count of generations (the unit of work used in Stagnated class)
 * is reset.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 18/08/13
 * Time: 03:14
 */
public class GenerationCountOverEpochs implements TerminationCondition
{
    private int maximumGenerationCount;

    private int epochSize;
    private int globalGenerationCount;

    public GenerationCountOverEpochs(int maximumGenerationCount, int epochSize)
    {
        this.maximumGenerationCount = maximumGenerationCount;
        this.epochSize              = epochSize;
        this.globalGenerationCount  = 0;
    }

    @Override
    public boolean shouldTerminate(PopulationData<?> populationData) {
        this.globalGenerationCount += this.epochSize;
        return this.globalGenerationCount >= this.maximumGenerationCount;
    }
}
