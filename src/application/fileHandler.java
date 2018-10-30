package application;

import constants.string;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class fileHandler
{

    /*Helper Methods*/
    public static void removeFile(String FilePath)
    {
        File file = new File(FilePath);
        file.delete();
    }

    public static void appendFile(String FilePath,String content) throws IOException, MalformedURLException, MalformedURLException, URISyntaxException, URISyntaxException, URISyntaxException
    {
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

    public static void appendPortSettings(String URL, String FilePath) throws IOException
    {
        String content = URL + string.lineBreak;

        File file = new File(FilePath);

        if (!file.exists())
        {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(content);
        bw.close();
    }

    public static void copyDirectory(File src, File dest) throws IOException
    {
        if (dest.exists())
        {
            return;
        }

        if (src.isDirectory())
        {

            //if directory not exists, create it
            dest.mkdir();

            //list all the directory contents
            String files[] = src.list();

            for (String file : files)
            {
                //construct the src and dest file structure
                File srcFile = new File(src, file);
                File destFile = new File(dest, file);
                //recursive copy
                copyDirectory(srcFile, destFile);
            }

        }
        else
        {
            //if file, then copy it
            //Use bytes stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }

}
