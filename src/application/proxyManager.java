package application;

import constants.preferences;
import java.io.File;
import java.io.IOException;

public class proxyManager
{

    Process torRuntime = null;
    Process polipoRuntime = null;
    int threadNo = 0;
    int proxyPort = 9150;

    proxyManager() throws IOException, InterruptedException
    {
    }

    public void initializeCircuts(int threadNo) throws IOException
    {
        this.threadNo = threadNo;
        proxyPort = 10000 + threadNo;
        fileHandler.copyDirectory(new File("./Proxy/Tor"), new File("./Proxies_Theads/Tor" + threadNo));
        fileHandler.appendPortSettings("SocksPort " + proxyPort + "\nControlPort " + (20000 + threadNo) + "\nDataDirectory " + "./Proxies_Theads/Tor" + threadNo + "\nUseBridges 1\nClientTransportPlugin obfs2,obfs3 exec /usr/bin/obfsproxy managed\nClientTransportPlugin obfs4 exec /usr/bin/obfs4proxy managed\nBridge obfs4 104.131.154.63:9443 B4AB17DADC1867D61F598658554A4DBEDFCD1548 cert=cdCfVyrffZTwFyv2D1numcMmmz7DUGLMlmzvW6QDbZtndAGdEK5r78XoAOikP/phtpgJSg iat-mode=0\nBridge obfs4 144.217.20.138:40927 FB70B257C162BF1038CA669D568D76F5B7F0BABB cert=vYIV5MgrghGQvZPIi1tJwnzorMgqgmlKaB77Y3Z9Q/v94wZBOAXkW+fdx4aSxLVnKO+xNw iat-mode=0\nBridge obfs4 85.195.247.105:42501 9F548604BD5AB011F13301D843F83E1A8DA57A0D cert=I5iJp4egNJbz+20QsW02sgSBC6giNQhsbGtLATeAgzwSWdJfxvXbAY+hhWlBqmpuo+plKQ iat-mode=0", "./Proxies_Theads/Tor" + threadNo + "/torcc.1");
        new File("./Proxies_Theads/Tor" + threadNo + "/src/or/tor").setExecutable(true);
    }

    public void startProxy() throws IOException, InterruptedException
    {
        startTorProxy();
        //startPolipoProxy();
    }

    public void stopProxy() throws IOException
    {
        //stopPolipoProxy();
        stopTorProxy();
    }

    private void initializeRuntimes() throws IOException, InterruptedException
    {
    }

    private void stopTorProxy() throws IOException
    {
        torRuntime.destroy();
    }

    private void stopPolipoProxy() throws IOException
    {
        polipoRuntime.destroy();
    }

    private void startTorProxy() throws IOException
    {
        if (!preferences.useTorFromOS)
        {
            Runtime.getRuntime().exec("./Proxies_Theads/Tor" + threadNo + "/src/or/tor -f ./Proxies_Theads/Tor" + threadNo + "/torcc.1");
        }
    }

    private void startPolipoProxy() throws IOException, InterruptedException
    {
        polipoRuntime = Runtime.getRuntime().exec("./Proxy/Polipo/polipo");
    }
}
