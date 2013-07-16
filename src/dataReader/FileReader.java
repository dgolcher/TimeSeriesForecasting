package dataReader;

import java.util.List;

/**
 * Interface for file readers.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 14/07/13
 * Time: 00:06
 */
public interface FileReader
{

    /**
     * In this method, all the data in file is read, and the instances of it are returned.
     *
     * @return list of instances read.
     */
    public List readFile();


}
