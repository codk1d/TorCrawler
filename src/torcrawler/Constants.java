package torcrawler;

public class Constants {

    /*Tor Request Handler*/
    public static String proxyIP = "127.0.0.1";
    public static String testOnionURL = "https://securityzap.com/massive-deep-web-links-2015-updated-june-2015/";
    public static String urlExtractingRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
    public static String urlTypeOnion = "onion";
    public static int proxyPort = 9050;

    /*Formating Variables*/
    public static String lineBreak = "\n";
    public static String emptyString = "";

    /*Testing Variables*/
    public static String baseLink = "https://haxf4rall.com/2016/11/06/100-working-deep-web-onion-and-dark-web-links/";
    public static String errorLogs = "CODE ERROR ";
    public static String filepath = "extractedURL.txt";

}
