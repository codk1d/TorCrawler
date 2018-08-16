package torcrawler;

public class URLProperties 
{
    Constants.UrlTypes type;
    String URL;
    String hostURL;
    
    public URLProperties(Constants.UrlTypes type,String URL)
    {
        this.URL = URL;
        this.type = type;
    }
        
}
