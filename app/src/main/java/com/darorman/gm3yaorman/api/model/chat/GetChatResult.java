package com.darorman.gm3yaorman.api.model.chat;

/**
 * Created by Ahmed Mostafa on 10/3/2018.
 */
public class GetChatResult {
    private String UserType;

    private String UserName;

    private String CMessage;

    public String getUserType ()
    {
        return UserType;
    }

    public void setUserType (String UserType)
    {
        this.UserType = UserType;
    }

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public String getCMessage ()
    {
        return CMessage;
    }

    public void setCMessage (String CMessage)
    {
        this.CMessage = CMessage;
    }
}
