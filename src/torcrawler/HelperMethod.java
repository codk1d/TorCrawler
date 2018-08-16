package torcrawler;

import java.net.URL;

public class HelperMethod {

    /*Helper Methods*/
    public static Constants.UrlTypes urlType(String URLLink)
    {
        String hostURL = extractHostURL(URLLink);
        if(hostURL.contains(".onion"))
        {
            return Constants.UrlTypes.onion;
        }
        else if(!hostURL.equals(Constants.hostError))
        {
            return Constants.UrlTypes.base;
        }
        else
        {
            return Constants.UrlTypes.none;
        }
    }
    
    public static String extractHostURL(String URLLink)
    {
        try {
            if(!URLLink.startsWith("http"))
            {
                URLLink = "https://" + URLLink;
            }
            URL url = new URL(URLLink);
            return url.getHost();
        } 
        catch (Exception ex) {
            return Constants.hostError;
        }
    }

}
