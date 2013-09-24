import geneticProgramming.functions.Node;
import geneticProgramming.functions.terminal.Constant;
import processor.TimeSeriesProcessor;
import statistics.GenerationReport;
import statistics.IslandReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 11/08/13
 * Time: 21:34
 */
public class TimeSeriesTest
{

    public static void main(String[] args) throws Exception
    {
//        thirdDegreeII(1);
//        thirdDegree(1);
//        parabole(1);
        linear(1);
//        increasingExtremeGenerations(1);
//        increasingFewIslands(1);
//        increasingMajorPopulations(1);
//        increasingManyIslands(1);
//        increasingMoreGenerations(1);
//        decreasingExtremeGenerations(1);
//       decreasingFewIslands(1);
//        decreasingMajorPopulations(1);
//        decreasingManyIslands(1);
//        decreasingMoreGenerations(1);
//        irregularExtremeGenerations(1);
//       irregularFewIslands(1);
//        irregularMajorPopulations(1);
//        irregularManyIslands(1);
//        irregularMoreGenerations(1);
//        linear(1);
//        linearExtremeGenerations(1);
//       linearFewIslands(1);
//        linearMajorPopulations(1);
//        linearManyIslands(1);
//        linearMoreGenerations(1);
//        parableExtremeGenerations(1);
//       parableFewIslands(1);
//        parableMajorPopulations(1);
//        parableManyIslands(1);
//        parableMoreGenerations(1);
    }

    private static void thirdDegreeII(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/terceiro_grau II/gp_configuration_parabole_test.arff";
        String islandConfigurationPath = "data/config/terceiro_grau II/island_configuration_parabole_test.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void thirdDegree(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/terceiro_grau/gp_configuration_parabole_test.arff";
        String islandConfigurationPath = "data/config/terceiro_grau/island_configuration_parabole_test.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void parabole(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/parabole_sample/gp_configuration_parabole_test.arff";
        String islandConfigurationPath = "data/config/parabole_sample/island_configuration_parabole_test.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
        ArrayList<IslandReport> reports = timeSeriesProcessor.getIslandReports();

        System.out.println("\n\n\n REPORT ABOUT THE EVOLUTION.");
        for (IslandReport islandReport : reports) {
            System.out.println("Island:" + islandReport.getIslandIdentifier());
            System.out.println("Number of Generations" + islandReport.getGenerationCounter());
            for (GenerationReport generationReport : islandReport.getEvolutionHistory()) {
                System.out.println("\tGeneration Number: " + generationReport.getGeneration());
                System.out.println("\tBest solution" + generationReport.getBestSolution());
                System.out.println("\tBest fitness: " + generationReport.getFitness());
            }

            System.out.println("\n");
        }
    }

    private static void linear(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/linear_test/gp_configuration_linear_test.arff";
        String islandConfigurationPath = "data/config/linear_test/island_configuration_linear_test.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void decreasingExtremeGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/decreasing/EXTREME_GENERATIONS/gp_configuration_extreme_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/decreasing/EXTREME_GENERATIONS/island_configuration_extreme_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void decreasingFewIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/decreasing/FEW_ISLANDS/gp_configuration_few_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/decreasing/FEW_ISLANDS/island_configuration_few_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void decreasingMajorPopulations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/decreasing/MAJOR_POPULATION/gp_configuration_major_population.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/decreasing/MAJOR_POPULATION/island_configuration_major_population.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void decreasingManyIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/decreasing/MANY_ISLANDS/gp_configuration_many_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/decreasing/MANY_ISLANDS/island_configuration_many_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void decreasingMoreGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/decreasing/MORE_GENERATIONS/gp_configuration_more_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/decreasing/MORE_GENERATIONS/island_configuration_more_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

//--------------------------------------------------------------------------------------------------------------------------

    private static void increasingExtremeGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/increasing/EXTREME_GENERATIONS/gp_configuration_extreme_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/increasing/EXTREME_GENERATIONS/island_configuration_extreme_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void increasingFewIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/increasing/FEW_ISLANDS/gp_configuration_few_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/increasing/FEW_ISLANDS/island_configuration_few_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void increasingMajorPopulations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/increasing/MAJOR_POPULATION/gp_configuration_major_population.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/increasing/MAJOR_POPULATION/island_configuration_major_population.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void increasingManyIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/increasing/MANY_ISLANDS/gp_configuration_many_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/increasing/MANY_ISLANDS/island_configuration_many_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void increasingMoreGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/increasing/MORE_GENERATIONS/gp_configuration_more_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/increasing/MORE_GENERATIONS/island_configuration_more_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

//--------------------------------------------------------------------------------------------------------------------------

    private static void irregularExtremeGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/irregular/EXTREME_GENERATIONS/gp_configuration_extreme_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/irregular/EXTREME_GENERATIONS/island_configuration_extreme_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void irregularFewIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/irregular/FEW_ISLANDS/gp_configuration_few_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/irregular/FEW_ISLANDS/island_configuration_few_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void irregularMajorPopulations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/irregular/MAJOR_POPULATION/gp_configuration_major_population.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/irregular/MAJOR_POPULATION/island_configuration_major_population.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void irregularManyIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/irregular/MANY_ISLANDS/gp_configuration_many_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/irregular/MANY_ISLANDS/island_configuration_many_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void irregularMoreGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/irregular/MORE_GENERATIONS/gp_configuration_more_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/irregular/MORE_GENERATIONS/island_configuration_more_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

//--------------------------------------------------------------------------------------------------------------------------

    private static void linearExtremeGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/linear/EXTREME_GENERATIONS/gp_configuration_extreme_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/linear/EXTREME_GENERATIONS/island_configuration_extreme_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void linearFewIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/linear/FEW_ISLANDS/gp_configuration_few_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/linear/FEW_ISLANDS/island_configuration_few_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void linearMajorPopulations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/linear/MAJOR_POPULATION/gp_configuration_major_population.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/linear/MAJOR_POPULATION/island_configuration_major_population.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void linearManyIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/linear/MANY_ISLANDS/gp_configuration_many_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/linear/MANY_ISLANDS/island_configuration_many_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void linearMoreGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/linear/MORE_GENERATIONS/gp_configuration_more_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/linear/MORE_GENERATIONS/island_configuration_more_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

//--------------------------------------------------------------------------------------------------------------------------

    private static void parableExtremeGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/parable/EXTREME_GENERATIONS/gp_configuration_extreme_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/parable/EXTREME_GENERATIONS/island_configuration_extreme_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void parableFewIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/parable/FEW_ISLANDS/gp_configuration_few_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/parable/FEW_ISLANDS/island_configuration_few_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void parableMajorPopulations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/parable/MAJOR_POPULATION/gp_configuration_major_population.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/parable/MAJOR_POPULATION/island_configuration_major_population.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void parableManyIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/parable/MANY_ISLANDS/gp_configuration_many_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/parable/MANY_ISLANDS/island_configuration_many_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

    private static void parableMoreGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/MORE_GENERATIONS/gp_configuration_more_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/MORE_GENERATIONS/island_configuration_more_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath
        );
        timeSeriesProcessor.run(i);
    }

}