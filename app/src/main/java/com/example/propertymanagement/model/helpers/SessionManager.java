package com.example.propertymanagement.model.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.propertymanagement.model.app.MyApplication;
import com.example.propertymanagement.model.pojo.User;
import com.example.propertymanagement.view.activities.LoginChoiceActivity;

public class SessionManager {

    static Context context;

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    private static final String USER_INFO = "user_info";

    private static final String USER_ID = "userid";
    private static final String USER_TYPE = "usertype";
    private static final String USER_EMAIL = "useremail";
    private static final String APPAPIKEY = "appapikey";

    public SessionManager(){
        this.context = MyApplication.getAppContext();
        sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static User getUser(){
        User user = new User();
        user.setUserid(sharedPreferences.getString(USER_ID, null));
        user.setUsertype(sharedPreferences.getString(USER_TYPE, null));
        user.setUseremail(sharedPreferences.getString(USER_EMAIL, null));
        user.setAppapikey(sharedPreferences.getString(APPAPIKEY, null));
        return user;
    }

    public void saveUser(User user){
        editor.putString(USER_ID, user.getUserid());
        editor.putString(USER_TYPE, user.getUsertype());
        editor.putString(USER_EMAIL, user.getUseremail());
        editor.putString(APPAPIKEY, user.getAppapikey());

        editor.commit();
    }

    public static void clearLogin(){
        editor.clear().commit();
        goToLogin();
    }

    public boolean checkLogin(){
        User user = getUser();
        return user.getAppapikey() != null;
    }

    public static void goToLogin(){
        Toast.makeText(context, "Please Log In!", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(context, LoginChoiceActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
