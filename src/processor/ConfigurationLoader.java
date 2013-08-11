package processor;

import geneticProgramming.configuration.Configurable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Configuration loader.
 *
 * This class is used to load the configuration objects to used in Time Series Processor.
 * This class was prepared to load data from ARFF files and load them into Configurable instances.
 * This class provides two ways of loading Configurable objects. The first one is used to load a single object. In this
 * way, the loader will create instances from an ARFF file and then, get the first object (weka.core.Instance object) of
 * data. The second way is used to create many instances from the same ARFF file.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 10/08/13
 * Time: 21:43
 */
public class ConfigurationLoader
{

    /**
     * This method reads the ARFF file found in the path (first parameter passed to this method) and creates an instance
     * of Configurable class. It's important to notice that, if the ARFF file has many sets of data, only the first line
     * will be used in this loader method.
     *
     * @param filePath       Path to the ARFF file with all the configurations.
     * @param configurations Object that will receive the configuration data.
     *
     * @throws IOException
     */
    public static void loadConfigurations(String filePath, Configurable configurations) throws IOException
    {
        Instances instances = ConfigurationLoader.getInstances(filePath);
        configurations.loadConfigurationFromWekaInstance(instances.get(0));
    }

    /**
     * This method reads the ARFF file found in the path (first parameter passed to this method) and then created as
     * many instances in the configurations array as it finds in that file. Since this method doesn't know the type of
     * objects expected in the Configurations object, it will create new instances based on the type passed to the
     * third parameter (type), using reflection. This instances will be set in the configurations array.
     *
     * @param filePath       Path to the ARFF file with all the configurations.
     * @param configurations List of objects that will receive the configuration data.
     * @param type           Type of configuration objects that must be created.
     *
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @SuppressWarnings("unchecked")
    public static void loadConfigurations(String filePath, ArrayList configurations, Class type)
                                          throws IOException, IllegalAccessException, InstantiationException
    {
        Instances instances = ConfigurationLoader.getInstances(filePath);
        for (Instance instance : instances) {
            Configurable configuration = (Configurable) type.newInstance();
            configuration.loadConfigurationFromWekaInstance(instance);
            configurations.add(configuration);
        }
    }

    /**
     * This auxiliary method reads the ARFF file (if this file is found in the path passed in the first parameter) and
     * creates an instance of Instances (weka.core.Instances).
     *
     * @param filePath Path where the ARFF file can be found.
     *
     * @return Returns an instance of weka.core.Instances.
     * @throws IOException
     */
    private static Instances getInstances(String filePath) throws IOException
    {
        BufferedReader reader            = new BufferedReader(new FileReader(filePath));
        ArffLoader.ArffReader arffReader = new ArffLoader.ArffReader(reader);
        Instances instances              = arffReader.getData();
        instances.setClassIndex(instances.numAttributes() - 1);
        return instances;
    }

}
