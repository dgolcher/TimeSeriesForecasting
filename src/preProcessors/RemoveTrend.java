package preProcessors;

/**
 * Removes the trend component of a Time Series
 *
 * To normalize a time series means make all values of this time series to be in a specific range of values. This is
 * important to improve quality in the forecast.
 *
 * When normalized data is used to do a forecast, it's important to turn it back to its original form. So, this class
 * will have to public operators. The first getNormalizedData will return the data of a time series (the original one
 * will be set to this class as parameter) normalized in a specific range of values (generally, values between 0 and 1).
 * The second function getDenormalizedData. This method will get the time series (with the original data and the
 * forecast) and put in on the original range.
 *
 * The equation used to normalize data, in this class, is
 * X' = (b - a) * [(X - Xmin) / Xmax - Xmin] + a,
 *
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 29/08/13
 * Time: 13:22
 */
public class RemoveTrend 
{

}