import dataReader.ArffFileReader;
import dataReader.FileReader;
import model.TimeNode;
import timeSeriesBuilder.ArffTimeSeriesBuilder;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 14/07/13
 * Time: 01:54
 */
public class Teste
{

    public static void main(String[] args)
    {
        String filePath                     = "data/dados bolsas/MERVAL/arff/MERVAL DIARIO.arff";
        FileReader reader                   = new ArffFileReader(filePath);
        ArffTimeSeriesBuilder builder       = new ArffTimeSeriesBuilder(reader);
        ArrayList<TimeNode> timeSeries = builder.getTimeSeries();

        for (TimeNode node : timeSeries) {
            System.out.println("Date  : " + node.getDate());
            System.out.println("Value : " + node.getValue());
            System.out.println();
        }

    }

}