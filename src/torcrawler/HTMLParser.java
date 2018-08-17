package torcrawler;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {

    /*URL QUEUES*/
    private  ArrayList<URLProperties> onionQueues;
    private  ArrayList<URLProperties> hostUrlQueues;
    private  ArrayList<URLProperties> onionDataQueues;
    private  ArrayList<URLProperties> baseQueues;

    /*VARIABLES DECLARATIONS*/
    private URLProperties currentHostURL;
    private DuplicationFilter duplicateFilterExternal;
    private DuplicationFilter duplicateFilterInternal;

    /*INITIALIZATIONS*/
    private void initializations() {
        variableInitalization();
    }

    private void variableInitalization() {
        onionQueues = new ArrayList<>();
        hostUrlQueues = new ArrayList<>();
        onionDataQueues = new ArrayList<>();
        baseQueues = new ArrayList<>();
        duplicateFilterExternal = new DuplicationFilter();
        duplicateFilterInternal = new DuplicationFilter();
        
        onionDataQueues.add(new URLProperties(Constants.UrlTypes.base,Constants.baseLink));
        currentHostURL = new URLProperties(Constants.UrlTypes.base,Constants.baseLink); 
        
        duplicateFilterExternal.Initialize('_');
        duplicateFilterInternal.Initialize('_');
    }

    HTMLParser() {
        initializations();
    }
    
    /*METHOD UPDATE*/
    public int size()
    {
        return onionQueues.size() + hostUrlQueues.size() + onionDataQueues.size() + baseQueues.size();
    }

    public URLProperties fetchAndRemove()
    {
        URLProperties urlProperties;
        if(hostUrlQueues.size()>0)
        {
            urlProperties = hostUrlQueues.get(0);
            hostUrlQueues.remove(0);
        }
        else if(onionQueues.size()>0)
        {
            urlProperties = onionQueues.get(0);
            onionQueues.remove(0);
        }
        else if(onionDataQueues.size()>0)
        {
            urlProperties = onionDataQueues.get(0);
            onionDataQueues.remove(0);
        }
        else
        {
            urlProperties = baseQueues.get(0);
            baseQueues.remove(0);
        }
        return urlProperties;
    }
    
    public void add(String URLLink,Constants.UrlTypes type)
    {
        URLProperties urlProperties = new URLProperties(type,URLLink);
        currentHostURL = urlProperties;

        if(currentHostURL.hostURL.equals(HelperMethod.extractHostURL(URLLink)) && 
           duplicateFilterInternal.urlDuplicationHandler(0,URLLink.substring(URLLink.indexOf(HelperMethod.extractHostURL(URLLink)))))
        {
            hostUrlQueues.add(new URLProperties(type,URLLink));
            System.out.println("SHOT URL FOUND : " + URLLink);
        }
        else if(duplicateFilterExternal.urlDuplicationHandler(0,HelperMethod.extractHostURL(URLLink)))
        {
            if(type == Constants.UrlTypes.onion)
            {
                onionDataQueues.add(urlProperties);
                System.out.println("SHOT URL FOUND : " + URLLink);
            }
            else if(type == Constants.UrlTypes.base && URLLink.contains("onion"))
            {
                onionDataQueues.add(urlProperties);
                System.out.println("SHOT URL FOUND : " + URLLink);
            }
            else
            {
                System.out.println("SHOT URL FOUND : " + URLLink);
                baseQueues.add(urlProperties);
            }
        }
        else
        {
            System.out.println("REPEATED URL FOUND : " + URLLink);
        }
    }
    
    /*METHOD PARSER*/
    public void extractUrls(String HTML) throws MalformedURLException {

        System.out.println("TOTAL LINKS : " + size());
        extractAndSaveUrlsFromTags(HTML);
        extractAndSaveUrlsFromContent(HTML);
    }
    
    private boolean isLinkTyped(String URLLink)
    {
        if(URLLink.endsWith(".jpg"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private void extractAndSaveUrlsFromTags(String HTML) throws MalformedURLException
    {
        Document document = Jsoup.parse(HTML);
        Elements links = document.select("a[href]");
        for (Element link : links) {
            String URLLink = link.attr("href");
            Constants.UrlTypes urlType = HelperMethod.urlType(URLLink);
            if(!isLinkTyped(URLLink))
            {
                if(!URLLink.startsWith("http"))
                {
                    URLLink = "http://" + URLLink;
                }
                add(URLLink,urlType);
            }
	}
    }
    
    private void extractAndSaveUrlsFromContent(String HTML) throws MalformedURLException
    {
        Pattern urlPattern = Pattern.compile(Constants.baseLinkRegex);
        Matcher matcherUrl = urlPattern.matcher(Jsoup.parse(HTML).text());

        while (matcherUrl.find())
        {
            String URLLink = matcherUrl.group();
            Constants.UrlTypes urlType = HelperMethod.urlType(URLLink);
            if(!isLinkTyped(URLLink))
            {
                if(!URLLink.startsWith("http"))
                {
                    URLLink = "http://" + URLLink;
                }
                add(URLLink,urlType);
            }
        }
    }
}
