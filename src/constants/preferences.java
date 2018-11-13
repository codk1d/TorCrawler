package constants;

public class preferences
{
    /*Crawler Preferences*/
    public static int proxyPort = 9150;
    public static int maxThreadCount = 30;
    public static int requestTimeGap = 1000;
    public static int internetConsumption = 10;

    public static int maxDLinkUrlSize = 30;
    public static int retryCount = 10;
    public static int backupTimer = 300000;
    public static boolean useTorFromOS = true;
    public static String networkType = "Onion";

    /*Session Preferences*/
    public static String filepath_url = "extracted_urls.txt";
    public static String filepath_dlink = "extracted_dlinks.txt";
    public static String filepath_queue_manager = "queue_manager";
    public static String filepath_queue_manager_backup = "queue_backup//queue_manager_";

}
