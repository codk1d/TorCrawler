package torcrawler;

import java.net.MalformedURLException;
import java.net.URL;

public class HelperMethod
{

    /*CHECK TYPE OF URL ONION OR BASEURL OR SAME HOST URL*/
    public static Enumeration.UrlTypes urlType(String URLLink)
    {
        String hostURL = extractHostURL(URLLink);
        if (hostURL.contains(".onion"))
        {
            return Enumeration.UrlTypes.onion;
        }
        else if (!hostURL.equals(Constants.hostError))
        {
            return Enumeration.UrlTypes.base;
        }
        else
        {
            return Enumeration.UrlTypes.none;
        }
    }

    /*URL TO HOST URL CONVERTER*/
    public static String extractHostURL(String URLLink)
    {
        try
        {
            if (!URLLink.startsWith(Constants.requestTypeHttp))
            {
                URLLink = Constants.requestTypeHttps + Constants.urlSlashes + URLLink;
            }
            URL url = new URL(URLLink);
            return url.getHost();
        }
        catch (MalformedURLException ex)
        {
            System.out.println("MALFORMED URL READS : " + URLLink + " - " + ex.getMessage());
            return Constants.hostError;
        }
    }

}
