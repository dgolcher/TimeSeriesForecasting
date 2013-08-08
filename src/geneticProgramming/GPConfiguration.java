package geneticProgramming;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 08/08/13
 * Time: 00:33
 */
@SuppressWarnings("UnusedDeclaration")
public class GPConfiguration
{

    private int timeSeriesSize;
    private int trainingDataInit;
    private int trainingDataEnd;
    private int testingDataInit;
    private int testingDataEnd;
    private int populationSize;
    private int elitismCount;
    private int windowSize;
    private int maximumTreeDepth;
    private double leafNodeProbability;
    private double mutationProbability;
    private int maximumDepth;
    private double survivalProbability;
    private int amountOfPlagueSpreads;
    private int generationsBeforePlague;
    private boolean isNaturalFitness;
    private double targetFitness;
    private int maximumGenerationCount;
    private int stagnationLimit;
    private boolean verboseEvolve;
    private int printLogInterval;
    private int fitnessType;
    private int forecastHorizon;

    public int getTimeSeriesSize()
    {
        return timeSeriesSize;
    }

    public void setTimeSeriesSize(int timeSeriesSize)
    {
        this.timeSeriesSize = timeSeriesSize;
    }

    public int getTrainingDataInit()
    {
        return trainingDataInit;
    }

    public void setTrainingDataInit(int trainingDataInit)
    {
        this.trainingDataInit = trainingDataInit;
    }

    public int getTrainingDataEnd()
    {
        return trainingDataEnd;
    }

    public void setTrainingDataEnd(int trainingDataEnd)
    {
        this.trainingDataEnd = trainingDataEnd;
    }

    public int getTestingDataInit()
    {
        return testingDataInit;
    }

    public void setTestingDataInit(int testingDataInit)
    {
        this.testingDataInit = testingDataInit;
    }

    public int getTestingDataEnd()
    {
        return testingDataEnd;
    }

    public void setTestingDataEnd(int testingDataEnd)
    {
        this.testingDataEnd = testingDataEnd;
    }

    public int getPopulationSize()
    {
        return populationSize;
    }

    public void setPopulationSize(int populationSize)
    {
        this.populationSize = populationSize;
    }

    public int getElitismCount()
    {
        return elitismCount;
    }

    public void setElitismCount(int elitismCount)
    {
        this.elitismCount = elitismCount;
    }

    public int getWindowSize()
    {
        return windowSize;
    }

    public void setWindowSize(int windowSize)
    {
        this.windowSize = windowSize;
    }

    public int getMaximumTreeDepth()
    {
        return maximumTreeDepth;
    }

    public void setMaximumTreeDepth(int maximumTreeDepth)
    {
        this.maximumTreeDepth = maximumTreeDepth;
    }

    public double getLeafNodeProbability()
    {
        return leafNodeProbability;
    }

    public void setLeafNodeProbability(double leafNodeProbability)
    {
        this.leafNodeProbability = leafNodeProbability;
    }

    public double getMutationProbability()
    {
        return mutationProbability;
    }

    public void setMutationProbability(double mutationProbability)
    {
        this.mutationProbability = mutationProbability;
    }

    public int getMaximumDepth()
    {
        return maximumDepth;
    }

    public void setMaximumDepth(int maximumDepth)
    {
        this.maximumDepth = maximumDepth;
    }

    public double getSurvivalProbability()
    {
        return survivalProbability;
    }

    public void setSurvivalProbability(double survivalProbability)
    {
        this.survivalProbability = survivalProbability;
    }

    public int getAmountOfPlagueSpreads()
    {
        return amountOfPlagueSpreads;
    }

    public void setAmountOfPlagueSpreads(int amountOfPlagueSpreads)
    {
        this.amountOfPlagueSpreads = amountOfPlagueSpreads;
    }

    public int getGenerationsBeforePlague()
    {
        return generationsBeforePlague;
    }

    public void setGenerationsBeforePlague(int generationsBeforePlague)
    {
        this.generationsBeforePlague = generationsBeforePlague;
    }

    public boolean isNaturalFitness()
    {
        return isNaturalFitness;
    }

    public void setNaturalFitness(boolean naturalFitness)
    {
        isNaturalFitness = naturalFitness;
    }

    public double getTargetFitness()
    {
        return targetFitness;
    }

    public void setTargetFitness(double targetFitness)
    {
        this.targetFitness = targetFitness;
    }

    public int getMaximumGenerationCount()
    {
        return maximumGenerationCount;
    }

    public void setMaximumGenerationCount(int maximumGenerationCount)
    {
        this.maximumGenerationCount = maximumGenerationCount;
    }

    public int getStagnationLimit()
    {
        return stagnationLimit;
    }

    public void setStagnationLimit(int stagnationLimit)
    {
        this.stagnationLimit = stagnationLimit;
    }

    public boolean isVerboseEvolve()
    {
        return verboseEvolve;
    }

    public void setVerboseEvolve(boolean verboseEvolve)
    {
        this.verboseEvolve = verboseEvolve;
    }

    public int getPrintLogInterval()
    {
        return printLogInterval;
    }

    public void setPrintLogInterval(int printLogInterval)
    {
        this.printLogInterval = printLogInterval;
    }

    public int getFitnessType()
    {
        return fitnessType;
    }

    public void setFitnessType(int fitnessType)
    {
        this.fitnessType = fitnessType;
    }

    public int getForecastHorizon()
    {
        return forecastHorizon;
    }

    public void setForecastHorizon(int forecastHorizon)
    {
        this.forecastHorizon = forecastHorizon;
    }
}
