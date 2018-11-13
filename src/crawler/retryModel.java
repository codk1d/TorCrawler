package crawler;

import application.helperMethod;
import constants.preferences;
import java.io.Serializable;
import java.util.Date;

public class retryModel implements Serializable
{

    private final String parentURL;
    private final String URL;
    private int retryCount;
    private Date retryDate;

    public retryModel(String parentURL, String URL)
    {
        this.parentURL = parentURL;
        this.URL = URL;
        retryCount = preferences.retryCount;
        retryDate = new Date();
        updateDate();
    }

    private void updateDate()
    {
        retryDate = helperMethod.addDays(retryDate,1);
    }
    
    public Date getDate()
    {
        return retryDate;
    }

    public String getParentURL()
    {
        return parentURL;
    }

    public String getURL()
    {
        return URL;
    }

    public int getRetryCount()
    {
        return retryCount;
    }
    public void updateRetryModel()
    {
        retryCount-=1;
        retryDate = helperMethod.addDays(retryDate,1);
    }
}
