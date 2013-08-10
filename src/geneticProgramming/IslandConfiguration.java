package geneticProgramming;

import weka.core.Instance;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 10/08/13
 * Time: 01:19
 */
@SuppressWarnings("UnusedDeclaration")
public class IslandConfiguration
{

    private int populationSize;
    private int windowSize;
    private int maxInitSizeTree;
    private int maxSizeTree;
    private double leafProbability;
    private boolean enableElitism;
    private int elitePopulationSize;
    private boolean enableCrossover;
    private boolean enableMutation;
    private double mutationProbability;
    private boolean enablePlague;
    private double survivorPlagueProbability;
    private int totalOfPlagueSpreads;
    private int generationsBeforePlague;
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
    private boolean enableStatisticOperators;
    private boolean enableTrigonometricalOperators;


    public IslandConfiguration(Instance instance)
    {
        this.setPopulationSize((int) instance.value(0));
        this.setWindowSize((int) instance.value(1));
        this.setMaxInitSizeTree((int) instance.value(2));
        this.setMaxSizeTree((int) instance.value(3));
        this.setLeafProbability(instance.value(4));
        this.setEnableElitism(instance.value(5) == 1);
        this.setElitePopulationSize((int) instance.value(6));
        this.setEnableCrossover(instance.value(7) == 1);
        this.setEnableMutation(instance.value(8) == 1);
        this.setMutationProbability(instance.value(9));
        this.setEnablePlague(instance.value(10) == 1);
        this.setSurvivorPlagueProbability(instance.value(11));
        this.setTotalOfPlagueSpreads((int) instance.value(12));
        this.setGenerationsBeforePlague((int) instance.value(13));
        this.setFitnessStrategy((int) instance.value(14));
        this.setFitnessValue((int) instance.value(15));
        this.setFitnessNatural(instance.value(16) == 1);
        this.setEnableGenerationCount(instance.value(17) == 1);
        this.setGenerationCount((int) instance.value(18));
        this.setEnableStagnationGenerationCount(instance.value(19) == 1);
        this.setStagnatedGenerationsLimit((int) instance.value(20));
        this.setEnableTerminationByFitness(instance.value(21) == 1);
        this.setEnableBasicOperators(instance.value(22) == 1);
        this.setEnableComplexOperators(instance.value(23) == 1);
        this.setEnableLogicOperators(instance.value(24) == 1);
        this.setEnableStatisticOperators(instance.value(25) == 1);
        this.setEnableTrigonometricalOperators(instance.value(26) == 1);
    }

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

    public int getMaxInitSizeTree()
    {
        return maxInitSizeTree;
    }

    public void setMaxInitSizeTree(int maxInitSizeTree)
    {
        this.maxInitSizeTree = maxInitSizeTree;
    }

    public int getMaxSizeTree()
    {
        return maxSizeTree;
    }

    public void setMaxSizeTree(int maxSizeTree)
    {
        this.maxSizeTree = maxSizeTree;
    }

    public double getLeafProbability()
    {
        return leafProbability;
    }

    public void setLeafProbability(double leafProbability)
    {
        this.leafProbability = leafProbability;
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

    public int getGenerationsBeforePlague()
    {
        return generationsBeforePlague;
    }

    public void setGenerationsBeforePlague(int generationsBeforePlague)
    {
        this.generationsBeforePlague = generationsBeforePlague;
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

    public boolean isFitnessNatural()
    {
        return isFitnessNatural;
    }

    public void setFitnessNatural(boolean fitnessNatural)
    {
        isFitnessNatural = fitnessNatural;
    }

    public boolean isEnableGenerationCount()
    {
        return enableGenerationCount;
    }

    public void setEnableGenerationCount(boolean enableGenerationCount)
    {
        this.enableGenerationCount = enableGenerationCount;
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

    public boolean isEnableStatisticOperators()
    {
        return enableStatisticOperators;
    }

    public void setEnableStatisticOperators(boolean enableStatisticOperators)
    {
        this.enableStatisticOperators = enableStatisticOperators;
    }

    public boolean isEnableTrigonometricalOperators()
    {
        return enableTrigonometricalOperators;
    }

    public void setEnableTrigonometricalOperators(boolean enableTrigonometricalOperators)
    {
        this.enableTrigonometricalOperators = enableTrigonometricalOperators;
    }
}