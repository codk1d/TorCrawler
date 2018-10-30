package crawler;

public class urlModel
{
    private String parentURL;
    private String URL;
    
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
