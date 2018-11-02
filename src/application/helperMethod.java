package application;

import constants.enumeration.logType;
import constants.preferences;
import java.awt.Dimension;
import java.awt.Point;
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
import javax.swing.text.Position;

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

    public static Point centreDimension(int frameWidth,int frameHeight)
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point position = new Point();
        position.x = (int) ((dimension.getWidth() - frameWidth) / 2);
        position.y = (int) ((dimension.getHeight() - frameHeight) / 2) - 12;
        return position;
    }

    public static void writeObjectToFile(Object serObj)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(preferences.filepath_queue_manager);
            ObjectOutputStream objectOut;
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static Object readObjectFromFile()
    {

        try
        {
            if (!new File(preferences.filepath_queue_manager).exists())
            {
                return null;
            }
            FileInputStream fileIn = new FileInputStream(preferences.filepath_queue_manager);
            ObjectInputStream objectIn;
            objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            objectIn.close();
            return obj;

        }
        catch (IOException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public static logType getErrorMessageType(String errorMessage)
    {
        if (errorMessage.contains("java.io.FileNotFoundException"))
        {
            return logType.warning;
        }
        else
        {
            return logType.error;
        }
    }

}
