package crawler;

import constants.enumeration;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import constants.string;
import java.util.ArrayList;

public class nlpParser
{

    static Set<String> nounPhrases = new HashSet<>();
    static Set<String> adjectivePhrases = new HashSet<>();
    static Set<String> verbPhrases = new HashSet<>();
    String keyWords = "";

    public void getNounPhrases(Parse p)
    {
        if (p.getType().equals("NN") || p.getType().equals("NNS") || p.getType().equals("NNP") || p.getType().equals("NNPS"))
        {
            if (!keyWords.contains(p.getCoveredText()))
            {
                keyWords += p.getCoveredText() + "_";
            }
        }

        //if (p.getType().equals("JJ") || p.getType().equals("JJR") || p.getType().equals("JJS"))
        //{
        //keyWords += p.getCoveredText()+"_";
        //}
        //if (p.getType().equals("VB") || p.getType().equals("VBP") || p.getType().equals("VBG") || p.getType().equals("VBD") || p.getType().equals("VBN"))
        //{
        //keyWords += p.getCoveredText()+"_";
        //}
        for (Parse child : p.getChildren())
        {
            getNounPhrases(child);
        }
    }

    public String parserAction(String doc) throws Exception
    {
        InputStream is = new FileInputStream("en-parser-chunking.bin");
        ParserModel model = new ParserModel(is);
        Parser parser = ParserFactory.create(model);
        Parse topParses[];
        topParses = ParserTool.parseLine(doc, parser, 1);
        return keyWords;
    }

    /*THE FOLLOWNG URL FOUNDS URL EMBEDED IN TEXT OR CONTENT OF PAGE*/
    public static ArrayList<String> extractAndSaveUrlsFromContent(String HTML, String host) throws MalformedURLException, IOException, URISyntaxException, Exception
    {
        Document doc = Jsoup.connect(host).get();
        ArrayList<String> urlListFiltered = new ArrayList<String>();

        Elements links = doc.select("a[href]");

        for (Element link : links)
        {
            String URLLink = link.absUrl("href");
            if (urlHelperMethod.isUrlValid(URLLink))
            {
                urlListFiltered.add(URLLink);
            }
            if (URLLink.length() > 1 && URLLink.charAt(0) == '/' && URLLink.charAt(1) == '/')
            {
                URLLink = "http://" + URLLink.substring(2);
            }
            urlListFiltered.add(URLLink);
        }

        String html = doc.body().text();
        String[] urlList = html.split(" ");

        for (String URLLink : urlList)
        {
            if (URLLink.length() > 1 && URLLink.charAt(0) == '/' && URLLink.charAt(1) == '/')
            {
                URLLink = "http://" + URLLink.substring(2);
            }

            if (urlHelperMethod.isUrlValid(URLLink))
            {
                urlListFiltered.add(URLLink);
            }
        }

        return urlListFiltered;
    }

    public static String extractKeywords(String doc) throws Exception
    {
        nlpParser parser = new nlpParser();
        return parser.parserAction(doc);
    }

    public static String extractTitle(String HTML)
    {
        Document document = Jsoup.parse(HTML);
        if (document.title().equals(""))
        {
            return document.select("h1").text();
        }
        else
        {
            return document.title();
        }
    }

    public static String extractSummary(String HTML)
    {
        Document document = Jsoup.parse(HTML);

        String title = string.emptyString;
        String description = string.emptyString;

        Elements metaTags = document.getElementsByTag("meta");

        for (Element metaTag : metaTags)
        {
            String content = metaTag.attr("content");
            String name = metaTag.attr("name");

            if ("d.description".equals(name))
            {
                description = content;
                break;
            }
        }

        description = title + " " + description + " " + document.text();

        if (description.length() > 1050)
        {
            description = description.substring(0, 1050);
        }

        description = description.replaceAll("/n", "");
        return description;
    }

    public static boolean isStopWord(String query)
    {
        String words = "a about above across after again against all almost alone along already also although always among an and another any anybody anyone anything anywhere are area areas around as ask asked asking asks at away b back backed backing backs be became because become becomes been before began behind being beings best better between big both but by c came can cannot case cases certain certainly clear clearly come could d did differ different differently do does done down down downed downing downs during e each early either end ended ending ends enough even evenly ever every everybody everyone everything everywhere f face faces fact facts far felt few find finds first for four from full fully further furthered furthering furthers g gave general generally get gets give given gives go going good goods got great greater greatest group grouped grouping groups h had has have having he her here herself high high high higher highest him himself his how however i if important in interest interested interesting interests into is it its itself j just k keep keeps kind knew know known knows l large largely last later latest least less let lets like likely long longer longest m made make making man many may me member members men might more most mostly mr mrs much must my myself n necessary need needed needing needs never new new newer newest next no nobody non noone not nothing now nowhere number numbers o of off often old older oldest on once one only open opened opening opens or order ordered ordering orders other others our out over p part parted parting parts per perhaps place places point pointed pointing points possible present presented presenting presents problem problems put puts q quite r rather really right right room rooms s said same saw say says second seconds see seem seemed seeming seems sees several shall she should show showed showing shows side sides since small smaller smallest so some somebody someone something somewhere state states still still such sure t take taken than that the their them then there therefore these they thing things think thinks this those though thought thoughts three through thus to today together too took toward turn turned turning turns two u under until up upon us use used uses v very w want wanted wanting wants was way ways we well wells went were what when where whether which while who whole whose why will with within without work worked working works would x y year years yet you young younger youngest your yours z , . / ; ' [ ] = - 0 ( ) ' < > ? \" :  { + _ ) ( * & ^ % $ # @ ! ";
        if (words.contains(query))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String extractKeyWords(String HTML)
    {

        String keyWord = "";

        String[] tokenList = nlpParser.extractSummary(HTML).replaceAll("^\"|\"$", " ").split(" ");

        for (int e = 0; e < tokenList.length && tokenList.length < 50; e++)
        {
            String token = tokenList[e];
            String linkType = urlHelperMethod.getUrlExtension(token);
            enumeration.UrlTypes urlType = urlHelperMethod.getNetworkType(token);

            if (!nlpParser.isStopWord(token))
            {
                keyWord = keyWord + "_" + token;
            }
        }

        if (keyWord.equals(""))
        {
            return "null_null";
        }

        return keyWord;
    }

}
