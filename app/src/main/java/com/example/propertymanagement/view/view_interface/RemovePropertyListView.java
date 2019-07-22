package com.example.propertymanagement.view.view_interface;

import com.example.propertymanagement.model.pojo.Property;

import java.util.ArrayList;

public interface RemovePropertyListView {

    void onSuccessRemovePropertyListView(String message, int position);
    void onFailRemovePropertyListView(String message);
}
