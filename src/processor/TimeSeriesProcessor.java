package processor;

import geneticProgramming.configuration.GPConfiguration;
import geneticProgramming.configuration.IslandConfiguration;
import geneticProgramming.functions.Node;
import geneticProgramming.geneticOperators.GenerationCountOverEpochs;
import geneticProgramming.geneticOperators.StagnationOverEpochs;
import model.TimeNode;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import org.uncommons.watchmaker.framework.islands.IslandEvolutionObserver;
import org.uncommons.watchmaker.framework.islands.RingMigration;
import org.uncommons.watchmaker.framework.termination.TargetFitness;
import postProcessors.Forecast;
import preProcessors.Normalizer;
import statistics.GenerationReport;
import statistics.IslandReport;
import statistics.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * This class provides a simple interface to use the system of prediction of time series using Genetic Algorithms.
 * It expects two configuration files: The first pointing to Global configurations' file, and the second pointing to
 * the file of configurations of the islands used in the evolution engine (the samples of both files can be found in
 * /TimeSeriesForecasting/data/config/samples).
 * After creating a new instance of this class, passing both files before explained, it's necessary just call the run
 * method, which will run all the prediction engine. This method requires a integer parameter, used to identify the log
 * file (It was used in cases in which it's desired to run the same method many times).
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

    private Logger logger;
    private ArrayList<IslandReport> islandReports;

    private Normalizer normalizer;

    public TimeSeriesProcessor(String gpConfigurationFilePath, String islandConfigurationFilePath)
    {
        this.gpConfigurationFilePath     = gpConfigurationFilePath;
        this.islandConfigurationFilePath = islandConfigurationFilePath;
        this.gpConfiguration             = new GPConfiguration();
        this.islandConfiguration         = new ArrayList<IslandConfiguration>();
        this.logger                      = new Logger();
        this.islandReports               = new ArrayList<IslandReport>();
    }

    public void run(int i) throws Exception
    {
System.out.println("load configurations");
        this.loadConfigurations();
System.out.println("Get data");
        this.getData();
System.out.println("Pre process data");
        this.preProcessData();
System.out.println("get islands");
        List<EvolutionEngine<Node>> islands = this.getIslands();
System.out.println("Process data");
        this.bestCandidate = this.processData(islands);
System.out.println("Get forecasted data");
        ArrayList<TimeNode> forecastedTimeSeries = this.getForecastedTimeSeries();
System.out.println("Post process data");
        forecastedTimeSeries = this.postProcessingData(forecastedTimeSeries);

        String logIslands = "\nRelatorio sobre as ilhas\n";
        ArrayList<IslandReport> reports = this.getIslandReports();
        for (IslandReport islandReport : reports) {
            logIslands += "Island:" + islandReport.getIslandIdentifier()+"\n";
            logIslands += "Number of Generations" + islandReport.getGenerationCounter()+"\n";
            for (GenerationReport generationReport : islandReport.getEvolutionHistory()) {
                logIslands += ("\tGeneration Number: " + generationReport.getGeneration()+"\n");
                logIslands += ("\tBest solution: " + generationReport.getBestSolution()+"\n");
                logIslands += ("\tBest fitness: " + generationReport.getFitness()+"\n");
            }

            logIslands += "\n\n";
        }
        this.logger.logIslands(logIslands);

        // Presenting results.
        String logForecastedData = "Data found:\n";
        for (TimeNode node : forecastedTimeSeries) {
            logForecastedData += (node.getValue() + ", ");
        }

        // @todo Create a new method for logForecastedData on logger (or, it was best just create a method appendLog)
        this.logger.logIslands(logForecastedData);

        // Comparing forecasted data with the real testing data.
        this.logger.commitLogFile(this.gpConfiguration.getEvolutionIdentifier() + i);
    }

    public ArrayList<IslandReport> getIslandReports()
    {
        return this.islandReports;
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
        this.normalizer = new Normalizer(this.trainingData);
        this.trainingData = normalizer.getNormalizedData();
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
        Node individual = null;
        IslandEvolution<Node> engine = this.getEvolutionEngine(islands);
        TerminationCondition[] terminationConditions = this.getTerminationConditions();
        try {
            individual = engine.evolve(
                    this.gpConfiguration.getPopulationSize(),
                    this.gpConfiguration.getElitePopulationSize(),
                    this.gpConfiguration.getEpochLength(),
                    this.gpConfiguration.getMigrationCount(),
                    terminationConditions
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            String log = "\nCondition(s) satisfied.";
            for (TerminationCondition condition :  engine.getSatisfiedTerminationConditions()) {
                log += ("\n\t" + condition.getClass());
            }

            log += "\nBest Individual found: " + individual.toString();
            log += "\nIt's fitness was: " + individual.getFitnessValue();

            this.logger.logTerminationConditions(log);
        }

        return individual;
    }

    /**
     * @todo this method was not implemented yet.
     *
     * This method reverts all modifications made over the data in preProcessData method. This method have also to
     * make the same modifications over the forecasted data (all values produced).
     */
    private ArrayList<TimeNode> postProcessingData(ArrayList<TimeNode> forecastedTimeSeries)
    {
        ArrayList<TimeNode> postProcessedData = this.normalizer.getUnNormalizedData(forecastedTimeSeries);
        return postProcessedData;
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
        Forecast forecast = new Forecast (
            this.originalTimeSeries, this.trainingData, this.gpConfiguration, this.bestCandidate
        );
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
            terminationConditions.add(new GenerationCountOverEpochs (
                    this.gpConfiguration.getGenerationCount(),
                    this.gpConfiguration.getEpochLength()
            ));
        }

        if (this.gpConfiguration.isEnableStagnationGenerationCount()) {
            terminationConditions.add(
                    new StagnationOverEpochs(
                            this.gpConfiguration.getStagnatedGenerationsLimit(),
                            this.gpConfiguration.isFitnessNatural(),
                            this.gpConfiguration.getEpochLength()
                    )
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
        this.addEvolutionObservers(evolutionEngine);

        return evolutionEngine;
    }

    /**
     * @todo Verify if it's possible to use this method (or something like this) to get updated about issues over islands, like migration, etc.
     *
     * @param engine        PG engine, where the observer methods will be created.
     */
    private void addEvolutionObservers(IslandEvolution<Node> engine)
    {
        engine.addEvolutionObserver(new IslandEvolutionObserver<Node>() {
            @Override
            public void islandPopulationUpdate(int islandIndex, PopulationData<? extends Node> populationData) {
                if (populationData.getGenerationNumber() % gpConfiguration.getLogInterval() == 0) {
                    reportIsland(islandIndex, populationData);
                    printEvolutionLog(islandIndex, populationData);
                }
            }

            @Override
            public void populationUpdate(PopulationData<? extends Node> populationData) {
                if (populationData.getGenerationNumber() % gpConfiguration.getLogInterval() == 0) {
                    printEvolutionLog(populationData);
                }
            }
        });
    }

    private void reportIsland(int islandIndex, PopulationData<? extends Node> populationData)
    {
        IslandReport islandReport = null;
        for (IslandReport report : this.islandReports) {
            if (islandIndex == report.getIslandIdentifier()) {
                islandReport = report;
            }
        }

        // If there's no record of this island in the array of records, then it's necessary to create a new one.
        if (islandReport == null) {
            islandReport = new IslandReport(islandIndex);
            this.islandReports.add(islandReport);
        }

        int currentGeneration = islandReport.getGenerationCounter() + this.gpConfiguration.getLogInterval();
        GenerationReport generationReport = new GenerationReport();
        generationReport.setBestSolution(populationData.getBestCandidate());
        generationReport.setFitness(populationData.getBestCandidateFitness());
        generationReport.setGeneration(currentGeneration);
        islandReport.setGenerationCounter(currentGeneration);
        islandReport.addEvolutionReport(generationReport);
    }

    /**
     * This method prints all data about the evolution (if it is parametrized for it).
     *
     * @param islandIndex    Island index.
     * @param populationData Data about the evolution process.
     */
    private void printEvolutionLog(int islandIndex, PopulationData<? extends Node> populationData) {
        if (gpConfiguration.isVerboseModeActivated()) {
            String evolutionLog = "";
            evolutionLog += "\nIsland #" + islandIndex;
            evolutionLog += "\nGeneration: " + populationData.getGenerationNumber();
            evolutionLog += "\n\tBest Solution: " + populationData.getBestCandidate();
            evolutionLog += "\n\tIts Fitness is: " + populationData.getBestCandidateFitness();
            evolutionLog += "\n\tPopulation size: " + populationData.getPopulationSize();
            evolutionLog += "\n-----------------------------------------------------------";
            System.out.println(evolutionLog);

            logger.logEvolution(evolutionLog);
        }
    }

    /**
     * This method prints all data about the evolution (if it is parametrized for it).
     *
     * @param populationData Data about the evolution process.
     */
    private void printEvolutionLog(PopulationData<? extends Node> populationData) {
        if (gpConfiguration.isVerboseModeActivated()) {
            String evolutionLog = "";

            evolutionLog += "\nGLOBAL EVOLUTION: ";
            evolutionLog += "\nGeneration: " + populationData.getGenerationNumber();
            evolutionLog += "\n\tBest Solution: " + populationData.getBestCandidate();
            evolutionLog += "\n\tIts Fitness is: " + populationData.getBestCandidateFitness();
            evolutionLog += "\n\tPopulation size: " + populationData.getPopulationSize();
            evolutionLog += "\n-----------------------------------------------------------";
            System.out.println(evolutionLog);

            if (populationData.getBestCandidateFitness() == gpConfiguration.getFitnessValue()) {
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