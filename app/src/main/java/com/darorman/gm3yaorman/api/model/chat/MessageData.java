package com.darorman.gm3yaorman.api.model.chat;

/**
 * Created by Ahmed Mostafa on 10/3/2018.
 */
public class MessageData {
    private String Personid, CMessage, UserType;

    public MessageData(String Personid, String CMessage, String UserType) {
        this.Personid = Personid;
        this.CMessage = CMessage;
        this.UserType = UserType;
    }
}
