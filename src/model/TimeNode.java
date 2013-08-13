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

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p/>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "(" + this.getDate().toString() + ", " + this.getValue() + ")";
    }
}
