package com.darorman.gm3yaorman.api.model.chat;

/**
 * Created by Ahmed Mostafa on 10/3/2018.
 */
public class ChatMessageResult {
    private String Message;

    private String Id;

    public String getMessage ()
    {
        return Message;
    }

    public void setMessage (String Message)
    {
        this.Message = Message;
    }

    public String getId ()
    {
        return Id;
    }

    public void setId (String Id)
    {
        this.Id = Id;
    }
}
