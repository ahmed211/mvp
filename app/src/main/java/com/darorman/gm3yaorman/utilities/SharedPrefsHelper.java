package com.darorman.gm3yaorman.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ahmed Mostafa on 9/13/2018.
 */
public class SharedPrefsHelper {

    public static final String MY_PREFS = "MY_PREFS";

    public static final String USER_ID = "USER_ID";
    public static final String ADMIN_ID = "ADMIN_ID";
    public static final String DEPARTMNET_ID = "DEP_ID";

    SharedPreferences mSharedPreferences;

    public SharedPrefsHelper(Context context) {
        mSharedPreferences = context.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
    }

    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    public void putUserID(String id) {
        mSharedPreferences.edit().putString(USER_ID, id).apply();
    }

    public String getUserID() {
        return mSharedPreferences.getString(USER_ID, null);
    }

    public String getAdminID() {
        return mSharedPreferences.getString(ADMIN_ID, null);
    }

    public void putAdminID(String id) {
        mSharedPreferences.edit().putString(ADMIN_ID, id).apply();
    }

    public String getDepartmentID(){
        return mSharedPreferences.getString(DEPARTMNET_ID, null);
    }

    public void putDepartmentID(String id){
        mSharedPreferences.edit().putString(DEPARTMNET_ID, id);
    }

    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    public void setLoggedInMode(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply();
    }

}