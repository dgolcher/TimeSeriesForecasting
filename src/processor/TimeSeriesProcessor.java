package processor;

import geneticProgramming.configuration.GPConfiguration;
import geneticProgramming.configuration.IslandConfiguration;
import geneticProgramming.functions.Node;
import model.TimeNode;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import org.uncommons.watchmaker.framework.islands.RingMigration;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import postProcessors.Forecast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 10/08/13
 * Time: 20:28
 */
// @todo at the end of the implementation of this class, remove this line and the other below.
@SuppressWarnings("UnusedDeclaration")
public class TimeSeriesProcessor
{

    private GPConfiguration                gpConfiguration;
    private ArrayList<IslandConfiguration> islandConfiguration;
    private String                         gpConfigurationFilePath;
    private String                         islandConfigurationFilePath;

    private ArrayList<TimeNode>            originalTimeSeries;
    private ArrayList<TimeNode>            trainingData;
    private ArrayList<TimeNode>            testingData;
    private Node                           bestCandidate;

    public TimeSeriesProcessor(String gpConfigurationFilePath, String islandConfigurationFilePath)
    {
        this.gpConfigurationFilePath     = gpConfigurationFilePath;
        this.islandConfigurationFilePath = islandConfigurationFilePath;
        this.gpConfiguration             = new GPConfiguration();
        this.islandConfiguration         = new ArrayList<IslandConfiguration>();
    }

    public void run() throws Exception
    {
        // Reading configuration files of GP and islands.
        // Loading all configurations.
        this.loadConfigurations();
        // Creating log files and object.
        // Loading training and testing time series.
        this.getData();
        // Initializing components and islands.
        List<EvolutionEngine<Node>> islands = this.getIslands();
        // Pre-processing data.
        this.preProcessData();
        // Process data.
        this.bestCandidate = this.processData(islands);
        // Process the forecasting for the next n periods.
        ArrayList<TimeNode> forecastedTimeSeries = this.getForecastedTimeSeries();
        // Post-processing data.
        this.postProcessingData(forecastedTimeSeries);
        // Presenting results.
        // Comparing forecasted data with the real testing data.
    }

    private void loadConfigurations() throws IOException, InstantiationException, IllegalAccessException
    {
        ConfigurationLoader.loadConfigurations(this.gpConfigurationFilePath, this.gpConfiguration);
        ConfigurationLoader.loadConfigurations (
            this.islandConfigurationFilePath, this.islandConfiguration, IslandConfiguration.class
        );
    }

    private void getData() throws Exception
    {
        DataProvider dataProvider = new DataProvider(gpConfiguration);
        this.originalTimeSeries   = dataProvider.getFullTimeSeriesData();
        this.trainingData         = dataProvider.getTrainingData();
        this.testingData          = dataProvider.getTestingData();
    }

    private List<EvolutionEngine<Node>> getIslands() throws Exception
    {
        return IslandBuilder.build(this.islandConfiguration, this.gpConfiguration, this.trainingData);
    }

    /**
     * @todo this method was not implemented yet.
     *
     * This method processes all the modifications required to produce a better forecasting. All modifications made
     * over the data must be reverted after by the method postProcessingData.
     */
    private void preProcessData()
    {
        // This method must execute the pre-processing methods.
    }

    /**
     * @todo Verify if it's interesting to create a new migration strategy.
     * @todo Implement the way to set the evolution verbose.
     * @todo Verify if it's necessary to transport the configuration "populationSize" from islandConfigurations to GPConfigurations.
     * @todo Transport the configurations of termination conditions from islandsConfigurations to GPConfigurations.
     * @todo Create configuration for epoch length.
     * @todo Create configuration for migration count.
     *
     * @param islands Islands.
     *
     * @return Return the best candidate.
     */
    private Node processData(List<EvolutionEngine<Node>> islands)
    {
        IslandEvolution<Node> engine = new IslandEvolution<Node> (
            islands,
            new RingMigration(),
            this.islandConfiguration.get(0).isFitnessNatural(),
            new MersenneTwisterRNG()
        );
        return engine.evolve(
            this.islandConfiguration.get(0).getPopulationSize(),
            this.islandConfiguration.get(0).getElitePopulationSize(),
            10, // this.islandConfiguration.get(0).epochLength()
            10, // this.islandConfiguration.get(0).migrationCount()
            new GenerationCount(100000)
        );
    }

    /**
     * @todo this method was not implemented yet.
     *
     * This method reverts all modifications made over the data in preProcessData method. This method have also to
     * make the same modifications over the forecasted data (all values produced).
     */
    private void postProcessingData(ArrayList<TimeNode> forecastedTimeSeries)
    {
        // This method must undo all modifications in data made by the preProcessData method.
    }

    /**
     * This method executes the forecasting of values. It will use the best candidate found in the execution of GP
     * Program and then, return that data.
     *
     * @return Return the time series forecasted (this time series is composed by a portion of original data, generally
     * the data used to train the GP Machine + the data forecasted).
     */
    private ArrayList<TimeNode> getForecastedTimeSeries()
    {
        Forecast forecast = new Forecast(this.originalTimeSeries, this.gpConfiguration, this.bestCandidate);
        return forecast.processForecasting();
    }

}