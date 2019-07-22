package com.example.propertymanagement.model.app;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {

    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;

    }

    public static synchronized  MyApplication getMyApplication(){
        return myApplication;
    }

    public static Context getAppContext(){
        Log.i("Mytag", "here");
        Log.i("Mytag", "myApplication: " + myApplication);
        return myApplication.getApplicationContext();
    }
}
