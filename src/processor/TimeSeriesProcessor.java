package processor;

import geneticProgramming.configuration.GPConfiguration;
import geneticProgramming.configuration.IslandConfiguration;
import geneticProgramming.functions.Node;
import model.TimeNode;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import org.uncommons.watchmaker.framework.islands.IslandEvolutionObserver;
import org.uncommons.watchmaker.framework.islands.RingMigration;
import org.uncommons.watchmaker.framework.termination.GenerationCount;
import org.uncommons.watchmaker.framework.termination.Stagnation;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
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

    private Logger                         logger;

    public TimeSeriesProcessor(String gpConfigurationFilePath, String islandConfigurationFilePath, int i)
    {
        this.gpConfigurationFilePath     = gpConfigurationFilePath;
        this.islandConfigurationFilePath = islandConfigurationFilePath;
        this.gpConfiguration             = new GPConfiguration();
        this.islandConfiguration         = new ArrayList<IslandConfiguration>();
        this.logger                      = new Logger("LINEAR TEST"+i);
    }

    public void run() throws Exception
    {
        this.loadConfigurations();
        // Creating log files and object.
        this.getData();
        List<EvolutionEngine<Node>> islands = this.getIslands();
        this.preProcessData();
        this.bestCandidate = this.processData(islands);
        ArrayList<TimeNode> forecastedTimeSeries = this.getForecastedTimeSeries();
        forecastedTimeSeries = this.postProcessingData(forecastedTimeSeries);
        // Presenting results.
        for (TimeNode node : forecastedTimeSeries) {
            System.out.print(node.getValue() + ", ");
        }
        // Comparing forecasted data with the real testing data.
        // Save log file.
        this.logger.commitLogFile();
    }

    private void loadConfigurations() throws IOException, InstantiationException, IllegalAccessException
    {
        ConfigurationLoader.loadConfigurations(this.gpConfigurationFilePath, this.gpConfiguration);
        ConfigurationLoader.loadConfigurations (
            this.islandConfigurationFilePath, this.islandConfiguration, IslandConfiguration.class
        );
        this.logger.logConfigurations(this.gpConfiguration, this.islandConfiguration);
    }

    private void getData() throws Exception
    {
        DataProvider dataProvider = new DataProvider(gpConfiguration);
        this.originalTimeSeries   = dataProvider.getFullTimeSeriesData();
        this.trainingData         = dataProvider.getTrainingData();
        this.testingData          = dataProvider.getTestingData();

        this.logger.logDataSets(this.originalTimeSeries, this.trainingData, this.testingData, this.gpConfiguration);
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
     *
     * @param islands Islands.
     *
     * @return Return the best candidate.
     */
    private Node processData(List<EvolutionEngine<Node>> islands)
    {
        IslandEvolution<Node> engine = this.getEvolutionEngine(islands);
        TerminationCondition[] terminationConditions = this.getTerminationConditions();
        return engine.evolve(
            this.gpConfiguration.getPopulationSize(),
            this.gpConfiguration.getElitePopulationSize(),
            this.gpConfiguration.getEpochLength(),
            this.gpConfiguration.getMigrationCount(),
            terminationConditions
        );
    }

    /**
     * @todo this method was not implemented yet.
     *
     * This method reverts all modifications made over the data in preProcessData method. This method have also to
     * make the same modifications over the forecasted data (all values produced).
     */
    private ArrayList<TimeNode> postProcessingData(ArrayList<TimeNode> forecastedTimeSeries)
    {
        // This method must undo all modifications in data made by the preProcessData method.
        return forecastedTimeSeries;
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

    /**
     * Return termination conditions enabled by the gpConfiguration object.
     *
     * @return Return the set of termination conditions enabled to this execution.
     */
    private TerminationCondition[] getTerminationConditions()
    {
        ArrayList<TerminationCondition> terminationConditions = new ArrayList<TerminationCondition>();

        if (this.gpConfiguration.isEnableTerminationByFitness()) {
            terminationConditions.add(new TargetFitness(
                this.gpConfiguration.getFitnessValue(), this.gpConfiguration.isFitnessNatural())
            );
        }

        if (this.gpConfiguration.isEnableGenerationCount()) {
            terminationConditions.add(new GenerationCount(this.gpConfiguration.getGenerationCount()));
        }

        if (this.gpConfiguration.isEnableStagnationGenerationCount()) {
            terminationConditions.add(
                new Stagnation(this.gpConfiguration.getStagnatedGenerationsLimit(),
                this.gpConfiguration.isFitnessNatural())
            );
        }

        // @todo try to make this block better.
        TerminationCondition[] conditions = new TerminationCondition[terminationConditions.size()];
        for (int i = 0; i < conditions.length; i++) {
            conditions[i] = terminationConditions.get(i);
        }

        return conditions;
    }

    private IslandEvolution<Node> getEvolutionEngine(List<EvolutionEngine<Node>> islands) {
        IslandEvolution<Node> evolutionEngine = new IslandEvolution<Node> (
            islands,
            new RingMigration(),
            this.gpConfiguration.isFitnessNatural(),
            new MersenneTwisterRNG()
        );
        this.addEvolutionObservers(evolutionEngine, this.gpConfiguration);

        return evolutionEngine;
    }

    /**
     * @todo Verify if it's possible to use this method (or something like this) to get updated about issues over islands, like migration, etc.
     *
     * @param engine        PG engine, where the observer methods will be created.
     * @param configuration GP configuration.
     */
    private void addEvolutionObservers(IslandEvolution<Node> engine, final GPConfiguration configuration)
    {
        engine.addEvolutionObserver(new IslandEvolutionObserver<Node>() {
            @Override
            public void islandPopulationUpdate(int i, PopulationData<? extends Node> populationData) {
                printEvolutionLog(i, populationData, configuration);
            }

            @Override
            public void populationUpdate(PopulationData<? extends Node> populationData) {
//                printEvolutionLog(populationData, configuration);
            }
        });
    }

    /**
     * This method prints all data about the evolution (if it is parametrized for it).
     *
     * @param islandIndex    Island index.
     * @param populationData Data about the evolution process.
     * @param configuration  GPConfiguration object.
     */
    private void printEvolutionLog(int islandIndex, PopulationData<? extends Node> populationData,
                                   GPConfiguration configuration) {
        if (configuration.isVerboseModeActivated()) {
            String evolutionLog = "";

//            if (populationData.getGenerationNumber() % configuration.getLogInterval() == 0) {
                evolutionLog += "\nIsland #" + islandIndex;
                evolutionLog += "\nGeneration: " + populationData.getGenerationNumber();
                evolutionLog += "\n\tBest Solution: " + populationData.getBestCandidate();
                evolutionLog += "\n\tIts Fitness is: " + populationData.getBestCandidateFitness();
                evolutionLog += "\n\tPopulation size: " + populationData.getPopulationSize();
                evolutionLog += "\n-----------------------------------------------------------";
                System.out.println(evolutionLog);
//            }

            if (populationData.getBestCandidateFitness() == configuration.getFitnessValue()) {
                evolutionLog += "\n=============================================================";
                evolutionLog += "\n======================== FINAL RESULT =======================";
                evolutionLog += "\n=============================================================";
                evolutionLog += "\nIsland #" + islandIndex;
                evolutionLog += "\nGeneration: " + populationData.getGenerationNumber();
                evolutionLog += "\n\tBest Solution: " + populationData.getBestCandidate();
                evolutionLog += "\n\tIts Fitness is: " + populationData.getBestCandidateFitness();
                evolutionLog += "\n\tPopulation size: " + populationData.getPopulationSize();
                evolutionLog += "\n-----------------------------------------------------------";
                System.out.println(evolutionLog);
            }

            logger.logEvolution(evolutionLog);
        }
    }

    /**
     * This method prints all data about the evolution (if it is parametrized for it).
     *
     * @param populationData Data about the evolution process.
     * @param configuration  GPConfiguration object.
     */
    private void printEvolutionLog(PopulationData<? extends Node> populationData,
                                   GPConfiguration configuration) {
        if (configuration.isVerboseModeActivated()) {
            String evolutionLog = "";

            if (populationData.getGenerationNumber() % configuration.getLogInterval() == 0) {
                evolutionLog += "\nGeneration: " + populationData.getGenerationNumber();
                evolutionLog += "\n\tBest Solution: " + populationData.getBestCandidate();
                evolutionLog += "\n\tIts Fitness is: " + populationData.getBestCandidateFitness();
                evolutionLog += "\n\tPopulation size: " + populationData.getPopulationSize();
                evolutionLog += "\n-----------------------------------------------------------";
                System.out.println(evolutionLog);
            }

            if (populationData.getBestCandidateFitness() == configuration.getFitnessValue()) {
                evolutionLog += "\n=============================================================";
                evolutionLog += "\n======================== FINAL RESULT =======================";
                evolutionLog += "\n=============================================================";
                evolutionLog += "\nGeneration: " + populationData.getGenerationNumber();
                evolutionLog += "\n\tBest Solution: " + populationData.getBestCandidate();
                evolutionLog += "\n\tIts Fitness is: " + populationData.getBestCandidateFitness();
                evolutionLog += "\n\tPopulation size: " + populationData.getPopulationSize();
                evolutionLog += "\n-----------------------------------------------------------";
                System.out.println(evolutionLog);
            }

            logger.logEvolution(evolutionLog);
        }
    }

}