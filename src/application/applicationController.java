package application;

import logger.log;
import constants.preferences;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UnsupportedLookAndFeelException;

public class applicationController
{

    public static torWebCrawler torRequestHandler = null;
    public static crawlerManager crawlerUI;

    private static void initialization() throws IOException
    {
        applicationInitialization();
    }

    public static void openUI() throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
    {
        crawlerUI = new crawlerManager();
        crawlerUI.run();
        crawlerUI.crawlerObject = torRequestHandler.htmlParser;

    }

    /*APPLICATION GLOBAL INITIALIZATION*/
    private static void applicationInitialization() throws IOException
    {
        fileHandler.removeFile(preferences.filepath_url);
        fileHandler.removeFile(preferences.filepath_dlink);
        helperMethod.removeTorInstances();
    }

    public static void stopAllThread()
    {
        torRequestHandler.stopAllThread();
    }

    /*TRIGGER CRAWLER*/
    public static void main(String[] args) throws InterruptedException, IOException, InstantiationException, InstantiationException, ParseException, ClassNotFoundException, IllegalAccessException, IllegalAccessException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException, UnsupportedLookAndFeelException
    {
        try
        {
            if (torRequestHandler == null)
            {
                torRequestHandler = new torWebCrawler();
            }
            initialization();
            torRequestHandler.initializeCrawler();
            openUI();
        }
        catch (IOException ex)
        {
            log.print("GLOBAL ERROR : " + ex.toString());
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(applicationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
