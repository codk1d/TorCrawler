package logManager;

import constants.enumeration.logType;
import java.util.concurrent.locks.ReentrantLock;

public class logController
{

    /*Shared Instance*/
    private static final logController sharedInstance = new logController();
    ReentrantLock lock = new ReentrantLock();

    public static logController getInstance()
    {
        return sharedInstance;
    }

    /*Log message to crawler UI Model*/
    public void logMessage(String message, String messageType, logType Type)
    {
        lock.lock();
        try
        {
            if (Type != null)
            {
                switch (Type)
                {
                    case error:
                        logModel.getInstance().logError(messageType, message);
                        break;
                    case request:
                        logModel.getInstance().logRequest(messageType, message);
                        break;
                    case urlFound:
                        logModel.getInstance().logFoundURL(messageType, message);
                        break;
                    default:
                        break;
                }
            }
        }
        finally
        {
            lock.unlock();
        }
    }

    /*Log Thread Count*/
    public void logThreadCount(int threadCount)
    {
        logModel.getInstance().setThreadCount(threadCount);
    }

}
