package logManager;

import constants.preferences;
import java.util.ArrayList;

public class logModel
{

    /*Shared Instance*/
    private static final logModel sharedInstance = new logModel();

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
        logMessageModel model = sharedInstance.errorDataQueue.get(0);
        sharedInstance.errorDataQueue.remove(0);
        return model;
    }

    public void setThreadCount(int runningThread)
    {
        sharedInstance.totalRunningThread = runningThread;
    }

    /*Getter Methods*/
    public logMessageModel getRequestModel()
    {
        logMessageModel model = sharedInstance.requestDataQueue.get(0);
        sharedInstance.requestDataQueue.remove(0);
        return model;
    }

    public logMessageModel getFoundURLModel()
    {
        logMessageModel model = sharedInstance.foundURLDataQueue.get(0);
        sharedInstance.foundURLDataQueue.remove(0);
        return model;
    }

    /*Helper Method*/
    public boolean isErrorModelEmpty()
    {
        return sharedInstance.errorDataQueue.isEmpty();
    }

    public boolean isRequestModelEmpty()
    {
        return sharedInstance.requestDataQueue.isEmpty();
    }

    public boolean isFoundURLModelEmpty()
    {
        return sharedInstance.foundURLDataQueue.isEmpty();
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
