package application;

import constants.enumeration.logType;
import constants.preferences;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

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

    public static Point centreDimension(int frameWidth, int frameHeight)
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Point position = new Point();
        position.x = (int) ((dimension.getWidth() - frameWidth) / 2);
        position.y = (int) ((dimension.getHeight() - frameHeight) / 2) - 12;
        return position;
    }

    public static String getCurrentDateTime()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void writeObjectToFile(Object serObj, String address)
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream(address);
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

    public static int getFileCount(String path)
    {
        int count = 0;
        try (Stream<Path> files = Files.list(Paths.get(path)))
        {
            count = (int) files.count();
        }
        catch (IOException ex)
        {
            Logger.getLogger(helperMethod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
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

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static boolean isDeadlinePassed(Date date)
    {
        Date cDate = new Date();
        if (cDate.after(date))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
