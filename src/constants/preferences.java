package constants;

public class preferences
{
    /*Crawler Preferences*/
    public static int proxyPort = 9150;
    public static boolean useTorFromOS = true;
    public static int maxThreadCount = 100;
    public static int requestTimeGap = 100;
    public static int requestErrorTimeGap = 20000;
    public static int maxUrlDepth = 3;
    public static int socketTimeOutInterval = 20000;
    public static int retryCount = 10;
    public static String networkType = "Onion";

    /*Session Preferences*/
    public static String filepath_url = "extracted_urls.txt";
    public static String filepath_dlink = "extracted_dlinks.txt";
    public static String filepath_queue_manager = "queue_manager";

}
