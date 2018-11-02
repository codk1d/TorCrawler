package application;

import logManager.log;
import constants.enumeration;
import constants.enumeration.logType;
import constants.string;
import constants.preferences;
import constants.status;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.*;
import crawler.urlHelperMethod;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class webRequestHandler
{

    /*Shared Instance*/
    private static final webRequestHandler sharedInstance = new webRequestHandler();

    public static webRequestHandler getInstance()
    {
        return sharedInstance;
    }

    /*Private Variable*/
    public ArrayList<HttpURLConnection> connectionList = new ArrayList<HttpURLConnection>();
    ReentrantLock lock = new ReentrantLock();

    public String requestConnection(String url) throws MalformedURLException, IOException, Exception
    {
        if (urlHelperMethod.getNetworkType(url).equals(enumeration.UrlTypes.onion))
        {
            return requestOnionConnection(url);
        }
        else
        {
            return requestBaseConnection(url);
        }
    }

    /*USE ONION PROXY IF ONION URL F OR FASTER REQUEST*/
    public String requestBaseConnection(String url) throws MalformedURLException, IOException, Exception
    {
        System.setProperty("http.agent", "Chrome");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        connectionList.add(con);
        String html = getContent(con, "Base");
        if (!html.contains(".onion") && status.onionFilterStatus)
        {
            throw new Exception("URL is not in onion cluster network");
        }
        return html;
    }

    /*HELPER METHOD USE ONION PROXY IF ONION URL FOR FASTER REQUEST*/
    public String requestOnionConnection(String url) throws MalformedURLException, IOException, Exception
    {
        System.setProperty("http.agent", "Chrome");
        SocketAddress addr = new InetSocketAddress(string.proxyIP, preferences.proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
        connectionList.add(con);
        return getContent(con, "Onion");
    }

    public void removeAllRequests()
    {
        while (!connectionList.isEmpty())
        {
            connectionList.get(0).disconnect();
            connectionList.remove(0);
        }
    }

    public String getContent(HttpURLConnection conn, String networkType) throws IOException
    {
        conn.connect();

        Scanner scanner = new Scanner(conn.getInputStream());
        getInstance().lock.lock();
        try
        {
            scanner.useDelimiter("\\A");
            String content = "";

            while (scanner.hasNextLine())
            {
                content = content + scanner.nextLine();
            }

            log.logMessage(networkType + " URL FOUND", conn.getURL().getHost() + conn.getURL().getPath(), logType.urlFound);
            log.print("URL FOUND : " + conn.getURL());
            connectionList.remove(conn);
            return content;
        }
        finally
        {
            getInstance().lock.unlock();
        }

    }

    /*UPDATE URL DATABASE*/
    public void updateCache(String url) throws MalformedURLException, IOException, URISyntaxException
    {
        if (urlHelperMethod.getNetworkType(url).equals(enumeration.UrlTypes.onion) && status.cacheStatus)
        {
            url = url.replaceAll(" ", "%20");
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            connectionList.add(con);
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK)
            {
                log.logMessage("Cache Error\n" + url, "Server returned response code " + responseCode + " ", logType.error);
            }
            connectionList.remove(con);
        }
    }
}
