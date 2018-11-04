package logManager;

import constants.enumeration.logType;
import constants.string;

public class log
{
    /*Print Message or Log*/
    public static void print(String message)
    {
        System.out.println(message);
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
    
    /*Log Exceptions Errors and Warnings*/
    public static void logMessage(String message,String messageType,logType logType)
    {
       logController.getInstance().logMessage(messageType, message,logType);
    }
    
    public static void logThreadCount(int threadCount)
    {
        logController.getInstance().logThreadCount(threadCount);
    }

}
