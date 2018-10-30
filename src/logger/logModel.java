package logger;


import java.util.ArrayList;

public class logModel
{
    public String messageType;
    public String message;
    public ArrayList<String> infoParams;
    
    public logModel(String messageType,String message,ArrayList<String> infoParams)
    {
        this.message = message;
        this.messageType = messageType;
        this.infoParams = infoParams;
    }
}
