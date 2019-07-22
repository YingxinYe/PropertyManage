package com.example.propertymanagement.model.interactor.interactor_impl;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.propertymanagement.R;
import com.example.propertymanagement.model.helpers.SessionManager;
import com.example.propertymanagement.model.interactor.ineractor_interface.GetPropertyListInteractor;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.model.pojo.PropertyResponse;
import com.example.propertymanagement.model.pojo.User;
import com.example.propertymanagement.presenter.data_listeners.GetPropertyListListener;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPropertyListImpl implements GetPropertyListInteractor {
    private GetPropertyListListener getPropertyListListener;

    public GetPropertyListImpl(GetPropertyListListener getPropertyListListener){
        this.getPropertyListListener=getPropertyListListener;
    }
    @Override
    public void getRetrofitCall_checkListSize(Context context) {
        //{"msg":"success","userid":"81","usertype":"landlord","useremail":"w@gmail.com","appapikey":"c2f73c05a6bfb04219cddcbeb735d63d"}
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        User myUser=SessionManager.getUser();
        Call<PropertyResponse> mycall= apiInterface.GetPropertyList(myUser.getUserid(),myUser.getUsertype());
        mycall.enqueue(new Callback<PropertyResponse>() {
            @Override
            public void onResponse(Call<PropertyResponse> call, Response<PropertyResponse> response) {

                ArrayList<Property> list=response.body().getMlist();
                getPropertyListListener.onSuccess("get Property List good",list,response.body());

            }
            @Override
            public void onFailure(Call<PropertyResponse> call, Throwable t) {
                getPropertyListListener.onFail("Fail to get Property list"+t.getMessage());
            }
        });
    }

    @Override
    public void getRetrofitCallOnly(Context context) {

    }
}
