package com.darorman.gm3yaorman.api.model.admin_chat;

/**
 * Created by Ahmed Mostafa on 9/30/2018.
 */
public class GetListChatResult {
    private String UserName;
    private String UserId;

    public String getUserName ()
    {
        return UserName;
    }

    public void setUserName (String UserName)
    {
        this.UserName = UserName;
    }

    public String getUserId ()
    {
        return UserId;
    }

    public void setUserId (String UserId)
    {
        this.UserId = UserId;
    }
}
