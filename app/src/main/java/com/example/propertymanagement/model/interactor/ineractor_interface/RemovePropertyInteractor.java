package com.example.propertymanagement.model.interactor.ineractor_interface;

import android.content.Context;

import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.presenter.data_listeners.RemovePropertyListener;

import java.util.ArrayList;

public interface RemovePropertyInteractor {

    void removeProperty(Context context, ArrayList<Property> mlist, int position);
}
