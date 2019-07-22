package com.example.propertymanagement.model.interactor.interactor_impl;

import android.content.Context;
import android.util.Log;

import com.example.propertymanagement.model.interactor.ineractor_interface.RemovePropertyInteractor;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.MsgResponse;
import com.example.propertymanagement.model.pojo.Property;
import com.example.propertymanagement.presenter.data_listeners.RemovePropertyListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemovePropertyInteractorImpl implements RemovePropertyInteractor {

    RemovePropertyListener removePropertyListener;
    int pos;


    public RemovePropertyInteractorImpl(RemovePropertyListener removePropertyListener){
        this.removePropertyListener=removePropertyListener;
    }

    @Override
    public void removeProperty(Context context, ArrayList<Property> mlist, int position) {
        pos=position;
        Property select_property=mlist.get(position);
        ApiInterface apiInterface= ApiClient.getClient().create(ApiInterface.class);
        final Call<MsgResponse> mycall= apiInterface.RemoveProperty(select_property.getId());
        mycall.enqueue(new Callback<MsgResponse>() {
            @Override
            public void onResponse(Call<MsgResponse> call, Response<MsgResponse> response) {
                removePropertyListener.onSuccessRemoveProperty("Successfully delete",pos);
            }
            @Override
            public void onFailure(Call<MsgResponse> call, Throwable t) {
                removePropertyListener.onFailRemoveProperty("remove property fail "+t.getMessage());
            }
        });
    }
}
