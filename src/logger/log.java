package logger;

import constants.string;
import java.util.ArrayList;

public class log
{
    /*Print Message or Log*/
    public static void print(String message)
    {
        System.out.println(message.toString());
    }

    /*Print Exceptions*/
    public static void print(String message,Exception error)
    {
        System.out.println(message);
        
        if(error != null && string.reportException)
        {
            error.printStackTrace();
        }
    }
    
    public static void saveError(String message,String messageType)
    {
        logController.getInstance().logError(messageType, message, new ArrayList<String>());
    }
    
    public static void saveRequest(String message,String messageType)
    {
        logController.getInstance().logRequest(messageType, message, new ArrayList<String>());
    }

    public static void saveFoundURL(String message,String messageType)
    {
        logController.getInstance().logFoundURL(messageType, message, new ArrayList<String>());
    }

    public static void updateThreadCount(int pausedThread,int runningThread)
    {
        logController.getInstance().updateThreadCount(pausedThread,runningThread);
    }

}
