package preProcessors;

import model.TimeNode;

import java.util.ArrayList;

/**
 * Calculates the Autocorrelation coefficient of the time series.
 *
 * The Autocorrelation is the cross-correlation of a signal with itself. Informally, it is the similarity between 
 * observations as a function of the time lag between them. It is a mathematical tool for finding repeating patterns, 
 * such as the presence of seasonality, noise or trend (font: http://en.wikipedia.org/wiki/Autocorrelation).
 *
 *
 */
public class Autocorrelation 
{
    private ArrayList<TimeNode> timeSeries;
    private ArrayList autocorrelation;

    private float average;

    public Autocorrelation(ArrayList<TimeNode> timeSeries)
    {
        this.timeSeries      = timeSeries;
        this.autocorrelation = new ArrayList();
        this.average         = this.timeSeriesAverageValue();
    }

    public ArrayList getAutoCorrelationCoeficient() 
    {
        return this.autocorrelation;
    }

    /**
     * This method will find the correlation values for n-k elements of the time series, where n is the number of
     * elements in the arraylist of this time series and k is the interval chosen to process the autocorrelation.
     */
    public float process(int k)
    {
        float numerator   = 0;
        float denominator = 0;
        for (int i = 0; i < this.timeSeries.size() - k; i++) {
            numerator += (this.timeSeries.get(0).getValue() - this.average) * 
                         (this.timeSeries.get(i+k).getValue() - average);
        }

        for (int i = 0; i < this.timeSeries.size(); i++) {
            denominator += (this.timeSeries.get(i).getValue() - this.average);
        }

        return numerator / denominator;
    }

    private float timeSeriesAverageValue() 
    {
        float average = 0;

        for (TimeNode node : this.timeSeries) {
            average += node.getValue();
        }

        return average / this.timeSeries.size();
    }

}


// http://www.mathworks.com/help/econ/test-for-autocorrelation.html
// http://www.mathworks.com/help/econ/autocorr.html
// https://www.google.com.br/#q=autocorrelation+coefficient+example&safe=active

// public void autoCorrelation(int size){
//     float[] R = new R[size];
//     float sum;

//     for (int i=0;i<size;i++) {
//         sum=0;
//         for (int j=0;j<size-i;j++) {
//             sum+=x[j]*x[j+i];
//         }
//         R[i]=sum;
//     }
// }