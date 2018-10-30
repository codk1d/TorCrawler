package logger;

import java.util.ArrayList;

public class logController
{

    private static logController instance = new logController();
    private ArrayList<logModel> errorDataModel = new ArrayList<logModel>();
    private ArrayList<logModel> requestDataModel = new ArrayList<logModel>();
    private ArrayList<logModel> foundURLDataModel = new ArrayList<logModel>();
    private int pausedThread = 0;
    private int runningThread = 0;

    public void resetQueues()
    {
        instance.errorDataModel.removeAll(instance.errorDataModel);
        instance.requestDataModel.removeAll(instance.requestDataModel);
        instance.foundURLDataModel.removeAll(instance.foundURLDataModel);
    }

    public static logController getInstance()
    {
        return instance;
    }

    public void logRequest(String messageType, String message, ArrayList<String> params)
    {
        logModel model = new logModel(message, messageType, params);
        instance.requestDataModel.add(model);
    }

    public void logError(String messageType, String message, ArrayList<String> params)
    {
        synchronized (this)
        {
            logModel model = new logModel(message, messageType, params);
            instance.errorDataModel.add(model);
        }
    }

    public void logFoundURL(String messageType, String message, ArrayList<String> params)
    {
        synchronized (this)
        {
            logModel model = new logModel(message, messageType, params);
            instance.foundURLDataModel.add(model);
        }
    }

    public boolean isErrorModelEmpty()
    {
        return instance.errorDataModel.isEmpty();
    }

    public boolean isRequestModelEmpty()
    {
        return instance.requestDataModel.isEmpty();
    }

    public boolean isFoundURLModelEmpty()
    {
        return instance.foundURLDataModel.isEmpty();
    }

    public logModel getErrorModel()
    {
        synchronized (this)
        {
            if (instance.errorDataModel.size() > 0)
            {
                logModel model = instance.errorDataModel.get(0);
                instance.errorDataModel.remove(0);
                return model;
            }
            return new logModel("", "", null);
        }

    }

    public logModel getRequestModel()
    {
        synchronized (this)
        {
            if (instance.requestDataModel.size() > 0)
            {
                logModel model = instance.requestDataModel.get(0);
                instance.requestDataModel.remove(0);
                return model;
            }
            return new logModel("", "", null);
        }
    }

    public logModel getFoundURLModel()
    {
        synchronized (this)
        {
            if (instance.foundURLDataModel.size() > 0)
            {
                logModel model = instance.foundURLDataModel.get(0);
                instance.foundURLDataModel.remove(0);
                return model;
            }
            return new logModel("", "", null);
        }
    }

    public void updateThreadCount(int pausedThread, int runningThread)
    {
        instance.pausedThread = pausedThread;
        instance.runningThread = runningThread;
    }

    public int getPausedThread()
    {
        return instance.pausedThread;
    }

    public int getRunningThread()
    {
        return instance.runningThread;
    }
}
