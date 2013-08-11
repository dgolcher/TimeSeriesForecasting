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
    private int timeSeriesSize;
    private int initOfTrainingData;
    private int endOfTrainingData;
    private int initOfTestingData;
    private int endOfTestingData;
    private boolean verboseModeActivated;
    private int logInterval;
    private int windowSize;

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

    /**
     * Loads all configuration data using an instance of weka.core.Instance. This kind of object is result of reading
     * the ARFF files.
     *
     * @param instance Object containing all configurations that will be used in an instance of Configurable object.
     */
    @Override
    public void loadConfigurationFromWekaInstance(Instance instance) {
        this.setTimeSeriesFilePath(String.valueOf(instance.value(0)));
        this.setTimeSeriesSize((int) instance.value(1));
        this.setInitOfTrainingData((int) instance.value(2));
        this.setEndOfTrainingData((int) instance.value(3));
        this.setInitOfTestingData((int) instance.value(4));
        this.setEndOfTestingData((int) instance.value(5));
        this.setVerboseModeActivated(instance.value(6) == 1);
        this.setLogInterval((int) instance.value(7));
        this.setWindowSize((int) instance.value(8));
    }
}
