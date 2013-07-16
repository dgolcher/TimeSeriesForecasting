package timeSeriesBuilder;

import dataReader.FileReader;
import model.TimeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Time series Builder.
 *
 * This class is responsible for get all data read by the dataReader classes, then mount the time Series. This class
 * will represent timeSeries as a List of TimeNode (objects composed by key and value), containing two data: Date, that
 * represents when the value (the other one) happened.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 14/07/13
 * Time: 01:18
 */
abstract public class TimeSeriesBuilder
{

    /**
     * Instance of a fileReader object. This will be used to access the file and read it.
     */
    private FileReader reader;

    /**
     * Constructor. Set the reader object.
     *
     * @param reader A FileReader object, used to read the file of training data.
     */
    public TimeSeriesBuilder(FileReader reader)
    {
        this.reader = reader;
    }

    /**
     * Get the data read by the dataReader Object and transform it in an List, containing date and value.
     *
     * @return The instance of Time series.
     */
    public ArrayList<TimeNode> getTimeSeries()
    {
        ArrayList<TimeNode> timeSeries = new ArrayList<TimeNode>();
        List instances = this.reader.readFile();

        for (Object instance : instances) {
            this.addTimeSeriesNode(timeSeries, instance);
        }

        return timeSeries;
    }

    /**
     * This method creates an instance of a Time series's node (an instance of TimeNode) and put it in the
     * timeSeries object.
     *
     * @param timeSeries TimeSeries object.
     * @param instance   Row extracted from some training data file.
     */
    abstract protected void addTimeSeriesNode(ArrayList<TimeNode> timeSeries, Object instance);

}
