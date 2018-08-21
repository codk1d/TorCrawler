package torcrawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser
{

    /*URL QUEUES*/
    private ArrayList<URLProperties> onionQueues;
    private ArrayList<URLProperties> hostUrlQueues;
    private ArrayList<URLProperties> onionDataQueues;
    private ArrayList<URLProperties> baseQueues;

    /*VARIABLES DECLARATIONS*/
    private URLProperties currentHostURL;
    private DuplicationFilter duplicateFilterExternal;
    private DuplicationFilter duplicateFilterInternal;

    /*INITIALIZATIONS*/
    private void initializations()
    {
        variableInitalization();
    }

    private void variableInitalization()
    {
        onionQueues = new ArrayList<>();
        hostUrlQueues = new ArrayList<>();
        onionDataQueues = new ArrayList<>();
        baseQueues = new ArrayList<>();
        duplicateFilterExternal = new DuplicationFilter();
        duplicateFilterInternal = new DuplicationFilter();

        onionDataQueues.add(new URLProperties(Enumeration.UrlTypes.base, Constants.baseLink));
        currentHostURL = new URLProperties(Enumeration.UrlTypes.base, Constants.baseLink);

        duplicateFilterExternal.Initialize('_');
        duplicateFilterInternal.Initialize('_');
    }

    HTMLParser()
    {
        initializations();
    }

    /*TOTAL URL PARSED*/
    public int size()
    {
        return onionQueues.size() + hostUrlQueues.size() + onionDataQueues.size() + baseQueues.size();
    }

    public int totalOnionURL()
    {
        return onionQueues.size() + onionDataQueues.size();
    }

    /*METHOD UPDATE QUEUES AS NEW URL IS FOUND*/
    public URLProperties fetchAndRemove()
    {
        URLProperties urlProperties = null;
                    
        if (onionQueues.size() > 0)
        {
            urlProperties = onionQueues.get(0);
            onionQueues.remove(0);
        }
        else if (hostUrlQueues.size() > 0)
        {
            urlProperties = hostUrlQueues.get(0);
            hostUrlQueues.remove(0);
        }
        else if (onionDataQueues.size() > 0)
        {
            urlProperties = onionDataQueues.get(0);
            if (!checkURLDepth(urlProperties, Constants.maxUrlWidth))
            {
                System.out.println("Parent has no onion URL");
                urlProperties = null;
            }
            onionDataQueues.remove(0);
        }
        else if (baseQueues.size() > 0)
        {
            urlProperties = baseQueues.get(0);
            if (!checkURLDepth(urlProperties, Constants.maxUrlDepth))
            {
                System.out.println("Parent has no onion URL");
                urlProperties = null;
            }
            baseQueues.remove(0);
        }

        currentHostURL = urlProperties;
        return urlProperties;
    }

    /*CHECK URL DEPTH TO LIMIT TREE HEIGHT SO THAT CRAWLER DOENST DIVERT INTO URLS THAT DONT CONTAIN
    INFORMATION REGARDING ONION LINKS*/
    public boolean checkURLDepth(URLProperties urlProperties, int URLDepth)
    {
        int counter = 0;
        URLProperties node = urlProperties;

        while (counter < URLDepth)
        {
            if (node.parentNode == null || node.parentNode.totalURLFound > 0)
            {
                return true;
            }
            counter += 1;
        }

        return false;
    }

    /*ADD NEWLY FOUND URL IN SPECIFIED LIST LIKE HOST-ONION-ONION DATA QUEUES. LISTS HAVE BEEN
    SEPERATED FOR FASTER PERFORMANCE*/
    public void add(String URLLink, Enumeration.UrlTypes type, URLProperties parentNode) throws IOException
    {
                                
        URLProperties urlProperties = new URLProperties(type, URLLink);
        urlProperties.parentNode = parentNode;
        int hostURLIndex = URLLink.indexOf(HelperMethod.extractHostURL(URLLink));

        if (currentHostURL.hostURL.equals(HelperMethod.extractHostURL(URLLink)) && hostURLIndex != -1
                && duplicateFilterInternal.urlDuplicationHandler(0, URLLink.substring(hostURLIndex)))
        {
            hostUrlQueues.add(urlProperties);
        }
        else if (duplicateFilterExternal.urlDuplicationHandler(0, HelperMethod.extractHostURL(URLLink)))
        {
            if (type == Enumeration.UrlTypes.onion)
            {
                onionQueues.add(urlProperties);
                parentNode.onionURLCount(1);
            }
            else if (type == Enumeration.UrlTypes.base && URLLink.contains("onion"))
            {
                onionDataQueues.add(urlProperties);
            }
            else
            {
                baseQueues.add(urlProperties);
            }
        }
             
    }

    /*FINDING OUT TYPES OF URL LIKE IF IMAGE-VIDEO-AUDIO OR DOCUMENT*/
    private boolean isLinkTyped(String URLLink)
    {
        if (URLLink.endsWith(".jpg"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /*METHOD PARSER*/
    public void extractUrls(String HTML) throws MalformedURLException, IOException
    {
        URLProperties parentURL = currentHostURL;

        extractAndSaveUrlsFromTags(HTML, parentURL);
        extractAndSaveUrlsFromContent(HTML, parentURL);

        System.out.println("TOTAL LINKS : " + size() + " - TITLE : " + extractTitle(HTML) + " - SUMMARY : " + extractSummary(HTML));

    }

    /*THE FOLLOWNG METHOD IS USED TO EXTRACT URLS FROM LINK OR HREF TAGS*/
    private void extractAndSaveUrlsFromTags(String HTML, URLProperties parentNode) throws MalformedURLException, IOException
    {
        Document document = Jsoup.parse(HTML);
        Elements links = document.select("a[href]");
        for (Element link : links)
        {
            String URLLink = link.attr("href");
            Enumeration.UrlTypes urlType = HelperMethod.urlType(URLLink);
            if (!isLinkTyped(URLLink))
            {
                if (!URLLink.startsWith("http"))
                {
                    URLLink = "http://" + URLLink;
                }
                add(URLLink, urlType, parentNode);
            }
        }
    }

    /*THE FOLLOWNG URL FOUNDS URL EMBEDED IN TEXT OR CONTENT OF PAGE*/
    private void extractAndSaveUrlsFromContent(String HTML, URLProperties parentNode) throws MalformedURLException, IOException
    {
        Pattern urlPattern = Pattern.compile(Constants.baseLinkRegex);
        Matcher matcherUrl = urlPattern.matcher(Jsoup.parse(HTML).text());

        while (matcherUrl.find())
        {
            String URLLink = matcherUrl.group();
            Enumeration.UrlTypes urlType = HelperMethod.urlType(URLLink);
            if (!isLinkTyped(URLLink))
            {
                if (!URLLink.startsWith("http"))
                {
                    URLLink = "http://" + URLLink;
                }
                add(URLLink, urlType, parentNode);
            }
        }
    }
    
    private String extractTitle(String HTML)
    {
        Document document = Jsoup.parse(HTML);
        if(document.title().equals(""))
        {
            return document.select("h1").text();
        }
        else
        {
            return document.title();
        }
    }
    
    private String extractSummary(String HTML)
    {
        Document document = Jsoup.parse(HTML);
        
        String title = Constants.emptyString;
        String description = Constants.emptyString;;
        
        Elements metaTags = document.getElementsByTag("meta");

        for (Element metaTag : metaTags) {
          String content = metaTag.attr("content");
          String name = metaTag.attr("name");

          if("d.description".equals(name)) {
            description = content;
            break;
          }
        }
        
        if(!description.equals(Constants.emptyString))
        {
            description = title + " " + description;
        }
        else
        {
            if(document.select("p").size()>0 && document.select("h1")!=null)
            {
               description = document.select("h1").text() + "  " + document.select("p").get(0).text();
            }
            else if(document.select("h1")!=null)
            {
                description = document.select("h1").text();
            }
        }

        if(description.length() > 50)
        {
            description = description.substring(0,50);
        }
        
        return description;
    }
    
}
