package application;

import constants.enumeration;
import logger.log;
import constants.preferences;
import constants.status;
import constants.string;
import crawler.crawler;
import crawler.urlModel;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class torWebCrawler
{

    /*INSTANCES DECLARATIONS PRIVATE*/
    private final ArrayList<Thread> threadListPaused = new ArrayList<Thread>();
    private final ArrayList<Thread> threadListRunning = new ArrayList<Thread>();
    public crawler htmlParser;
    private int threadCount = 0;

    /*INITIALIZATIONS*/
    private void initializations() throws IOException, InterruptedException
    {
        instanceInitalization();
    }

    private void instanceInitalization() throws IOException, InterruptedException
    {
        crawler tempCrawler = (crawler) helperMethod.readObjectFromFile();
        if (tempCrawler == null)
        {
            htmlParser = new crawler();
        }
        else
        {
            htmlParser = new crawler();
        }

    }

    torWebCrawler() throws IOException, InterruptedException
    {
        initializations();
    }

    public void initializeCrawler() throws InterruptedException, IOException
    {
        while (threadCount < preferences.maxThreadCount)
        {
            threadCount++;
            createCrawler();
        }

        threadManager();

    }

    public void stopAllThread()
    {
        while (!threadListRunning.isEmpty())
        {
            threadListRunning.get(0).stop();
            threadListRunning.remove(0);
        }
    }

    public void threadManager()
    {
        new Thread()
        {
            @Override
            synchronized public void run()
            {
                /*Thread Initialization*/
                int count = 0;
                while (count < threadListRunning.size())
                {
                    if (!threadListRunning.get(count).isAlive())
                    {
                        threadListRunning.get(count).start();
                        count = 0;
                    }
                    count++;
                }

                /*Thread Scheduler*/
                int threadIndex = 0;
                while (true)
                {
                    try
                    {
                        if (threadListPaused.size() > 0
                                && status.appStatus == enumeration.appStatus.running
                                && htmlParser.size() > 0)
                        {
                            Thread thread = threadListPaused.get(0);
                            synchronized (thread)
                            {
                                threadListPaused.remove(thread);
                                threadListRunning.add(thread);
                                thread.notify();
                            }
                        }
                        log.updateThreadCount(threadListPaused.size(), threadListRunning.size());
                        sleep(100);
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(torWebCrawler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }.start();
    }

    public synchronized void pauseThreadDataSave(Thread thread)
    {
        threadListRunning.remove(thread);
        threadListPaused.add(thread);
    }

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
                            if (!htmlParser.isHostEmpty(host) && !host.equals(""))
                            {
                                urlmodel = htmlParser.getUrl(host);
                                url = urlmodel.getURL();
                                log.saveRequest(threadName + " : URL REQUEST", url);
                                String html = webRequestHandler.getInstance().requestConnection(url);
                                htmlParser.parse_html(html, url);
                            }
                            else
                            {
                                host = htmlParser.getKey();
                                continue;
                            }
                        }

                        pauseThreadDataSave(this);
                        wait();
                    }
                    catch (Exception ex)
                    {
                        //log.print("Error : " , ex);
                        if (urlmodel != null)
                        {
                            log.saveError("Server Error", ex.toString() + "<br> &nbsp &nbsp &nbsp &nbsp Parent URL:" + urlmodel.getParentURL() + "<br> &nbsp &nbsp &nbsp &nbsp Complete URL : " + url);
                        }
                        else
                        {
                            log.saveError("Server Error", ex.toString() + "<br> &nbsp &nbsp &nbsp &nbsp Host:" + host + "<br> &nbsp &nbsp &nbsp &nbsp Complete URL : " + url);
                        }
                    }

                }

            }
        };
        threadListRunning.add(thread);
    }
}
