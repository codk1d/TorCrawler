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

        while (htmlParser.size() > 0) {
            try {
                URLProperties currentURL = htmlParser.fetchAndRemove();
                String parsedPage = Constants.emptyString;
                if (currentURL.type.equals(Constants.UrlTypes.onion)) {
                    System.out.println("ONION LINK : " + currentURL.URL);
                    parsedPage = WebRequesHandler.requestOnionConnection(currentURL.URL);
                } else {
                    System.out.println("BASE LINK : " + currentURL.URL);
                    parsedPage = WebRequesHandler.requestBaseConnection(currentURL.URL);
                }
                htmlParser.extractUrls(parsedPage);

            } catch (Exception ex) {
                System.out.println("Main Thread Error" + ex.toString());
            }
        }

    }

}
