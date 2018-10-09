package com.darorman.gm3yaorman.api.model.admin_login;

/**
 * Created by Ahmed Mostafa on 9/18/2018.
 */
public class AdminLoginResult {
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
