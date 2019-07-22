package com.example.propertymanagement.presenter.presenter_interface;

import android.content.Context;

import com.example.propertymanagement.model.pojo.Property;

import java.util.ArrayList;

public interface RemovePropertyPresenter {

    void removePropertyPresent(Context context, ArrayList<Property> mlist, int position);
}
