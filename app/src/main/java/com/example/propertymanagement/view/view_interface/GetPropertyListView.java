package com.example.propertymanagement.view.view_interface;

import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.model.pojo.PropertyResponse;

import java.util.ArrayList;


public interface GetPropertyListView {

    void onSuccessView(String message, ArrayList<Property> mlist, PropertyResponse response);
    void onFailView(String message);

}
