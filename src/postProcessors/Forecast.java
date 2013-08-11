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
     * @param originalTimeSeries Full time series used to train and test the model.
     * @param configuration      GPConfiguration instance, used get some information about the forecasting.
     * @param bestCandidate      Individual provided by the GP Machine, consider the best candidate found.
     */
    public Forecast(ArrayList<TimeNode> originalTimeSeries, GPConfiguration configuration, Node bestCandidate)
    {
        this.originalTimeSeries = originalTimeSeries;
        this.configuration      = configuration;
        this.model              = bestCandidate;
        this.initializeForecastingData(configuration);
    }

    /**
     * This method executes the forecasting using the model provided to this class.
     *
     * @return Returns the forecasted data.
     */
    public ArrayList<TimeNode> processForecasting()
    {
        int forecastHorizon = this.configuration.getEndOfTrainingData() - this.configuration.getInitOfTrainingData();
        for (int i = 0; i < forecastHorizon; i++) {
            double[] params = new double[forecastHorizon];
            for (int j = 0; j < this.configuration.getWindowSize(); j++) {
                int index = (this.forecastedTimeSeries.size() + j) - forecastHorizon;
                params[j] = this.forecastedTimeSeries.get(index).getValue();
            }

            double forecastedValue = this.model.evaluate(params);
            // @todo Verify how to get the date values.
            TimeNode node = new TimeNode();
            node.setDate(null);
            node.setValue(forecastedValue);
            this.forecastedTimeSeries.add(node);
        }

        return this.forecastedTimeSeries;
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
