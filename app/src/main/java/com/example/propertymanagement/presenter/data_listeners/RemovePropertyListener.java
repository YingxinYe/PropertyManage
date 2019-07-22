package com.example.propertymanagement.presenter.data_listeners;

import android.content.Context;

import com.example.propertymanagement.model.pojo.Property;

import java.util.ArrayList;

public interface RemovePropertyListener {

    void onSuccessRemoveProperty(String message,int position);
    void onFailRemoveProperty(String message);
}
