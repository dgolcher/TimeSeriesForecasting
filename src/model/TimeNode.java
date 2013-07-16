package model;

import java.util.Date;

/**
 * Representation of a node in a time series.
 *
 * This class is used as a representation of each pair of date and value, used to compose a time series.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 13/07/13
 * Time: 02:30
 */
public class TimeNode
{

    private Date date;
    private double value;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

}
