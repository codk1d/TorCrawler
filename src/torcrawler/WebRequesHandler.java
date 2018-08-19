package torcrawler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebRequesHandler
{

    /*USE ONION PROXY IF ONION URL FOR FASTER REQUEST*/
    public static String requestOnionConnection(String URLLink) throws MalformedURLException, IOException
    {

        SocketAddress addr = new InetSocketAddress(Constants.proxyIP, Constants.proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        Document doc = Jsoup.connect(URLLink).proxy(proxy).get();
        FileHandler.appendFile(URLLink, Constants.filepath);
        return doc.html();
    }

    /*USE ONION PROXY IF ONION URL FOR FASTER REQUEST*/
    public static String requestBaseConnection(String URLLink) throws MalformedURLException, IOException
    {
        Document doc = Jsoup.connect(URLLink).get();
        FileHandler.appendFile(URLLink, Constants.filepath);
        return doc.html();
    }
}
