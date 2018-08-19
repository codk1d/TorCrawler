package torcrawler;

public class URLProperties
{

    /*PRIVATE VARIABLES*/
    Enumeration.UrlTypes type;
    URLProperties parentNode;

    String URL;
    String hostURL;
    int totalURLFound = 0;

    /*INITIALIZATION*/
    public URLProperties(Enumeration.UrlTypes type, String URL)
    {
        this.URL = URL;
        this.type = type;
        this.hostURL = HelperMethod.extractHostURL(URL);
    }

    public void initializeURLFound(int totalURLFound)
    {
        this.totalURLFound = totalURLFound;
    }

    /*INCREASE URL HIT COUNT AS ONION URLS ARE FOUND*/
    public void onionURLCount(int totalURLFound)
    {
        this.totalURLFound += totalURLFound;
    }

}
