package preProcessors;

import model.TimeNode;
import java.util.ArrayList;

/**
 * Removes the trend component of a Time Series
 *
 * Assuming the Aditive Time Series model, which considers that every time series is composed by the addition of Trend, 
 * Sazonality and Noise (Z(t) = Mt + St + Yt), this class was meant to strip from the original time series its component
 * of Trend.
 * 
 * In time series analisys, trend can be defined as
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 29/08/13
 * Time: 13:22
 */
public class RemoveTrend 
{

	private ArrayList<TimeNode> timeSeries;

	public RemoveTrend(ArrayList<TimeNode> timeSeries) 
	{
		this.timeSeries = timeSeries;
	}

	/**
	 * Returns an ArrayList containing the time series data with trend stripped. This method removes this component by 
	 * subtracting a a certain value by its previous value. For each time that this method is used over a time series, 
	 * its first node is removed from the dataset (since its value is equals zero).
	 * Its possible that a time series must be processed more than one time by this method.
	 * 
	 * @return Returns the original data processed.
	 */
	public ArrayList<TimeNode> getTrendStrippedData() 
	{
		ArrayList<TimeNode> trendStrippedTimeSeries = new ArrayList<TimeNode>();

		for (int i = 0; i < this.timeSeries.size() - 1; i++) {
			TimeNode node = new TimeNode();
			node.setDate(this.timeSeries.get(i+1).getDate());
			node.setValue(this.timeSeries.get(i+1).getValue() - this.timeSeries.get(i+1).getValue());
			trendStrippedTimeSeries.add(node);
		}

		return trendStrippedTimeSeries;
	}

}