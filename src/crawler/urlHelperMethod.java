package crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import constants.string;
import constants.enumeration;
import application.helperMethod;
import constants.preferences;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class urlHelperMethod
{

    /*CHECK TYPE OF URL ONION OR BASEURL OR SAME HOST URL*/
    public static enumeration.UrlTypes getNetworkType(String URLLink)
    {
        String hostURL = getUrlHost(URLLink);
        if (hostURL.contains(string.typeOnion))
        {
            return enumeration.UrlTypes.onion;
        }
        else if (!hostURL.equals(string.hostError))
        {
            return enumeration.UrlTypes.base;
        }
        else
        {
            return enumeration.UrlTypes.none;
        }
    }

    public static boolean isUrlValid(String url)
    {
        try
        {
            new URL(url);
            return true;
        }
        catch (MalformedURLException e)
        {
            return false;
        }
    }

    public static String getUrlHost(String URLLink)
    {
        try
        {
            Pattern pattern = Pattern.compile(string.urlHostMacherRegex);
            Matcher matcher = pattern.matcher(URLLink);
            matcher.find();
            String protocol = matcher.group(1);
            String domain = matcher.group(2);
            return protocol + domain;
        }
        catch (Exception ex)
        {
            return "";
        }
    }

    public static String getSubUrl(String URLLink)
    {
        return URLLink.replace(getUrlHost(URLLink), "");
    }

    public static String getUrlExtension(String URLLink)
    {
        if (URLLink.endsWith(".js") || URLLink.endsWith(".jpg") || URLLink.endsWith(".png") || URLLink.endsWith(".svg") || URLLink.endsWith(".ico"))
        {
            return "image";
        }
        else if (URLLink.endsWith(".pdf") || URLLink.endsWith(".doc") || URLLink.endsWith(".ppt"))
        {
            return "doc";
        }
        else if (URLLink.endsWith(".mp3") || URLLink.endsWith(".avi") || URLLink.endsWith(".webm"))
        {
            return "video";
        }
        else
        {
            return "link";
        }
    }

    public static String createCacheUrl(String URL, String Title, String Description, String datatype, String keyTypes) throws IOException, MalformedURLException, URISyntaxException
    {
        datatype = "all";
        if (Title.equals(""))
        {
            Title = "Title not found";
        }
        if (Description.equals(""))
        {
            Description = "Description not found";
        }
        String query = "http://localhost/BoogleSearch/public/update_cache?url=" + URL + "&title=" + Title + "&desc=" + Description + "&type=" + preferences.networkType.toLowerCase() + "&n_type=" + preferences.networkType + "&s_type=" + datatype + "&live_date=" + helperMethod.getCurrentDate() + "&update_date=" + helperMethod.getCurrentDate() + "&key_word=" + keyTypes;

        return query;
    }

    public static String createDLink(String URL, String Title, String datatype) throws IOException, MalformedURLException, URISyntaxException
    {
        if (datatype.equals(""))
        {
            return "";
        }
        if (Title.equals(""))
        {
            Title = "Title not found";
        }

        String query = "http://localhost/BoogleSearch/public/update_cache?url=" + URL + "&title=" + Title + "&type=" + preferences.networkType.toLowerCase() + "&n_type=" + preferences.networkType + "&s_type=" + datatype + "&live_date=" + helperMethod.getCurrentDate() + "&update_date=" + helperMethod.getCurrentDate();

        return query;
    }

}
