package crawler;

import java.io.Serializable;

public class urlModel implements Serializable
{
    private final String parentURL;
    private final String URL;
    
    public urlModel(String parentURL,String URL)
    {
        this.parentURL = parentURL;
        this.URL = URL;
    }
    
    public String getParentURL()
    {
        return parentURL;
    }
    
    public String getURL()
    {
        return URL;
    }
    
}
