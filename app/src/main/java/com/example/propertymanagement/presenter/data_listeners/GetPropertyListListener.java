package com.example.propertymanagement.presenter.data_listeners;

import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.model.pojo.PropertyResponse;

import java.util.ArrayList;

public interface GetPropertyListListener {

    void onSuccess(String message, ArrayList<Property> mlist, PropertyResponse response);
    void onFail(String message);
}
