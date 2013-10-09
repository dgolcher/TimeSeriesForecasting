package preProcessors;

import geneticProgramming.functions.Node;
import model.TimeNode;

import java.util.ArrayList;

/**
 * Normalizes a time series
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
 * where X'      Is the normalized data;
 *       Xmin    Is the minimum value in the set of original data.
 *       Xmax    Is the maximum value in the set of original data.
 *       a and b Are the limit in the vector of data.
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 02/08/13
 * Time: 12:45
 */
public class Normalizer
{

    private ArrayList<TimeNode> timeSeries;
    private double maxValue;
    private double minValue;
    private int normalizationOption;

    public static final int NORMALIZE_BETWEEN_ONE_AND_ZERO      = 1;
    public static final int NORMALIZE_BETWEEN_ONE_AND_MINUS_ONE = 2;

    /**
     * Constructor. This method defines which time series will be normalized and which is the maximum and minimum value
     * represented there.
     *
     * @param timeSeries The list of values that will be normalized.
     * @param normalizationOption which kind of normalization will be used. The possibilities are to normalize the
     *                            series in numbers between 1 and 0 or between 1 and -1.
     */
    public Normalizer(ArrayList<TimeNode> timeSeries, int normalizationOption)
    {
        this.timeSeries          = timeSeries;
        this.maxValue            = Double.MIN_VALUE;
        this.minValue            = Node.BAD_FITNESS_VALUE;
        this.normalizationOption = normalizationOption;

        this.getLimitValues();
    }

    /**
     * Constructor. This method defines which time series will be normalized. In this method, the type of normalization
     * used was set as NORMALIZE_BETWEEN_ONE_AND_ZERO.
     *
     * @param timeSeries The list of values that will be normalized.
     */
    public Normalizer(ArrayList<TimeNode> timeSeries)
    {
        this(timeSeries, Normalizer.NORMALIZE_BETWEEN_ONE_AND_ZERO);
    }

    /**
     * This method returns the time series normalized. This method can use two kinds of normalization. The first one,
     * defines a range of values between 0 and 1. The second one, will return a series normalized between -1 and 1.
     *
     * @return Returns a time series normalized.
     */
    public ArrayList<TimeNode> getNormalizedData()
    {
        ArrayList<TimeNode> normalizedData = new ArrayList<TimeNode>();
        for (TimeNode node : this.timeSeries) {
            if (this.normalizationOption == Normalizer.NORMALIZE_BETWEEN_ONE_AND_MINUS_ONE) {
                normalizedData.add(this.normalizationBetweenMinus1And1(node));
            } else {
                normalizedData.add(this.normalizationBetween0and1(node));
            }
        }

        return normalizedData;
    }

    /**
     * This method returns the time series denormalized. This method can use two kinds of denormalization. The first one,
     * consider that the time series was normalized with values between 0 and 1. The second one, uses the range between
     * -1 and 1.
     *
     * @return Returns a time series denormalized.
     */
    public ArrayList<TimeNode> getDenormalizedData(ArrayList<TimeNode> normalizedData)
    {
        ArrayList<TimeNode> denormalizedData = new ArrayList<TimeNode>();
        for (TimeNode node : normalizedData) {
            if (this.normalizationOption == Normalizer.NORMALIZE_BETWEEN_ONE_AND_ZERO) {
                denormalizedData.add(this.denormalizationBetween0And1(node));
            } else {
                denormalizedData.add(this.denormalizationBetweenMinus1And1(node));
            }
        }

        return denormalizedData;
    }

    /**
     * This method process the normalization, considering a range of values between 0 and 1. the Equation used here is
     *
     * Z(t)' = (Z(t) - min(Z))  /  (max(Z) - min(Z)),
     * Where
     *   Z(t)' : is the value of the variable Z in the moment t normalized;
     *   Z(t)  : is the value of the variable Z in the moment t;
     *   min(Z): is the minimum value found in the time series Z;
     *   max(Z): is the maximum value found in the time series Z.
     *
     * @param node the moment representing the moment t in the time series Z.
     * @return Returns the value of the variable Z(t) normalized.
     */
    private TimeNode normalizationBetween0and1(TimeNode node)
    {
        double actualValue = node.getValue();
        double newValue    = (actualValue - this.minValue) / (this.maxValue - this.minValue);
        node.setValue(newValue);

        return node;
    }

    /**
     * This method process the normalization considering, a range of values between -1 and 1. The Equation used here is
     *
     * Z(t)' = (Z(t) - max(Z) - min(Z))  /  (max(Z) - min(Z))
     * Where
     *   Z(t)' : is the value of the variable Z in the moment t normalized;
     *   Z(t)  : is the value of the variable Z in the moment t;
     *   min(Z): is the minimum value found in the time series Z;
     *   max(Z): is the maximum value found in the time series Z.
     *
     * @param node the moment representing the moment t in the time series Z.
     * @return Returns the value of the variable Z(t) normalized.
     */
    private TimeNode normalizationBetweenMinus1And1(TimeNode node)
    {
        double actualValue = node.getValue();
        double newValue    = (actualValue - this.maxValue - this.minValue) / (this.maxValue - this.minValue);
        node.setValue(newValue);

        return node;
    }

    /**
     * This method process the denormalization, considering a range of values between 0 and 1. The Equation used here
     * is
     *
     * Z(t) = Z(t)' * (max(Z) - min(Z)) + min(Z)
     * Where
     *   Z(t)' : is the value of the variable Z in the moment t normalized;
     *   Z(t)  : is the value of the variable Z in the moment t;
     *   min(Z): is the minimum value found in the time series Z;
     *   max(Z): is the maximum value found in the time series Z.
     *
     * @param node the moment representing the moment t in the time series Z.
     * @return Returns the value of the variable Z(t) normalized.
     */
    private TimeNode denormalizationBetween0And1(TimeNode node)
    {
        double actualValue = node.getValue();
        double newValue    = (actualValue * (this.maxValue - this.minValue)) + this.minValue;
        node.setValue(newValue);

        return node;
    }

    /**
     * This method process the denormalization, considering a range of values between -1 and 1. The Equation used here
     * is
     *
     * Z(t) = Z(t)' * (max(Z) - min(Z)) + min(Z) + max(Z)
     * Where
     *   Z(t)' : is the value of the variable Z in the moment t normalized;
     *   Z(t)  : is the value of the variable Z in the moment t;
     *   min(Z): is the minimum value found in the time series Z;
     *   max(Z): is the maximum value found in the time series Z.
     *
     * @param node the moment representing the moment t in the time series Z.
     * @return Returns the value of the variable Z(t) normalized.
     */
    private TimeNode denormalizationBetweenMinus1And1(TimeNode node)
    {
        double actualValue = node.getValue();
        double newValue    = (actualValue * (this.maxValue - this.minValue)) + this.maxValue + this.minValue;
        node.setValue(newValue);

        return node;
    }

    /**
     * This method finds and stores the maximum and the minimum values of a time series.
     */
    private void getLimitValues() {
        for (TimeNode node : this.timeSeries) {
            this.minValue = (node.getValue() < this.minValue) ? node.getValue() : this.minValue;
            this.maxValue = (node.getValue() > this.minValue) ? node.getValue() : this.maxValue;
        }
    }

}
