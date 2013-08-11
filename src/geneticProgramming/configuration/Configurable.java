package geneticProgramming.configuration;

import weka.core.Instance;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 10/08/13
 * Time: 21:37
 */
public interface Configurable
{

    /**
     * Loads all configuration data using an instance of weka.core.Instance. This kind of object is result of reading
     * the ARFF files.
     *
     * @param instance Object containing all configurations that will be used in an instance of Configurable object.
     */
    public void loadConfigurationFromWekaInstance(Instance instance);

}
