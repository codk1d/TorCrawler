package logManager;

import application.helperMethod;
import constants.enumeration;
import constants.enumeration.logType;
import constants.status;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;

public class logEvents
{

    /*Shared Instance*/
    private static final logEvents sharedInstance = new logEvents();
    private logViewController view;

    public static logEvents getInstance()
    {
        return sharedInstance;
    }

    public void Initialize(logViewController view)
    {
        this.view = view;
    }

    public void onCreateBackup()
    {
        try
        {
            helperMethod.writeObjectToFile(view.crawlerObject);
            view.resetCrawler();
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(logViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void onRestart()
    {
        try
        {
            view.resetCrawler();
        }
        catch (InstantiationException | ClassNotFoundException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(logViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onClearLogs()
    {
        view.jSystemProgressPane.setText("");
        view.jServerErrorPane.setText("");
        view.jUrlFoundPane.setText("");
        view.jWarningPane.setText("");
        view.crawlerObject.clearQueues();
    }
    
    public void onUpdateLogs()
    {
        int currentSize = logModel.getInstance().getSize();
        while (currentSize > 0)
        {
            try
            {
                if (!logModel.getInstance().isErrorModelEmpty())
                {
                    logMessageModel errorModel = logModel.getInstance().logErrorModel();
                    view.showMessage(errorModel,helperMethod.getErrorMessageType(errorModel.getMessage()));
                }
                if (!logModel.getInstance().isRequestModelEmpty())
                {
                    logMessageModel requestModel = logModel.getInstance().getRequestModel();
                    view.showMessage(requestModel,logType.request);
                }
                if (!logModel.getInstance().isFoundURLModelEmpty())
                {
                    logMessageModel requestModel = logModel.getInstance().getFoundURLModel();
                    view.showMessage(requestModel,logType.urlFound);
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(logViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (BadLocationException ex)
            {
                Logger.getLogger(logEvents.class.getName()).log(Level.SEVERE, null, ex);
            }
            currentSize -= 1;
        }
        view.pausedThread.setText("  " + logModel.getInstance().getPausedThread());
        view.runningThread.setText("  " + logModel.getInstance().getRunningThread());
    }
    
    public void onPauseCrawler()
    {
        status.appStatus = enumeration.appStatus.paused;
        view.jStartBtn.setBackground(new Color(240, 240, 240));
        view.jPauseBtn.setBackground(Color.green);
    }
    
    public void onStartCrawler()
    {
        status.appStatus = enumeration.appStatus.running;
        view.jPauseBtn.setBackground(new Color(240, 240, 240));
        view.jStartBtn.setBackground(Color.green);
    }    
}
