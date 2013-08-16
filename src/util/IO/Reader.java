package util.IO;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: paulo
 * Date: 14/08/13
 * Time: 13:13
 */
public class Reader
{

    String filePath;
    String content;
    File file;
    BufferedReader bufferedReader;

    public Reader(String filePath) throws IOException
    {
        this.filePath       = filePath;
        this.file           = new File(this.filePath);

        if (!this.file.exists()) {
            file.createNewFile();
        }

        this.bufferedReader = new BufferedReader(new FileReader(this.file));
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
    public void read() throws IOException
    {
        this.bufferedReader.readLine();
    }

    /**
     *
     */
    public void close() throws IOException
    {
        this.bufferedReader.close();
    }

}
