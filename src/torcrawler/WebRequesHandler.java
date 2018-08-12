package torcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

public class WebRequesHandler {

    public static String requestOnionConnection(String link) throws MalformedURLException, IOException {
        SocketAddress addr = new InetSocketAddress(Constants.proxyIP, Constants.proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);
        URL url = new URL(link);
        URLConnection conn = url.openConnection(proxy);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        String HtmlCode = Constants.emptyString;
        while ((inputLine = in.readLine()) != null) {
            HtmlCode = HtmlCode + inputLine;
        }
        in.close();
        return HtmlCode;
    }
    
    public static String requestBaseConnection(String link) throws MalformedURLException, IOException {
        URL url = new URL(link);
        URLConnection conn = url.openConnection();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        String HtmlCode = Constants.emptyString;
        while ((inputLine = in.readLine()) != null) {
            HtmlCode = HtmlCode + inputLine;
        }
        in.close();
        return HtmlCode;
    }
}
