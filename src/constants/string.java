package constants;

public class string
{

    /*Error Handling*/
    public static boolean reportException = true;
    public static String void0 = "javascript:void(0)";

    /*Constant Strings*/
    public static String requestTypeHttp = "http:";
    public static String requestTypeHttps = "https:";
    public static String requestTypeFttp = "fttp:";
    public static String proxyIP = "127.0.0.1";
    public static String onionLinkRegex = "(?:https?://)?(?:www)?(\\S*?\\.onion)";
    public static String baseLinkRegex = "\\(?(?:(http|https|ftp):\\/\\/)?(?:((?:[^\\W\\s]|\\.|-|[:]{1})+)@{1})?((?:www.)?(?:[^\\W\\s]|\\.|-)+[\\.][^\\W\\s]{2,4}|localhost(?=\\/)|\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})(?::(\\d*))?([\\/]?[^\\s\\?]*[\\/]{1})*(?:\\/?([^\\s\\n\\?\\[\\]\\{\\}\\#]*(?:(?=\\.)){1}|[^\\s\\n\\?\\[\\]\\{\\}\\.\\#]*)?([\\.]{1}[^\\s\\?\\#]*)?)?(?:\\?{1}([^\\s\\n\\#\\[\\]]*))?([\\#][^\\s\\n]*)?\\)?";
    public static String urlHostMacherRegex = "(https?://)([^:^/]*)(:\\d*)?(.*)?";

    /*Formating Variables*/
    public static String lineBreak = "\n";
    public static String emptyString = "";
    public static String urlSlashes = "//";

    /*Testing Variables*/
    public static String hostError = "none";
    public static String baseLink = "https://en.wikipedia.org/wiki/Superman";

    /*STATUS CODE*/
    public static int statusCode200 = 200;

    /*Variable Types*/
    public static String typeOnion = ".onion";
}
