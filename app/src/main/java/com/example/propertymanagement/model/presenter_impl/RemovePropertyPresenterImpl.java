package com.example.propertymanagement.model.presenter_impl;

import android.content.Context;
import android.util.Log;

import com.example.propertymanagement.model.interactor.interactor_impl.RemovePropertyInteractorImpl;
import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.presenter.data_listeners.RemovePropertyListener;
import com.example.propertymanagement.presenter.presenter_interface.RemovePropertyPresenter;
import com.example.propertymanagement.view.view_interface.RemovePropertyListView;

import java.util.ArrayList;

public class RemovePropertyPresenterImpl implements RemovePropertyListener, RemovePropertyPresenter {

    RemovePropertyListView removePropertyListView;
    RemovePropertyInteractorImpl myImpl;
    ArrayList<Property> mlist=new ArrayList<>();

    public RemovePropertyPresenterImpl(RemovePropertyListView removePropertyListView){
        this.removePropertyListView=removePropertyListView;
        myImpl=new RemovePropertyInteractorImpl(this);

    }
    @Override
    public void removePropertyPresent(Context context,ArrayList<Property> mlist,int position) {
        myImpl.removeProperty(context,mlist,position);
    }

    @Override
    public void onSuccessRemoveProperty(String message, int position) {
        removePropertyListView.onSuccessRemovePropertyListView(message,position);

    }

    @Override
    public void onFailRemoveProperty(String message) {
        removePropertyListView.onFailRemovePropertyListView(message);
    }


}
