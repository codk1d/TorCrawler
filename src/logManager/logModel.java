package logManager;

import constants.preferences;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class logModel
{

    /*Shared Instance*/
    private static final logModel sharedInstance = new logModel();
    private ReentrantLock lock = new ReentrantLock();

    /*Queue Initialization*/
    private final ArrayList<logMessageModel> errorDataQueue = new ArrayList<logMessageModel>();
    private final ArrayList<logMessageModel> requestDataQueue = new ArrayList<logMessageModel>();
    private final ArrayList<logMessageModel> foundURLDataQueue = new ArrayList<logMessageModel>();

    /*Data Initialization*/
    private int totalRunningThread = 0;

    /*Initialization*/
    public static logModel getInstance()
    {
        return sharedInstance;
    }

    public void resetQueues()
    {
        errorDataQueue.removeAll(errorDataQueue);
        requestDataQueue.removeAll(requestDataQueue);
        foundURLDataQueue.removeAll(foundURLDataQueue);
    }

    /*Setter or Log Methods*/
    public void logRequest(String messageType, String message)
    {
        logMessageModel model = new logMessageModel(message, messageType);
        sharedInstance.requestDataQueue.add(model);
    }

    public void logError(String messageType, String message)
    {
        logMessageModel model = new logMessageModel(message, messageType);
        sharedInstance.errorDataQueue.add(model);
    }

    public void logFoundURL(String messageType, String message)
    {
        logMessageModel model = new logMessageModel(message, messageType);
        sharedInstance.foundURLDataQueue.add(model);
    }

    public logMessageModel logErrorModel()
    {
        lock.lock();
        try
        {
            logMessageModel model = sharedInstance.errorDataQueue.get(0);
            sharedInstance.errorDataQueue.remove(0);
            return model;
        }
        catch(Exception e)
        {
            log.print("fuck");
            return null;
        }
        finally
        {
            lock.unlock();
        }
    }

    public void setThreadCount(int runningThread)
    {
        sharedInstance.totalRunningThread = runningThread;
    }

    /*Getter Methods*/
    public logMessageModel getRequestModel()
    {
        lock.lock();
        try
        {
            logMessageModel model = sharedInstance.requestDataQueue.get(0);
            sharedInstance.requestDataQueue.remove(0);
            return model;
        }
        catch(Exception e)
        {
            log.print("fuck");
            return null;
        }
        finally
        {
            lock.unlock();
        }
    }

    public logMessageModel getFoundURLModel()
    {
        lock.lock();
        try
        {
            logMessageModel model = sharedInstance.foundURLDataQueue.get(0);
            sharedInstance.foundURLDataQueue.remove(0);
            return model;
        }
        catch(Exception e)
        {
            log.print("fuck");
            return null;
        }
        finally
        {
            lock.unlock();
        }
    }

    /*Helper Method*/
    public boolean isErrorModelEmpty()
    {
        lock.lock();
        try
        {
            return sharedInstance.errorDataQueue.isEmpty();
        }
        finally
        {
            lock.unlock();
        }
    }

    public boolean isRequestModelEmpty()
    {
        lock.lock();
        try
        {
            return sharedInstance.requestDataQueue.isEmpty();
        }
        finally
        {
            lock.unlock();
        }
    }

    public boolean isFoundURLModelEmpty()
    {
        lock.lock();
        try
        {
            return sharedInstance.foundURLDataQueue.isEmpty();
        }
        finally
        {
            lock.unlock();
        }

    }

    public int getPausedThread()
    {
        return preferences.maxThreadCount - sharedInstance.totalRunningThread;
    }

    public int getRunningThread()
    {
        return sharedInstance.totalRunningThread;
    }

    public int getSize()
    {
        return sharedInstance.errorDataQueue.size() + sharedInstance.requestDataQueue.size() + sharedInstance.foundURLDataQueue.size();
    }

}
