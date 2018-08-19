package torcrawler;

public class ApplicationController
{

    /*APPLICATION GLOBAL INITIALIZATION*/
    private static void applicationInitialization()
    {
        FileHandler.removeFile(Constants.filepath);
    }

    private static void initialization()
    {
        applicationInitialization();
    }

    /*TRIGGER CRAWLER*/
    public static void main(String[] args)
    {
        initialization();

        TorWebCrawler torRequestHandler = new TorWebCrawler();
        torRequestHandler.start();
    }

}
