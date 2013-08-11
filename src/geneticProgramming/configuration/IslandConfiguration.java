package geneticProgramming.configuration;

import weka.core.Instance;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 10/08/13
 * Time: 20:48
 */
@SuppressWarnings("UnusedDeclaration")
public class IslandConfiguration implements Configurable
{
    private int populationSize;
    private int windowSize;
    private int maxInitTreeDepth;
    private int maxTreeDepth;
    private double leafProbability;
    private int selectionStrategy;
    private double selectionProbability;
    private double selectionRatio;
    private boolean enableElitism;
    private int elitePopulationSize;
    private boolean enableCrossover;
    private boolean enableMutation;
    private double mutationProbability;
    private boolean enablePlague;
    private double survivorPlagueProbability;
    private int totalOfPlagueSpreads;
    private int generationsCountBeforePlague;
    private int fitnessStrategy;
    private int fitnessValue;
    private boolean isFitnessNatural;
    private boolean enableGenerationCount;
    private int generationCount;
    private boolean enableStagnationGenerationCount;
    private int stagnatedGenerationsLimit;
    private boolean enableTerminationByFitness;
    private boolean enableBasicOperators;
    private boolean enableComplexOperators;
    private boolean enableLogicOperators;
    private boolean enableStatisticsOperators;
    private boolean enableTrigonometricOperators;

    public int getPopulationSize()
    {
        return populationSize;
    }

    public void setPopulationSize(int populationSize)
    {
        this.populationSize = populationSize;
    }

    public int getWindowSize()
    {
        return windowSize;
    }

    public void setWindowSize(int windowSize)
    {
        this.windowSize = windowSize;
    }

    public int getMaxInitTreeDepth()
    {
        return maxInitTreeDepth;
    }

    public void setMaxInitTreeDepth(int maxInitTreeDepth)
    {
        this.maxInitTreeDepth = maxInitTreeDepth;
    }

    public int getMaxTreeDepth()
    {
        return maxTreeDepth;
    }

    public void setMaxTreeDepth(int maxTreeDepth)
    {
        this.maxTreeDepth = maxTreeDepth;
    }

    public double getLeafProbability()
    {
        return leafProbability;
    }

    public void setLeafProbability(double leafProbability)
    {
        this.leafProbability = leafProbability;
    }

    public int getSelectionStrategy()
    {
        return selectionStrategy;
    }

    public void setSelectionStrategy(int selectionStrategy)
    {
        this.selectionStrategy = selectionStrategy;
    }

    public double getSelectionProbability()
    {
        return selectionProbability;
    }

    public void setSelectionProbability(double selectionProbability)
    {
        this.selectionProbability = selectionProbability;
    }

    public double getSelectionRatio()
    {
        return selectionRatio;
    }

    public void setSelectionRatio(double selectionRatio)
    {
        this.selectionRatio = selectionRatio;
    }

    public boolean isEnableElitism()
    {
        return enableElitism;
    }

    public void setEnableElitism(boolean enableElitism)
    {
        this.enableElitism = enableElitism;
    }

    public int getElitePopulationSize()
    {
        return elitePopulationSize;
    }

    public void setElitePopulationSize(int elitePopulationSize)
    {
        this.elitePopulationSize = elitePopulationSize;
    }

    public boolean isEnableCrossover()
    {
        return enableCrossover;
    }

    public void setEnableCrossover(boolean enableCrossover)
    {
        this.enableCrossover = enableCrossover;
    }

    public boolean isEnableMutation()
    {
        return enableMutation;
    }

    public void setEnableMutation(boolean enableMutation)
    {
        this.enableMutation = enableMutation;
    }

    public double getMutationProbability()
    {
        return mutationProbability;
    }

    public void setMutationProbability(double mutationProbability)
    {
        this.mutationProbability = mutationProbability;
    }

    public boolean isEnablePlague()
    {
        return enablePlague;
    }

    public void setEnablePlague(boolean enablePlague)
    {
        this.enablePlague = enablePlague;
    }

    public double getSurvivorPlagueProbability()
    {
        return survivorPlagueProbability;
    }

    public void setSurvivorPlagueProbability(double survivorPlagueProbability)
    {
        this.survivorPlagueProbability = survivorPlagueProbability;
    }

    public int getTotalOfPlagueSpreads()
    {
        return totalOfPlagueSpreads;
    }

    public void setTotalOfPlagueSpreads(int totalOfPlagueSpreads)
    {
        this.totalOfPlagueSpreads = totalOfPlagueSpreads;
    }

    public int getGenerationsCountBeforePlague()
    {
        return generationsCountBeforePlague;
    }

    public void setGenerationsCountBeforePlague(int generationsCountBeforePlague)
    {
        this.generationsCountBeforePlague = generationsCountBeforePlague;
    }

    public int getFitnessStrategy()
    {
        return fitnessStrategy;
    }

    public void setFitnessStrategy(int fitnessStrategy)
    {
        this.fitnessStrategy = fitnessStrategy;
    }

    public int getFitnessValue()
    {
        return fitnessValue;
    }

    public void setFitnessValue(int fitnessValue)
    {
        this.fitnessValue = fitnessValue;
    }

    public boolean isEnableGenerationCount()
    {
        return enableGenerationCount;
    }

    public void setEnableGenerationCount(boolean enableGenerationCount)
    {
        this.enableGenerationCount = enableGenerationCount;
    }

    public boolean isFitnessNatural() {
        return isFitnessNatural;
    }

    public void setFitnessNatural(boolean fitnessNatural) {
        isFitnessNatural = fitnessNatural;
    }

    public int getGenerationCount()
    {
        return generationCount;
    }

    public void setGenerationCount(int generationCount)
    {
        this.generationCount = generationCount;
    }

    public boolean isEnableStagnationGenerationCount()
    {
        return enableStagnationGenerationCount;
    }

    public void setEnableStagnationGenerationCount(boolean enableStagnationGenerationCount)
    {
        this.enableStagnationGenerationCount = enableStagnationGenerationCount;
    }

    public int getStagnatedGenerationsLimit()
    {
        return stagnatedGenerationsLimit;
    }

    public void setStagnatedGenerationsLimit(int stagnatedGenerationsLimit)
    {
        this.stagnatedGenerationsLimit = stagnatedGenerationsLimit;
    }

    public boolean isEnableTerminationByFitness()
    {
        return enableTerminationByFitness;
    }

    public void setEnableTerminationByFitness(boolean enableTerminationByFitness)
    {
        this.enableTerminationByFitness = enableTerminationByFitness;
    }

    public boolean isEnableBasicOperators()
    {
        return enableBasicOperators;
    }

    public void setEnableBasicOperators(boolean enableBasicOperators)
    {
        this.enableBasicOperators = enableBasicOperators;
    }

    public boolean isEnableComplexOperators()
    {
        return enableComplexOperators;
    }

    public void setEnableComplexOperators(boolean enableComplexOperators)
    {
        this.enableComplexOperators = enableComplexOperators;
    }

    public boolean isEnableLogicOperators()
    {
        return enableLogicOperators;
    }

    public void setEnableLogicOperators(boolean enableLogicOperators)
    {
        this.enableLogicOperators = enableLogicOperators;
    }

    public boolean isEnableStatisticsOperators()
    {
        return enableStatisticsOperators;
    }

    public void setEnableStatisticsOperators(boolean enableStatisticsOperators)
    {
        this.enableStatisticsOperators = enableStatisticsOperators;
    }

    public boolean isEnableTrigonometricOperators()
    {
        return enableTrigonometricOperators;
    }

    public void setEnableTrigonometricOperators(boolean enableTrigonometricOperators)
    {
        this.enableTrigonometricOperators = enableTrigonometricOperators;
    }

    /**
     * Loads all configuration data using an instance of weka.core.Instance. This kind of object is result of reading
     * the ARFF files.
     *
     * @param instance Object containing all configurations that will be used in an instance of Configurable object.
     */
    @Override
    public void loadConfigurationFromWekaInstance(Instance instance)
    {
        this.setPopulationSize((int) instance.value(0));
        this.setWindowSize((int) instance.value(1));
        this.setMaxInitTreeDepth((int) instance.value(2));
        this.setMaxTreeDepth((int) instance.value(3));
        this.setLeafProbability(instance.value(4));
        this.setSelectionStrategy((int) instance.value(5));
        this.setSelectionProbability(instance.value(6));
        this.setSelectionRatio(instance.value(7));
        this.setEnableElitism(instance.value(8) == 1);
        this.setElitePopulationSize((int) instance.value(9));
        this.setEnableCrossover(instance.value(10) == 1);
        this.setEnableMutation(instance.value(11) == 1);
        this.setMutationProbability(instance.value(12));
        this.setEnablePlague(instance.value(13) == 1);
        this.setSurvivorPlagueProbability(instance.value(14));
        this.setTotalOfPlagueSpreads((int) instance.value(15));
        this.setGenerationsCountBeforePlague((int) instance.value(16));
        this.setFitnessStrategy((int) instance.value(17));
        this.setFitnessValue((int) instance.value(18));
        this.setFitnessNatural(instance.value(19) == 1);
        this.setEnableGenerationCount(instance.value(20) == 1);
        this.setGenerationCount((int) instance.value(21));
        this.setEnableStagnationGenerationCount(instance.value(22) == 1);
        this.setStagnatedGenerationsLimit((int) instance.value(23));
        this.setEnableTerminationByFitness(instance.value(24) == 1);
        this.setEnableBasicOperators(instance.value(25) == 1);
        this.setEnableComplexOperators(instance.value(26) == 1);
        this.setEnableLogicOperators(instance.value(27) == 1);
        this.setEnableStatisticsOperators(instance.value(28) == 1);
        this.setEnableTrigonometricOperators(instance.value(29) == 1);
    }
}
