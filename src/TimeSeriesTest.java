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
        String gpConfigurationPath     = "data/config/linear_test/gp_configuration_linear_test.arff";
        String islandConfigurationPath = "data/config/linear_test/island_configuration_linear_test.arff";
        TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
            gpConfigurationPath, islandConfigurationPath, 1
        );
        timeSeriesProcessor.run();
    }

}
