package com.darorman.gm3yaorman.api.model.admin_login;

/**
 * Created by Ahmed Mostafa on 9/18/2018.
 */
public class AdminData {
    private String name;

    private String passWord;

    private String dep;

    public AdminData(String name, String passWord, String dep) {
        this.name = name;
        this.passWord = passWord;
        this.dep = dep;
    }
}
