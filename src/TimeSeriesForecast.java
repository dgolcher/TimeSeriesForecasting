import processor.TimeSeriesProcessor;
import java.io.File;

/**
 * User: paulo
 * Date: 08/10/13
 * Time: 22:22
 */
public class TimeSeriesForecast
{

    public static void main(String[] args) throws Exception
    {
        File gpConfigurationDir         = new File("data/gpconfiguration");
        File islandConfigurationDir     = new File("data/island_configuration");

        File[] gpConfigurationFiles     = gpConfigurationDir.listFiles();
        File[] islandConfigurationFiles = islandConfigurationDir.listFiles();

        for (File gpConfFile : gpConfigurationFiles)
        {
            for (File islandConfFile : islandConfigurationFiles) {
                TimeSeriesProcessor timeSeriesProcessor = new TimeSeriesProcessor (
                        gpConfFile.toString(), islandConfFile.toString()
                );
                timeSeriesProcessor.run(1);
            }
        }
    }
}