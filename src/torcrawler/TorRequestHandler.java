package torcrawler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

public class TorRequestHandler 
{
    public void pingOnionLink() {
        
        try {
            SocketAddress addr = new InetSocketAddress("127.0.0.1", 9150);
            Proxy proxy = new Proxy(Proxy.Type.SOCKS, addr);      
            URL url = new URL("http://kbvbh4kdddiha2ht.onion/");
            URLConnection conn = url.openConnection(proxy);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) 
                System.out.println(inputLine);
            in.close();
        } 
        catch (Exception ex) {
            System.out.println("CODE ERROR " + ex.toString());
        }
   }
    
}
