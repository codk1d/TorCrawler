package torcrawler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TorRequestHandler 
{
    public void pingOnionLink() {
        
        try {
            SocketAddress addr = new InetSocketAddress(Constants.proxyIP,Constants.proxyPort);
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);      
            URL url = new URL(Constants.testOnionURL);
            URLConnection conn = url.openConnection(proxy);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            String HtmlCode = Constants.emptyString;
            while ((inputLine = in.readLine()) != null) 
                HtmlCode = HtmlCode + inputLine;
            in.close();
            
            /*KEY WORDS*/
            /*HEADER*/
            /*SUMMARY*/
            /*IMAGES LINKS*/
            /*VIDEO LINKS*/
            /*DOWNLOADS AND TOR LINKS*/
            /*BOOKS AND DOCUMENTS*/
            /*UPLOAD TO DATABASE*/
            
            System.out.println("TOTAL FOUND LINKS : " + extractUrls(HtmlCode).size());
        } 
        catch (Exception ex) {
            System.out.println(Constants.errorLogs + ex.toString());
        }
   }
    
    public static List<String> extractUrls(String text) {

        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = Constants.urlExtractingRegex;
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find())
        {
                containedUrls.add(text.substring(urlMatcher.start(Constants.emptyInt),
                urlMatcher.end(Constants.emptyInt)));
        }
        return containedUrls;
    }
}
 