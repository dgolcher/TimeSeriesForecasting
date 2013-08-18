import processor.TimeSeriesProcessor;

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
        TimeSeriesTest.fewIslands(1);
//        for (int i = 0; i < 10; i++) {
//            TimeSeriesTest.fewIslands(i);
//            TimeSeriesTest.extremeGenerations(i);
//            TimeSeriesTest.majorPopulations(i);
//            TimeSeriesTest.manyIslands(i);
//            TimeSeriesTest.moreGenerations(i);
//            TimeSeriesTest.linear(i);
//        }
    }

    private static void linear(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/linear_test/gp_configuration_linear_test.arff";
        String islandConfigurationPath = "data/config/linear_test/island_configuration_linear_test.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
            gpConfigurationPath, islandConfigurationPath, i
        );
        timeSeriesProcessor.run();
    }

    private static void extremeGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/EXTREME_GENERATIONS/gp_configuration_extreme_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/EXTREME_GENERATIONS/island_configuration_extreme_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath, i
        );
        timeSeriesProcessor.run();
    }

    private static void fewIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/FEW_ISLANDS/gp_configuration_few_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/FEW_ISLANDS/island_configuration_few_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath, i
        );
        timeSeriesProcessor.run();
    }

    private static void majorPopulations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/MAJOR_POPULATION/gp_configuration_major_population.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/MAJOR_POPULATION/island_configuration_major_population.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath, i
        );
        timeSeriesProcessor.run();
    }

    private static void manyIslands(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/MANY_ISLANDS/gp_configuration_many_islands.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/MANY_ISLANDS/island_configuration_many_islands.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath, i
        );
        timeSeriesProcessor.run();
    }

    private static void moreGenerations(int i) throws Exception
    {
        String gpConfigurationPath     = "data/config/IBOVESPA/MORE_GENERATIONS/gp_configuration_more_generations.arff";
        String islandConfigurationPath = "data/config/IBOVESPA/MORE_GENERATIONS/island_configuration_more_generations.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                gpConfigurationPath, islandConfigurationPath, i
        );
        timeSeriesProcessor.run();
    }

}
