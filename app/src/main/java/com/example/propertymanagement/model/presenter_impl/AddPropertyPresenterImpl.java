package com.example.propertymanagement.model.presenter_impl;

import android.content.Context;

import com.example.propertymanagement.model.interactor.ineractor_interface.AddPropertyInteractor;
import com.example.propertymanagement.model.interactor.interactor_impl.AddPropertyInteractorImpl;
import com.example.propertymanagement.presenter.data_listeners.AddPropertyListener;
import com.example.propertymanagement.presenter.presenter_interface.AddPropertyPresenter;
import com.example.propertymanagement.view.view_interface.AddPropertyView;

public class AddPropertyPresenterImpl implements AddPropertyListener, AddPropertyPresenter {

    AddPropertyInteractorImpl interactor;
    AddPropertyView addPropertyView;

    public AddPropertyPresenterImpl(AddPropertyView addPropertyView){
        this.addPropertyView=addPropertyView;
        interactor=new AddPropertyInteractorImpl(this);
    }

    @Override
    public void AddPropertyConnect(Context context,String propertyaddress, String propertycity, String propertystate, String propertycountry, String propertystatus,
                                   String propertypurchaseprice, String propertymortageinfo, String user_id, String user_type, String latitude, String longitude) {

        interactor.GetRetrofitCall(context,propertyaddress, propertycity, propertystate, propertycountry, propertystatus,
                propertypurchaseprice, propertymortageinfo, user_id, user_type, latitude, longitude);
    }

    @Override
    public void onSuccessConnect(Context context, String message) {
        addPropertyView.onSuccessAddView(message);
    }

    @Override
    public void onFailConnect(Context context, String message) {
        addPropertyView.onFailAddView(message);
    }


}
