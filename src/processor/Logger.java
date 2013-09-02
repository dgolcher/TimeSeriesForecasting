package processor;

import geneticProgramming.configuration.GPConfiguration;
import geneticProgramming.configuration.IslandConfiguration;
import geneticProgramming.geneticOperators.SelectionStrategyFactory;
import geneticProgramming.geneticOperators.TimeSeriesEvaluator;
import model.TimeNode;
import util.IO.Writer;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is responsible for logging the process of forecasting.
 *
 * User: paulo
 * Date: 15/08/13
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public class Logger
{

    public static final String DEFAULT_LOG_PATH   = "log";
    public static final String LOG_FILE_EXTENSION = "log";

    private String fileName;
    private String content;
    private Writer writer;
    long initTime;
    long finalTime;

    public Logger()
    {
        this.fileName = "";
        this.content  = "";

        this.initTime = System.nanoTime();
    }

    public void logConfigurations(GPConfiguration gpConfiguration, ArrayList<IslandConfiguration> islandConfigurations)
    {
        String configurations = this.getGlobalConfigurationsLogText(gpConfiguration, islandConfigurations);
        configurations       += this.getIslandLogText(islandConfigurations);

        this.content += configurations;
    }

    public void logDataSets(ArrayList<TimeNode> originalTimeSeries, ArrayList<TimeNode> trainingData,
                            ArrayList<TimeNode> testingData, GPConfiguration gpConfiguration)
    {
        String configurations = "\n=================================================================================\n";
        configurations += "\n\nDATA SETs\n";
        configurations += "TIME SERIES DATA: \n\t";
        for (TimeNode node : originalTimeSeries) {
            configurations += node.getValue() + ", ";
        }

        configurations = configurations.substring(0, configurations.length()-2) + "\n";

        configurations += "\nTRAINING DATA: \n";
        configurations += "TRAINING DATA BETWEEN " + gpConfiguration.getInitOfTrainingData() + " AND " +
                          gpConfiguration.getEndOfTrainingData() + " \n\t";
        for (TimeNode node : trainingData) {
            configurations += node.getValue() + ", ";
        }

        configurations = configurations.substring(0, configurations.length()-2) + "\n";

        configurations += "\nTESTING DATA: \n\t";
        configurations += "TESTING DATA BETWEEN " + gpConfiguration.getInitOfTestingData() + " AND " +
                gpConfiguration.getEndOfTestingData()  + " \n\t";
        for (TimeNode node : testingData) {
            configurations += node.getValue() + ", ";
        }

        this.content += configurations.substring(0, configurations.length()-2) + "\n";

        // @todo try to find a better place to place this text.
        this.content += "\n=================================================================================\n";
        this.content += "\nEVOLUTION LOG\n";
    }

    public void logEvolution(String evolutionLog)
    {
        this.content += evolutionLog;
    }

    public void logTerminationConditions(String terminationConditions)
    {
        this.content += terminationConditions;
    }

    public void commitLogFile(String fileName) throws IOException
    {
        this.fileName  = fileName;
        this.finalTime = System.nanoTime();
        this.content += "\n\nELAPSED TIME: " + (this.finalTime - this.initTime);

        this.writer = new Writer(Logger.DEFAULT_LOG_PATH + "/" + this.fileName + "." + LOG_FILE_EXTENSION);
        this.writer.setContent(this.content);
        this.writer.write();
        this.writer.close();
    }

    private String getGlobalConfigurationsLogText(GPConfiguration gpConfiguration,
                                                  ArrayList<IslandConfiguration> islandConfigurations)
    {
        String configurations = "GLOBAL CONFIGURATIONS";
        configurations += "\n\tGP FORECASTING EXECUTION: " + gpConfiguration.getEvolutionIdentifier();
        configurations += "\n\tPOPULATION SIZE:          " + gpConfiguration.getPopulationSize();
        configurations += "\n\tELITE POPULATION SIZE:    " + gpConfiguration.getElitePopulationSize();
        configurations += "\n\tWINDOW SIZE:              " + gpConfiguration.getWindowSize();
        configurations += "\n\tFITNESS NATURAL:          " + (gpConfiguration.isFitnessNatural() ? "YES" : "NO");
        configurations += "\n\tTERMINATION CONDITIONS:   ";
        if (gpConfiguration.isEnableGenerationCount()) {
            configurations += "\n\t\tGeneration Count (Number of Generations: "+gpConfiguration.getGenerationCount()+")";
        }

        if (gpConfiguration.isEnableStagnationGenerationCount()) {
            configurations += "\n\t\tStagnation (Stagnated Generations limit: "+gpConfiguration.getStagnatedGenerationsLimit()+")";
        }

        if (gpConfiguration.isEnableTerminationByFitness()) {
            configurations += "\n\t\tFitted Individual Found";
        }

        configurations += "\n\tEPOCH LENGTH:             " + gpConfiguration.getEpochLength();
        configurations += "\n\tMIGRATION COUNT:          " + gpConfiguration.getMigrationCount();
        configurations += "\n\tNUMBER OF ISLANDS:        " + islandConfigurations.size();
        return configurations;
    }

    private String getIslandLogText(ArrayList<IslandConfiguration> islandConfigurations)
    {
        String configurations = "\n\nISLAND CONFIGURATIONS";

        for (int i = 0; i < islandConfigurations.size(); i++) {
            configurations += "\n\tISLAND #" +(i+1);
            configurations += "\n\t\tSELECTION STRATEGY:       " + this.getSelectionStrategy(islandConfigurations.get(i));
            configurations += "\n\t\tENABLE ELITISM:           " + (islandConfigurations.get(i).isEnableElitism() ? "YES" : "NO");
            configurations += "\n\t\tENABLE CROSSOVER:         " + (islandConfigurations.get(i).isEnableCrossover() ? "YES" : "NO");
            configurations += "\n\t\tENABLE mutation:          " + (islandConfigurations.get(i).isEnableMutation() ? "YES" : "NO");
            if (islandConfigurations.get(i).isEnablePlague()) {
                configurations += "\n\t\tPLAGUE WAS ENABLED. CONFIGURATIONS:";
                configurations += "\n\t\t\tSURVIVOR PROBABILITY:    " + islandConfigurations.get(i).getSurvivorPlagueProbability();
                configurations += "\n\t\t\tTOTAL OF PLAGUE SPREADS: " + islandConfigurations.get(i).getTotalOfPlagueSpreads();
                configurations += "\n\t\t\tGENERATIONS BEFORE PLAGUE: " + islandConfigurations.get(i).getGenerationsCountBeforePlague();
                configurations += "\n\t\t\tFITNESS STRATEGY;          " + this.getFitnessStrategy(islandConfigurations.get(i));
            }

            configurations += "\n\t\tOPERATOR TYPES ENABLED:    ";
            configurations += this.getEnabledOperators(islandConfigurations, i);
        }
        return configurations;
    }

    private String getEnabledOperators(ArrayList<IslandConfiguration> islandConfigurations, int i)
    {
        String configurations = "";

        if (islandConfigurations.get(i).isEnableBasicOperators()) {
            configurations += "\n\t\t\tBASIC OPERATORS";
        }

        if (islandConfigurations.get(i).isEnableComplexOperators()) {
            configurations += "\n\t\t\tCOMPLEX OPERATORS";
        }

        if (islandConfigurations.get(i).isEnableLogicOperators()) {
            configurations += "\n\t\t\tLOGIC OPERATORS";
        }

        if (islandConfigurations.get(i).isEnableStatisticsOperators()) {
            configurations += "\n\t\t\tSTATISTICS OPERATORS";
        }

        if (islandConfigurations.get(i).isEnableTrigonometricOperators()) {
            configurations += "\n\t\t\tTRIGONOMETRIC OPERATORS";
        }

        return configurations;
    }

    private String getSelectionStrategy(IslandConfiguration configuration)
    {
        int selectionStrategy = configuration.getSelectionStrategy();

        switch (selectionStrategy) {
            case SelectionStrategyFactory.ROULETTE_WHEEL_SELECTION:
                return "ROULETTE WHEEL SELECTION";
            case SelectionStrategyFactory.TOURNAMENT_SELECTION:
                return "TOURNAMENT SELECTION";
            case SelectionStrategyFactory.STOCHASTIC_UNIVERSAL_SAMPLING:
                return "STOCHASTIC UNIVERSAL SAMPLING";
            case SelectionStrategyFactory.RANK_SELECTION:
                return "RANK SELECTION";
            case SelectionStrategyFactory.SIGMA_SCALING:
                return "SIGMA SCALING";
            case SelectionStrategyFactory.TRUNCATION_SELECTION:
                return "TRUNCATION SELECTION";
            default:
                return null;
        }
    }

    private String getFitnessStrategy(IslandConfiguration configuration)
    {
        int fitnessStrategy = configuration.getFitnessStrategy();

        switch (fitnessStrategy) {
            case TimeSeriesEvaluator.SQUARED_ERROR:
                return "SQUARED ERROR";
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_ERROR:
                return "MEAN ABSOLUTE ERROR";
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_PERCENT_ERROR:
                return "MEAN ABSOLUTE PERCENT ERROR";
            case TimeSeriesEvaluator.MEAN_ABSOLUTE_DEVIATION:
                return "MEAN ABSOLUTE DEVIATION";
            case TimeSeriesEvaluator.PERCENT_MEAN_ABSOLUTE_DEVIATION:
                return "PERCENT MEAN ABSOLUTE DEVIATION";
            case TimeSeriesEvaluator.MEAN_SQUARED_ERROR:
                return "MEAN SQUARED ERROR";
            case TimeSeriesEvaluator.ROOT_MEAN_SQUARED_ERROR:
                return "ROOT MEAN SQUARED ERROR";
            case TimeSeriesEvaluator.FORECAST_SKILL:
                return "FORECAST SKILL";
            case TimeSeriesEvaluator.AVERAGE_OF_ERROR:
                return "AVERAGE OF ERROR";
            default: return null;
        }
    }

}
