package postProcessors;

import geneticProgramming.configuration.GPConfiguration;
import geneticProgramming.functions.Node;
import model.TimeNode;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 05/08/13
 * Time: 23:57
 *
 * @todo Verify if its necessary to get the entire time series (from the init of it until the point where is the test
 * of the model will be done) to the forecasted array.
 */
public class Forecast
{

    private ArrayList<TimeNode> originalTimeSeries;
    private ArrayList<TimeNode> forecastedTimeSeries;
    private GPConfiguration configuration;
    private Node                model;

    /**
     * Constructor.
     * This method initializes all instances used in this class.
     *
     * @param originalTimeSeries Original time series. In this class, it's used just to get the correct date for the
    *                            forecaste data.
     * @param trainingData       Data used to train the GP engine. It's used as initial part of the forecasted data.
     * @param configuration      GPConfiguration instance, used get some information about the forecasting.
     * @param bestCandidate      Individual provided by the GP Machine, consider the best candidate found.
     */
    public Forecast(ArrayList<TimeNode> originalTimeSeries, ArrayList<TimeNode> trainingData, 
                    GPConfiguration configuration, Node bestCandidate)
    {
        this.originalTimeSeries   = originalTimeSeries;
        this.configuration        = configuration;
        this.model                = bestCandidate;
        this.forecastedTimeSeries = trainingData;
        // this.initializeForecastingData(trainingData);
    }

    /**
     * This method executes the forecasting using the model provided to this class.
     *
     * @return Returns the forecasted data.
     */
    public ArrayList<TimeNode> processForecasting()
    {
        int forecastHorizon = this.configuration.getEndOfTestingData() - this.configuration.getInitOfTestingData() + 1;
        for (int i = 0; i < forecastHorizon; i++) {
            double[] params = this.getModelParams();
            double forecastedValue = this.model.evaluate(params);
            TimeNode node = new TimeNode();
            node.setDate(this.originalTimeSeries.get(this.forecastedTimeSeries.size()).getDate());
            node.setValue(forecastedValue);
            this.forecastedTimeSeries.add(node);
        }

        return this.forecastedTimeSeries;
    }

    /**
     * This method returns the set of params used by the model found by the forecast engine (the node representing the
     * expression found to describe the time series).
     *
     */
    private double[] getModelParams() 
    {
        double[] params = new double[this.configuration.getWindowSize()];
        for (int j = 0; j < this.configuration.getWindowSize(); j++) {
            int index = (this.forecastedTimeSeries.size() + j) - this.configuration.getWindowSize();
            params[j] = this.forecastedTimeSeries.get(index).getValue();
        }

        return params;
    }

    /**
     * This is an auxiliary method used to initialize the forecasting data.
     *
     * @param configuration Configuration used in the GP Machine.
     */
    private void initializeForecastingData(GPConfiguration configuration) {
        this.forecastedTimeSeries = new ArrayList<TimeNode>();
        for (int i = 0; i < configuration.getInitOfTestingData(); i++) {
            this.forecastedTimeSeries.add(this.originalTimeSeries.get(i));
        }
    }

}
