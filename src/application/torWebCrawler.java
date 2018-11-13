package application;

import static java.lang.Thread.sleep;
import constants.enumeration;
import logManager.log;
import constants.preferences;
import constants.status;
import constants.string;
import crawler.crawler;
import crawler.retryModel;
import crawler.urlModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class torWebCrawler
{

    /*INSTANCES DECLARATIONS PRIVATE*/
    private final ArrayList<Thread> pausedThreadQueue = new ArrayList<Thread>();
    private final ArrayList<Thread> runningThreadQueue = new ArrayList<Thread>();
    private final ReentrantLock lock = new ReentrantLock();

    private crawler htmlParser;
    private int threadCount = 0;

    /*INITIALIZATIONS*/
    torWebCrawler() throws IOException, InterruptedException
    {
        initializations();
    }

    private void initializations() throws IOException, InterruptedException
    {
        crawler tempCrawler = (crawler) helperMethod.readObjectFromFile();
        if (tempCrawler == null)
        {
            htmlParser = new crawler();
        }
        else
        {
            htmlParser = tempCrawler;
        }
        backupManager();
    }

    /*CRAWLER INITIALIZATION*/
    public void initializeCrawler() throws InterruptedException, IOException
    {
        while (threadCount < preferences.maxThreadCount)
        {
            threadCount++;
            createCrawler();
        }

        threadManager();

    }

    /*CRAWLER HELPER METHODS*/
    public void stopAllThread()
    {
        while (!runningThreadQueue.isEmpty())
        {
            runningThreadQueue.get(0).stop();
            runningThreadQueue.remove(0);
        }
    }

    public void pauseThread(Thread thread)
    {
        runningThreadQueue.remove(thread);
        pausedThreadQueue.add(thread);
    }

    /*GETTER METHODS*/
    public crawler getHtmlParser()
    {
        return htmlParser;
    }

    public void threadInitialization()
    {
        /*Thread Initialization*/
        int count = 0;
        while (count < runningThreadQueue.size())
        {
            if (!runningThreadQueue.get(count).isAlive())
            {
                runningThreadQueue.get(count).start();
                count = 0;
            }
            count++;
        }
    }

    public void backupManager()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    while (true)
                    {
                        sleep(preferences.backupTimer);
                        //crawler parser = (crawler) SerializationUtils.clone(htmlParser);
                        //helperMethod.writeObjectToFile(parser,preferences.filepath_queue_manager);
                        //helperMethod.writeObjectToFile(parser,preferences.filepath_queue_manager_backup+"_"+helperMethod.getCurrentDateTime());
                    }
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(torWebCrawler.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }.start();
    }

    public void threadManager()
    {
        new Thread()
        {
            @Override
            public void run()
            {
                threadInitialization();
                /*Thread Scheduler*/
                while (true)
                {
                    try
                    {
                        if (pausedThreadQueue.size() > 0 && status.appStatus == enumeration.appStatus.running && htmlParser.size() > 0)
                        {
                            Thread thread = pausedThreadQueue.get(0);
                            synchronized (thread)
                            {
                                pausedThreadQueue.remove(thread);
                                runningThreadQueue.add(thread);
                                thread.notify();
                            }
                        }
                        log.logThreadCount(runningThreadQueue.size());
                        if (status.appStatus == enumeration.appStatus.running)
                        {
                            htmlParser.validateRetryUrl();
                        }
                        sleep(preferences.requestTimeGap);
                        //log.print(counterRequest+"");
                    }
                    catch (InterruptedException | IOException ex)
                    {
                        Logger.getLogger(torWebCrawler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    int counterRequest = 0;
    public void createCrawler() throws IOException, InterruptedException
    {
        String threadName = "Thread:" + threadCount;
        Thread thread = new Thread()
        {
            @Override
            synchronized public void run()
            {
                String host = string.emptyString;
                String url = "";
                urlModel urlmodel = new urlModel("", "");

                while (true)
                {
                    try
                    {
                        if (status.appStatus == enumeration.appStatus.running)
                        {
                            if (!htmlParser.isHostEmpty(host))
                            {
                                counterRequest++;
                                urlmodel = htmlParser.getUrl(host);
                                url = urlmodel.getURL();
                                log.logMessage(threadName + " : URL REQUEST", url, enumeration.logType.request);
                                String html = webRequestHandler.getInstance().requestConnection(url);
                                htmlParser.parse_html(html, url);
                                counterRequest--;
                            }
                            else
                            {
                                lock.lock();
                                try
                                {
                                    host = htmlParser.getKey();
                                }
                                finally
                                {
                                    lock.unlock();
                                }
                            }
                        }

                        if (htmlParser.isHostEmpty(host) || status.appStatus == enumeration.appStatus.paused)
                        {
                            lock.lock();
                            try
                            {
                                pauseThread(this);
                            }
                            finally
                            {
                                lock.unlock();
                            }
                            wait();
                        }
                    }
                    catch (Exception ex)
                    {
                        counterRequest--;
                        log.print("Error : " + host + " " + ex);
                        if (urlmodel != null)
                        {
                            log.logMessage("Server Error", ex.toString() + "<br> &nbsp &nbsp &nbsp &nbsp Parent URL:" + urlmodel.getParentURL() + "<br> &nbsp &nbsp &nbsp &nbsp Complete URL : " + url, enumeration.logType.error);
                            htmlParser.addToRetryQueue(new retryModel(urlmodel.getParentURL(), urlmodel.getURL()));
                        }
                        else
                        {
                            log.logMessage("Server Error", ex.toString() + "<br> &nbsp &nbsp &nbsp &nbsp Host:" + host + "<br> &nbsp &nbsp &nbsp &nbsp Complete URL : " + url, enumeration.logType.warning);
                        }
                    }
                }
            }
        };
        runningThreadQueue.add(thread);
    }
}
