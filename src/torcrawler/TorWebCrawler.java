package torcrawler;

import java.util.List;

public class TorWebCrawler extends Thread {

    /*INSTANCES DECLARATIONS*/
    private HTMLParser htmlParser;

    /*INITIALIZATIONS*/
    private void initializations() {
        instanceInitalization();
    }

    private void instanceInitalization() {
        htmlParser = new HTMLParser();
    }

    TorWebCrawler() {
        initializations();
    }

    /*BASE THREAD*/
    @Override
    public void run() {
        /*GLOBAL TRY CATCH*/
        try {

            while (htmlParser.size() > 0) {
                String currentURL = htmlParser.fetchAndRemove(0);
                String parsedPage = WebRequesHandler.requestOnionConnection(currentURL);
                htmlParser.extractUrls(parsedPage);
            }
        } catch (Exception ex) {
            System.out.println("Main Thread Error" + ex.toString());
        }

    }

}
