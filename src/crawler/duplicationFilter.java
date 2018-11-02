package crawler;

import java.util.HashMap;
import java.util.Map;

public class duplicationFilter
{

    /*PRIVATE VARIABLES*/
    private final Map<String, duplicationFilter> child = new HashMap<String, duplicationFilter>();

    public duplicationFilter()
    {
    }
    
    /*INITIALIZATION*/
    public void Initialize()
    {
        is_url_duplicate(0, "b");
    }

    /*HELPER METHODS*/
    public boolean is_url_duplicate(int index, String URLLink)
    {
        /*BASE CONDITION : LINK ALREADY FOUND*/
        if (index >= URLLink.length())
        {
            return false;
        }

        /*POPULATE LINK IF NOT FOUND*/
        String urlCharacter = URLLink.charAt(index) + "";
        if (!child.containsKey(urlCharacter))
        {
            addUrl(0, URLLink.substring(index));
            return true;
        }
        else
        {
            /*RECURSION*/
            return child.get(urlCharacter).is_url_duplicate(index + 1, URLLink);
        }
    }

    private void addUrl(int index, String URLLink)
    {
        /*BASE CONDITION*/
        if (index >= URLLink.length())
        {
            return;
        }

        /*NEW NODE INITIALIZATION*/
        char urlCharacter = URLLink.charAt(index);
        duplicationFilter filterNode = new duplicationFilter();
        child.put(urlCharacter + "", filterNode);

        /*RECURSION TREE*/
        filterNode.addUrl(index + 1, URLLink);
    }

}
