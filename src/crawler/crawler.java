package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import constants.enumeration;
import org.apache.commons.io.FilenameUtils;
import static crawler.nlpParser.extractTitle;
import application.webRequestHandler;
import logger.log;

public class crawler
{

    /*URL QUEUES*/
    private queueManager queryManager;

    /*VARIABLES DECLARATIONS*/
    private duplicationFilter duplicateFilter;

    /*INITIALIZATIONS*/
    public crawler() throws IOException
    {
        variable_initalization();
    }

    public int size()
    {
        return queryManager.size();
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
        return queryManager.getKey();
    }

    public boolean isHostEmpty(String host) throws InterruptedException
    {
        return queryManager.isHostEmpty(host);
    }

    public boolean isUrlPresent()
    {
        return queryManager.isUrlPresent();
    }

    public void saveUrl(String content) throws Exception
    {
        webRequestHandler.getInstance().updateCache(content);
    }

    /*METHOD PARSER*/
    public void parse_html(String HTML, String Url) throws MalformedURLException, IOException, URISyntaxException, Exception
    {
        ArrayList<String> urlList = nlpParser.extractAndSaveUrlsFromContent(HTML, Url);
        String keyWords = nlpParser.extractKeyWords(HTML);
        saveExtractedUrl(urlList, HTML,Url);
        initializeCurrentUrl(HTML, Url, keyWords);
    }

    public void initializeCurrentUrl(String HTML, String Url, String keywords) throws Exception
    {
        String summary = nlpParser.extractSummary(HTML).replace("'", "");//extractSummary(HTML);
        String content = urlHelperMethod.createCacheUrl(Url, extractTitle(HTML), summary, enumeration.UrlDataTypes.all.toString(), keywords);
        saveUrl(content);
    }

    public void saveExtractedUrl(ArrayList<String> urlList, String html,String parentURL) throws Exception
    {
        for (int e = 0; e < urlList.size(); e++)
        {
            String URLLink = urlList.get(e);
            String linkType = urlHelperMethod.getUrlExtension(URLLink);
            enumeration.UrlTypes urlType = urlHelperMethod.getNetworkType(URLLink);

            if (urlHelperMethod.isUrlValid(URLLink) && duplicateFilter.is_url_duplicate(0, URLLink))
            {
                if (linkType.equals("link"))
                {
                    if(URLLink.contains("www.example.org<"))
                    {
                        System.out.print("ERROR : " + parentURL + "_____"+URLLink);
                    }
                    if(!URLLink.contains("http"))
                    {
                        URLLink = "https://"+URLLink;
                    }
                    queryManager.setUrl(URLLink,parentURL);
                }
                else
                {
                    String content = urlHelperMethod.createDLink(URLLink, FilenameUtils.getName(URLLink), urlHelperMethod.getUrlExtension(URLLink));
                    saveUrl(content);
                }
            }
        }
    }
}
