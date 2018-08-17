package torcrawler;

import java.util.HashMap;
import java.util.Map;

public class DuplicationFilter {

    /*PRIVATE VARIABLES*/
    
    private Map<String, DuplicationFilter> child = new HashMap<String, DuplicationFilter>();
    private char urlCharacter;
    
    /*INITIALIZATION*/
    public void Initialize(char urlCharacter)
    {
        this.urlCharacter = urlCharacter;
    }

    /*HELPER METHODS*/
    
    public boolean urlDuplicationHandler(int index,String URLLink)
    {
        /*BASE CONDITION : LINK ALREADY FOUND*/
        if(index>=URLLink.length())
        {
            return false;
        }
        
        /*POPULATE LINK IF NOT FOUND*/
        String urlCharacter = URLLink.charAt(index) + "";
        if(!child.containsKey(urlCharacter))
        {
           addUrl(0,URLLink.substring(index));
           return true;
        }
        /*RECURSION*/
        else
        {
           return child.get(urlCharacter).urlDuplicationHandler(index+1, URLLink);
        }
    }
        
    private void addUrl(int index,String URLLink)
    {
        /*BASE CONDITION*/
        if(index>=URLLink.length())
        {
            return;
        }
        
        /*NEW NODE INITIALIZATION*/
        char urlCharacter = URLLink.charAt(index);
        DuplicationFilter filterNode = new DuplicationFilter();
        filterNode.Initialize(urlCharacter);
        child.put(urlCharacter + "", filterNode);
        
        /*RECURSION TREE*/
        filterNode.addUrl(index+1,URLLink);
    }
    
}
