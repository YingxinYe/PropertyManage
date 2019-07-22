package com.example.propertymanagement.model.helpers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.app.MyApplication;
import com.example.propertymanagement.model.pojo.User;

public class ToolbarManager {

    Toolbar toolbar;
    AppCompatActivity activity;
    Context context;
    SessionManager sessionManager;

    public ToolbarManager(AppCompatActivity activity){
        this.activity = activity;
        context = MyApplication.getAppContext();
        sessionManager = new SessionManager();
        toolbar = activity.findViewById(R.id.tool_bar);
    }

    public void setUpToolBar(){
        activity.setSupportActionBar(toolbar);
    }


    // menu
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = activity.getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_logout:
                sessionManager.clearLogin();
                break;

        }
        return true;
    }

    public void setUpToolBarTitle() {
        activity.setTitle("Property");
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // navigation drawer

}
