package util.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 14/08/13
 * Time: 13:13
 */
public class Writer
{

    String filePath;
    String content;
    File file;
    BufferedWriter bufferedWriter;

    /**
     *
     * @param filePath
     * @throws IOException
     */
    public Writer(String filePath) throws IOException
    {
        this.filePath       = filePath;
        this.file           = new File(this.filePath);

        if (!this.file.exists()) {
            file.createNewFile();
        }

        this.bufferedWriter = new BufferedWriter(new FileWriter(this.file));
    }

    /**
     *
     * @param content
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public String getContent()
    {
        return this.content;
    }

    /**
     *
     */
    public void open()
    {

    }

    /**
     *
     */
    public void write() throws IOException
    {
        this.bufferedWriter.write(this.getContent());
    }

    /**
     *
     */
    public void close() throws IOException
    {
        this.bufferedWriter.close();
    }

}
