package geneticProgramming.geneticOperators;

import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;

/**
 * Termination condition used for stagnation.
 *
 * This class is used as an alternative for Stagnated Termination condition, when using islands. This is necessary
 * because the island strategy implemented by Watchmaker only executes the termination conditions in the final of an
 * epoch. When a new epoch starts, the count of generations (the unit of work used in Stagnated class) is reset.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 17/08/13
 * Time: 23:12
 */
public class StagnationOverEpochs implements TerminationCondition
{

    private int generationLimit;
    private boolean naturalFitness;

    /**
     * Since the value of generationNumber in PopulationData object refers to an epoch's generation number, this
     * variable must count the number of generations in all process (not considering the change of epochs).
     */
    private int globalGenerationNumber;
    /**
     * This variable is used as an auxiliary variable to count the globalGenerationNumber.
     */
    private int epochSize;
    private double bestMeanFitness;
    private int fittestGeneration;

    /**
     * Constructor method.
     *
     * @param generationLimit Limit of generations that the engine must tolerate without any improve in the best
     *                        candidate's fitness value.
     * @param naturalFitness  Verify if the fitness evaluation is natural or not.
     * @param epochSize       Epoch size.
     */
    public StagnationOverEpochs(int generationLimit, boolean naturalFitness, int epochSize)
    {
        this.generationLimit        = generationLimit;
        this.naturalFitness         = naturalFitness;
        this.globalGenerationNumber = 0;
        this.epochSize              = epochSize;

        this.bestMeanFitness   = naturalFitness ? Double.MIN_VALUE : Double.MAX_VALUE;
        this.fittestGeneration = 0;
    }

    @Override
    public boolean shouldTerminate(PopulationData<?> populationData)
    {
        double fitness = populationData.getBestCandidateFitness();
        // This calculus is made because there's no way to know which is the current generation, since Watchmaker counts
        // generations inside the epochs.
        this.globalGenerationNumber += this.epochSize;
        if (this.hasFitnessImproved(fitness)) {
            this.bestMeanFitness = fitness;
            this.fittestGeneration = this.globalGenerationNumber;
        }

        return this.globalGenerationNumber - this.fittestGeneration >= this.generationLimit;
    }

    /**
     * Determine whether the population fitness is better than the best seen so far.
     *
     * @param fitness The fitness of the current population (either best fitness or mean
     *                fitness depending on how the termination condition is configured).
     *
     * @return True if the fitness has improved in the current generation, false otherwise.
     */
    private boolean hasFitnessImproved(double fitness)
    {
        return (naturalFitness && fitness > this.bestMeanFitness)
                || (!naturalFitness && fitness < this.bestMeanFitness);
    }

}
