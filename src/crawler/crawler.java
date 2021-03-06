package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import constants.enumeration;
import static crawler.nlpParser.extractTitle;
import application.webRequestHandler;
import constants.enumeration.UrlTypes;
import constants.preferences;
import constants.status;
import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;
import logManager.log;
import org.jsoup.Jsoup;

public class crawler implements Serializable
{

    /*URL QUEUES*/
    public queueManager queryManager;

    /*VARIABLES DECLARATIONS*/
    public duplicationFilter duplicateFilter;
    public ReentrantLock lock = new ReentrantLock();

    /*INITIALIZATIONS*/
    public crawler() throws IOException
    {
        variable_initalization();
    }

    public int size()
    {
        return queryManager.size();
    }

    public void clearQueues()
    {
        queryManager.clearQueues();
    }

    private void variable_initalization() throws IOException
    {
        queryManager = new queueManager();
        duplicateFilter = new duplicationFilter();
        duplicateFilter.Initialize();
    }

    public urlModel getUrl(String host) throws InterruptedException
    {
        return queryManager.getUrl(host);
    }

    public String getKey() throws InterruptedException
    {
        synchronized (this)
        {
            return queryManager.getKey();
        }
    }

    public boolean isHostEmpty(String host) throws InterruptedException
    {
        return queryManager.isHostEmpty(host);
    }

    /*METHOD PARSER*/
    public void parse_html(String HTML, String Url) throws MalformedURLException, IOException, URISyntaxException, Exception
    {
        ArrayList<String> urlList = null;
        lock.lock();
        try
        {
            urlList = nlpParser.extractAndSaveUrlsFromContent(HTML, Url);
            String keyWords = nlpParser.extractKeyWords(Jsoup.parse(HTML).body().text());
            saveCurrentUrl(HTML, Url, keyWords,urlList);
        }
        finally
        {
            lock.unlock();
        }
    }

    public void saveCurrentUrl(String HTML, String Url, String keywords,ArrayList<String> urlList) throws Exception
    {
        String title = extractTitle(HTML);
        String extractLogo = nlpParser.extractLogo(HTML);
        saveExtractedUrl(urlList, HTML, Url,title);
        if (urlHelperMethod.getNetworkType(Url).equals(enumeration.UrlTypes.onion) && status.cacheStatus)
        {
            String summary = nlpParser.extractSummary(HTML).replace("'", "");
            String content = urlHelperMethod.createCacheUrl(Url, title, summary, enumeration.UrlDataTypes.all.toString(), keywords,extractLogo);
            saveUrlToServer(content);
        }
    }

    public void saveUrlToServer(String content) throws Exception
    {
        webRequestHandler.getInstance().updateCache(content);
    }

    public void validateRetryUrl() throws IOException
    {
        queryManager.validateRetryUrl();
    }

    public void addToRetryQueue(retryModel rmodel)
    {
        queryManager.addToRetryQueue(rmodel);
    }

    public void saveExtractedUrl(ArrayList<String> urlList, String html, String parentURL,String parentTitle) throws Exception
    {
        for (int e = 0; e < urlList.size(); e++)
        {
            String URLLink = urlList.get(e);
            String linkType = urlHelperMethod.getUrlExtension(URLLink);
            UrlTypes urlType = urlHelperMethod.getNetworkType(URLLink);

            if (urlHelperMethod.isUrlValid(URLLink) && duplicateFilter.is_url_duplicate(0, URLLink))
            {
                if (linkType.equals("link"))
                {
                    queryManager.setUrl(URLLink, parentURL);
                }
                else
                {
                    String title = "";
                    if(parentTitle.length()>45)
                    {
                        parentTitle = parentTitle.substring(0,45)+"...";
                    }
                    if(URLLink.length()>preferences.maxDLinkUrlSize)
                    {
                        title = URLLink .substring(URLLink.length()-preferences.maxDLinkUrlSize);
                    }
                    log.print("FILE " + " URL FOUND" + " " + URLLink);
                    log.logMessage("FILE " + " URL FOUND", URLLink, enumeration.logType.urlFound);
                    String content = urlHelperMethod.createDLink(URLLink,parentTitle, urlHelperMethod.getUrlExtension(URLLink));
                    saveUrlToServer(content);
                }
            }
        }
    }
}
