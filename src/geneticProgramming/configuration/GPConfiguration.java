package geneticProgramming.configuration;

import weka.core.Instance;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 08/08/13
 * Time: 00:33
 */
@SuppressWarnings("UnusedDeclaration")
public class GPConfiguration implements Configurable
{

    private String timeSeriesFilePath;
    private int populationSize;
    private int elitePopulationSize;
    private int timeSeriesSize;
    private int initOfTrainingData;
    private int endOfTrainingData;
    private int initOfTestingData;
    private int endOfTestingData;
    private boolean verboseModeActivated;
    private int logInterval;
    private int windowSize;
    private int fitnessValue;
    private boolean isFitnessNatural;
    private boolean enableGenerationCount;
    private int generationCount;
    private boolean enableStagnationGenerationCount;
    private int stagnatedGenerationsLimit;
    private boolean enableTerminationByFitness;
    private int epochLength;
    private int migrationCount;

    public int getPopulationSize()
    {
        return populationSize;
    }

    public void setPopulationSize(int populationSize)
    {
        this.populationSize = populationSize;
    }

    public int getElitePopulationSize()
    {
        return elitePopulationSize;
    }

    public void setElitePopulationSize(int elitePopulationSize)
    {
        this.elitePopulationSize = elitePopulationSize;
    }

    public String getTimeSeriesFilePath()
    {
        return timeSeriesFilePath;
    }

    public void setTimeSeriesFilePath(String timeSeriesFilePath)
    {
        this.timeSeriesFilePath = timeSeriesFilePath;
    }

    public int getTimeSeriesSize()
    {
        return timeSeriesSize;
    }

    public void setTimeSeriesSize(int timeSeriesSize)
    {
        this.timeSeriesSize = timeSeriesSize;
    }

    public int getInitOfTrainingData()
    {
        return initOfTrainingData;
    }

    public void setInitOfTrainingData(int initOfTrainingData)
    {
        this.initOfTrainingData = initOfTrainingData;
    }

    public int getEndOfTrainingData()
    {
        return endOfTrainingData;
    }

    public void setEndOfTrainingData(int endOfTrainingData)
    {
        this.endOfTrainingData = endOfTrainingData;
    }

    public int getInitOfTestingData()
    {
        return initOfTestingData;
    }

    public void setInitOfTestingData(int initOfTestingData)
    {
        this.initOfTestingData = initOfTestingData;
    }

    public int getEndOfTestingData()
    {
        return endOfTestingData;
    }

    public void setEndOfTestingData(int endOfTestingData)
    {
        this.endOfTestingData = endOfTestingData;
    }

    public boolean isVerboseModeActivated()
    {
        return verboseModeActivated;
    }

    public void setVerboseModeActivated(boolean verboseModeActivated)
    {
        this.verboseModeActivated = verboseModeActivated;
    }

    public int getLogInterval()
    {
        return logInterval;
    }

    public void setLogInterval(int logInterval)
    {
        this.logInterval = logInterval;
    }

    public int getWindowSize()
    {
        return windowSize;
    }

    public void setWindowSize(int windowSize)
    {
        this.windowSize = windowSize;
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

    public int getEpochLength()
    {
        return epochLength;
    }

    
    public void setEpochLength(int epochLength) {
        this.epochLength = epochLength;
    }

    public int getMigrationCount()
    {
        return migrationCount;
    }


    public void setMigrationCount(int migrationCount)
    {
        this.migrationCount = migrationCount;
    }

    /**
     * Loads all configuration data using an instance of weka.core.Instance. This kind of object is result of reading
     * the ARFF files.
     *
     * @param instance Object containing all configurations that will be used in an instance of Configurable object.
     */
    @Override
    public void loadConfigurationFromWekaInstance(Instance instance) {
        this.setTimeSeriesFilePath(instance.stringValue(0));
        this.setPopulationSize((int) instance.value(1));
        this.setElitePopulationSize((int) instance.value(2));
        this.setTimeSeriesSize((int) instance.value(3));
        this.setInitOfTrainingData((int) instance.value(4));
        this.setEndOfTrainingData((int) instance.value(5));
        this.setInitOfTestingData((int) instance.value(6));
        this.setEndOfTestingData((int) instance.value(7));
        this.setVerboseModeActivated(instance.value(8) == 1);
        this.setLogInterval((int) instance.value(9));
        this.setWindowSize((int) instance.value(10));
        this.setFitnessValue((int) instance.value(11));
        this.setFitnessNatural(instance.value(12) == 1);
        this.setEnableGenerationCount(instance.value(13) == 1);
        this.setGenerationCount((int) instance.value(14));
        this.setEnableStagnationGenerationCount(instance.value(15) == 1);
        this.setStagnatedGenerationsLimit((int) instance.value(16));
        this.setEnableTerminationByFitness(instance.value(17) == 1);
        this.setEpochLength((int) instance.value(18));
        this.setMigrationCount((int) instance.value(19));
    }
}
