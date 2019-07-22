package com.example.propertymanagement.model.presenter_impl;

import android.content.Context;

import com.example.propertymanagement.model.interactor.ineractor_interface.GetPropertyListInteractor;
import com.example.propertymanagement.model.interactor.interactor_impl.GetPropertyListImpl;
import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.model.pojo.PropertyResponse;
import com.example.propertymanagement.presenter.data_listeners.GetPropertyListListener;
import com.example.propertymanagement.presenter.presenter_interface.GetPropertyListPresenter;
import com.example.propertymanagement.view.view_interface.GetPropertyListView;

import java.util.ArrayList;

public class GetPropertyListPresenterImpl implements GetPropertyListListener, GetPropertyListPresenter {

    GetPropertyListImpl getPropertyListImpl;
    GetPropertyListView getPropertyListView;

    public GetPropertyListPresenterImpl(GetPropertyListView getPropertyListView) {
        this.getPropertyListView = getPropertyListView;
        getPropertyListImpl = new GetPropertyListImpl(this);
    }

    @Override
    public void onSuccess(String message, ArrayList<Property> mlist, PropertyResponse response) {
        getPropertyListView.onSuccessView(message, mlist,response);
    }

    @Override
    public void onFail(String message) {
        getPropertyListView.onFailView(message);
    }

    @Override
    public void getPropertyListFromApi(Context context) {
        getPropertyListImpl.getRetrofitCall_checkListSize(context);
    }
}

