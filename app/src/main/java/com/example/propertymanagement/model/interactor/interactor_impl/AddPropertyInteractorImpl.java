package com.example.propertymanagement.model.interactor.interactor_impl;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.interactor.ineractor_interface.AddPropertyInteractor;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.MsgResponse;
import com.example.propertymanagement.model.pojo.PropertyResponse;
import com.example.propertymanagement.presenter.data_listeners.AddPropertyListener;
import com.example.propertymanagement.view.fragments.PropertyEmptyFragment;
import com.example.propertymanagement.view.fragments.PropertyListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPropertyInteractorImpl implements AddPropertyInteractor {

    AddPropertyListener addPropertyListener;

    public AddPropertyInteractorImpl(AddPropertyListener addPropertyListener){
        this.addPropertyListener=addPropertyListener;
    }

    @Override
    public void GetRetrofitCall(final Context context, String propertyaddress, String propertycity, String propertystate, String propertycountry, String propertystatus,
                                String propertypurchaseprice, String propertymortageinfo, String user_id, String user_type, String latitude, String longitude) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<MsgResponse> mycall = apiInterface.AddProperty(propertyaddress, propertycity, propertystate, propertycountry, propertystatus,
                propertypurchaseprice, propertymortageinfo, user_id, user_type, latitude, longitude);
        mycall.enqueue(new Callback<MsgResponse>() {
            @Override
            public void onResponse(Call<MsgResponse> call, Response<MsgResponse> response) {
                addPropertyListener.onSuccessConnect(context,"You added this property successfully.");
            }

            @Override
            public void onFailure(Call<MsgResponse> call, Throwable t) {
                addPropertyListener.onFailConnect(context, "Fail to add, "+t.getMessage());
            }
        });
    }



}
