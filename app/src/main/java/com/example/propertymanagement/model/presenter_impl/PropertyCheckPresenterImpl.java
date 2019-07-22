package com.example.propertymanagement.model.presenter_impl;

import android.text.TextUtils;
import android.view.View;

import com.example.propertymanagement.presenter.presenter_interface.PropertyValidationPresenter;
import com.example.propertymanagement.view.view_interface.PropertyValidationView;

public class PropertyCheckPresenterImpl implements PropertyValidationPresenter {

    PropertyValidationView view;

    public PropertyCheckPresenterImpl(PropertyValidationView view){
        this.view=view;
    }
    @Override
    public void checkValidation(String propertyaddress, String propertycity, String propertystate, String propertycountry, String propertystatus, String propertypurchaseprice, String propertymortageinfo) {

    }


}
