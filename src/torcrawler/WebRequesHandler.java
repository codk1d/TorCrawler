package torcrawler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebRequesHandler {

    public static String requestOnionConnection(String link) throws MalformedURLException, IOException {
        
        SocketAddress addr = new InetSocketAddress(Constants.proxyIP, Constants.proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        Document doc = Jsoup.connect(link).proxy(proxy).get();
        return doc.html();
    }
    
    public static String requestBaseConnection(String link) throws MalformedURLException, IOException {
        Document doc = Jsoup.connect(link).get();
        return doc.html();
    }
}
