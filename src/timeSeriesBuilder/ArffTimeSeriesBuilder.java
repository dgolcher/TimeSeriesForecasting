package timeSeriesBuilder;

import dataReader.FileReader;
import model.TimeNode;
import weka.core.Instance;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 14/07/13
 * Time: 01:43
 */
public class ArffTimeSeriesBuilder extends TimeSeriesBuilder
{
    /**
     * Constructor. Set the reader object.
     *
     * @param reader A FileReader object, used to read the file of training data.
     */
    public ArffTimeSeriesBuilder(FileReader reader)
    {
        super(reader);
    }

    /**
     * This method creates an instance of a Time series's node (an instance of TimeNode) and put it in the
     * timeSeries object.
     *
     * @param timeSeries TimeSeries object.
     * @param instance   Row extracted from some training data file.
     */
    @Override
    protected void addTimeSeriesNode(ArrayList<TimeNode> timeSeries, Object instance)
    {
        Instance instance1 = (Instance) instance;
        // Getting the data of a specific node in a time series.
        Date date = new Date((long) instance1.value(0));
        // Getting the value the quotation in a specific moment.
        double value = instance1.value(6);

        TimeNode node = new TimeNode();
        node.setDate(date);
        node.setValue(value);
        timeSeries.add(node);
    }
}
