package postProcessors;

import geneticProgramming.functions.Node;
import model.TimeNode;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 05/08/13
 * Time: 23:57
 */
public class Forecast
{

    private Node                programExample;
    private ArrayList<TimeNode> timeSeries;
    private int                 horizon;
    private int                 numberOfParams;

    public Forecast(ArrayList<TimeNode> originalTimeSeries, Node program, int horizon, int numberOfParams)
    {
        this.programExample     = program;
        this.timeSeries         = originalTimeSeries;
        this.horizon            = horizon;
        this.numberOfParams     = numberOfParams;
    }

    public ArrayList<TimeNode> forecastForNPeriods()
    {
        for (int i = 0; i < this.horizon; i++) {
            double[] params = new double[this.numberOfParams];
            for (int j = 0; j < this.numberOfParams; j++) {
                int index = (this.timeSeries.size() + j) - this.numberOfParams;
                params[j] = this.timeSeries.get(index).getValue();
            }

            double forecastedValue = this.programExample.evaluate(params);
            // @todo Verificar como definir as datas dos periodos previstos.
            TimeNode node = new TimeNode();
            node.setDate(null);
            node.setValue(forecastedValue);
            this.timeSeries.add(node);
        }

        return this.timeSeries;
    }

}
