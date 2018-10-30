package application;

import constants.preferences;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class helperMethod
{

    public static void removeTorInstances() throws IOException
    {
        if (!preferences.useTorFromOS)
        {
            Runtime.getRuntime().exec("kill $(lsof -t -i:8123)");
            Runtime.getRuntime().exec("sudo killall tor");
        }

    }

    public static String getCurrentDate()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM dd yyyy");
        Date date = new Date();
        return date.toString();
    }

    public static void centreWindow(Window frame)
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2)-12;
        frame.setLocation(x, y);
    }

    public static void writeObjectToFile(Object serObj)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(preferences.filepath_queue_manager);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static Object readObjectFromFile()
    {

        try
        {
            if(!new File(preferences.filepath_queue_manager).exists())
            {
                return null;
            }
            FileInputStream fileIn = new FileInputStream(preferences.filepath_queue_manager);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();
            return obj;

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
}
