package dataReader;

import weka.core.Instances;
import weka.core.converters.ArffLoader;

import java.io.BufferedReader;
import java.io.IOException;


/**
 * This class is used to read ARFF files.
 *
 * @author Paulo Fernandes
 *
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 14/07/13
 * Time: 00:15
 */
public class ArffFileReader implements FileReader
{

    public String filePath;

    /**
     * Constructor method: Sets the input file's path.
     *
     * @param filePath Input file's path.
     */
    public ArffFileReader(String filePath)
    {
        this.filePath = filePath;
    }


    /**
     * In this method, all the data in file is read, and the instances of it are returned.
     *
     * @return list of instances read.
     */
    @Override
    public Instances readFile()
    {
        try {
            BufferedReader reader            = new BufferedReader(new java.io.FileReader(this.getFilePath()));
            ArffLoader.ArffReader arffReader = new ArffLoader.ArffReader(reader);
            Instances instances              = arffReader.getData();
            instances.setClassIndex(instances.numAttributes() - 1);
            return instances;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getFilePath()
    {
        return filePath;
    }

}
