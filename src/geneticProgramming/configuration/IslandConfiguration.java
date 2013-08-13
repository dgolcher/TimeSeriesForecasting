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
    private int maxInitTreeDepth;
    private int maxTreeDepth;
    private double leafProbability;
    private int selectionStrategy;
    private double selectionProbability;
    private double selectionRatio;
    private boolean enableElitism;
    private boolean enableCrossover;
    private boolean enableMutation;
    private double mutationProbability;
    private boolean enablePlague;
    private double survivorPlagueProbability;
    private int totalOfPlagueSpreads;
    private int generationsCountBeforePlague;
    private int fitnessStrategy;
    private boolean enableBasicOperators;
    private boolean enableComplexOperators;
    private boolean enableLogicOperators;
    private boolean enableStatisticsOperators;
    private boolean enableTrigonometricOperators;

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
        this.setMaxInitTreeDepth((int) instance.value(0));
        this.setMaxTreeDepth((int) instance.value(1));
        this.setLeafProbability(instance.value(2));
        this.setSelectionStrategy((int) instance.value(3));
        this.setSelectionProbability(instance.value(4));
        this.setSelectionRatio(instance.value(5));
        this.setEnableElitism(instance.value(6) == 1);
        this.setEnableCrossover(instance.value(7) == 1);
        this.setEnableMutation(instance.value(8) == 1);
        this.setMutationProbability(instance.value(9));
        this.setEnablePlague(instance.value(10) == 1);
        this.setSurvivorPlagueProbability(instance.value(11));
        this.setTotalOfPlagueSpreads((int) instance.value(12));
        this.setGenerationsCountBeforePlague((int) instance.value(13));
        this.setFitnessStrategy((int) instance.value(14));
        this.setEnableBasicOperators(instance.value(15) == 1);
        this.setEnableComplexOperators(instance.value(16) == 1);
        this.setEnableLogicOperators(instance.value(17) == 1);
        this.setEnableStatisticsOperators(instance.value(18) == 1);
        this.setEnableTrigonometricOperators(instance.value(19) == 1);
    }
}
