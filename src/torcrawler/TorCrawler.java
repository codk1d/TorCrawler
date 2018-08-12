package torcrawler;

public class TorCrawler {

    private static void applicationInitialization()
    {
        FileHandler.removeFile(Constants.filepath);
    }
    
    public static void main(String[] args) {
        applicationInitialization();

        TorWebCrawler torRequestHandler = new TorWebCrawler();
        torRequestHandler.start();
    }

}
