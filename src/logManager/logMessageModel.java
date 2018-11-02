package logManager;

public class logMessageModel
{
    /*Variable Initialization*/
    private final String messageType;
    private final String message;
    
    /*Initializations*/
    public logMessageModel(String messageType,String message)
    {
        this.message = message;
        this.messageType = messageType;
    }
    
    /*Getter Setter Methods*/
    public String getMessageType()
    {
        return messageType;
    }

    public String getMessage()
    {
        return message;
    }
}
