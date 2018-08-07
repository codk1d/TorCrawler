package torcrawler;

public class TorCrawler {

    public static void main(String[] args) 
    {
        TorRequestHandler torRequestHandler = new TorRequestHandler();
        torRequestHandler.pingOnionLink();
    }
    
}
