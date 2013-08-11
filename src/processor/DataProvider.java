package processor;

import dataReader.ArffFileReader;
import dataReader.FileReader;
import geneticProgramming.configuration.GPConfiguration;
import model.TimeNode;
import timeSeriesBuilder.ArffTimeSeriesBuilder;

import java.util.ArrayList;

/**
 * Data Provider
 *
 * This class is responsible for reading the reading the data file (this file is represented in the configuration file)
 * and selecting what is the training data and what is testing data.
 * This class also provides interfaces to deliver these two set of data.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 11/08/13
 * Time: 03:10
 */
public class DataProvider
{

    private ArrayList<TimeNode> fullTimeSeriesData;
    private int initOfTrainingData;
    private int endOfTrainingData;
    private int initOfTestingData;
    private int endOfTestingData;

    /**
     * Constructor method.
     * This method initializes all attributes of this class.
     *
     * @param configuration Configuration object.
     */
    public DataProvider(GPConfiguration configuration)
    {
        this.initializeAttributes(configuration);
    }

    /**
     * Return the entire time series data.
     *
     * @return Return the entire time series data
     */
    public ArrayList<TimeNode> getFullTimeSeriesData()
    {
        return this.fullTimeSeriesData;
    }

    /**
     * Returns the part of the time series chosen (in the config object) to be used as Training data by the GP Machine.
     *
     * @return A portion of the original time series, used to train the GP Machine.
     * @throws Exception
     */
    public ArrayList<TimeNode> getTrainingData() throws Exception
    {
        ArrayList<TimeNode> trainingSeries = new ArrayList<TimeNode>();
        this.validateDataBoundaries(this.initOfTrainingData, this.endOfTrainingData);
        for (int i = this.initOfTrainingData; i < this.endOfTrainingData; i++) {
            trainingSeries.add(this.fullTimeSeriesData.get(i));
        }

        return trainingSeries;
    }

    /**
     * Returns the part of the time series chosen (in the config object) to be used to test the model found by the GP
     * machine.
     *
     * @return A portion of the original time series, used to test the results of the GP Machine.
     * @throws Exception
     */
    public ArrayList<TimeNode> getTestingData() throws Exception
    {
        ArrayList<TimeNode> testingTimeSeries = new ArrayList<TimeNode>();
        this.validateDataBoundaries(this.initOfTestingData, this.endOfTestingData);
        for (int i = this.initOfTestingData; i < this.endOfTestingData; i++) {
            testingTimeSeries.add(this.fullTimeSeriesData.get(i));
        }

        return testingTimeSeries;
    }

    /**
     * Auxiliary method used to set up this object.
     *
     * @param configuration Configuration object.
     */
    private void initializeAttributes(GPConfiguration configuration)
    {
        String filePath                = configuration.getTimeSeriesFilePath();
        FileReader reader              = new ArffFileReader(filePath);
        ArffTimeSeriesBuilder builder  = new ArffTimeSeriesBuilder(reader);
        this.initOfTrainingData        = configuration.getInitOfTrainingData();
        this.endOfTrainingData         = configuration.getEndOfTrainingData();
        this.initOfTestingData         = configuration.getInitOfTestingData();
        this.endOfTestingData          = configuration.getEndOfTestingData();
        this.fullTimeSeriesData        = builder.getTimeSeries();
    }

    /**
     * This method is used to validate the boundaries of test and train data, set in the configuration file. In cases
     * that these boundaries are considered invalid, this method will interrupt the process.
     *
     * @param init Index of the init point in the portion of time series used.
     * @param end  Index of the end point in the portion of time series used.
     * @throws Exception
     */
    private void validateDataBoundaries(int init, int end) throws Exception
    {
        if (init < 0 || end < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (init >= this.fullTimeSeriesData.size() || end >= this.fullTimeSeriesData.size()) {
            throw new IndexOutOfBoundsException();
        }

        if (init > end) {
            throw new Exception("The init point of this series must be lesser than the end of it.");
        }

    }

}
