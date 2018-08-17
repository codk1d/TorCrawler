package torcrawler;

public class Constants {

    /*Tor Request Handler*/
    public static String proxyIP = "127.0.0.1";
    public static int proxyPort = 9050;
    public static String onionLinkRegex = "(?:https?://)?(?:www)?(\\S*?\\.onion)";
    public static String baseLinkRegex = "\\(?(?:(http|https|ftp):\\/\\/)?(?:((?:[^\\W\\s]|\\.|-|[:]{1})+)@{1})?((?:www.)?(?:[^\\W\\s]|\\.|-)+[\\.][^\\W\\s]{2,4}|localhost(?=\\/)|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(?::(\\d*))?([\\/]?[^\\s\\?]*[\\/]{1})*(?:\\/?([^\\s\\n\\?\\[\\]\\{\\}\\#]*(?:(?=\\.)){1}|[^\\s\\n\\?\\[\\]\\{\\}\\.\\#]*)?([\\.]{1}[^\\s\\?\\#]*)?)?(?:\\?{1}([^\\s\\n\\#\\[\\]]*))?([\\#][^\\s\\n]*)?\\)?";
    public static int maxUrlDepth = 3;

    /*Formating Variables*/
    public static String lineBreak = "\n";
    public static String emptyString = "";

    /*Testing Variables*/
    public static String hostError = "none";
    public static String hostLink = "haxf4rall.com";
    public static String baseLink = "https://haxf4rall.com/2016/11/06/100-working-deep-web-onion-and-dark-web-links/";
    public static String filepath = "extractedURL.txt";
    
    /*Enums*/
    public enum UrlTypes {onion,base,none}

}
