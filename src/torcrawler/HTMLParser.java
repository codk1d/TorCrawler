package torcrawler;

import java.util.ArrayList;
import java.util.List;

public class HTMLParser {

    /*VARIABLES DECLARATIONS*/
    private  ArrayList<String> urlList;

    /*INITIALIZATIONS*/
    private void initializations() {
        variableInitalization();
    }

    private void variableInitalization() {
        urlList = new ArrayList<>();
        urlList.add(Constants.baseLink);
    }

    HTMLParser() {
        initializations();
    }
    
    /*PUBLIC METHOD*/
    public int size()
    {
        return urlList.size();
    }

    public String fetchAndRemove(int index)
    {
        remove(index);
        return urlList.get(index);
    }

    public String valueOf(int index)
    {
        return urlList.get(index);
    }
    
    public void remove(int index)
    {
        urlList.remove(index);
    }
    
    public void add(String value)
    {
        urlList.add(value);
    }

    public void extractUrls(String HTML) {

    }

}
