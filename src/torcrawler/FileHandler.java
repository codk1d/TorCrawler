package torcrawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler
{

    /*Helper Methods*/
    public static void removeFile(String FilePath)
    {
        File file = new File(FilePath);
        file.delete();
    }

    public static void appendFile(String URL, String FilePath) throws IOException
    {
        String content = URL + Constants.lineBreak;

        File file = new File(FilePath);

        if (!file.exists())
        {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }

}
