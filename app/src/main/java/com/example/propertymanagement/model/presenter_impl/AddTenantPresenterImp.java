package com.example.propertymanagement.model.presenter_impl;

import android.util.Log;

import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.AddTenantMessage;
import com.example.propertymanagement.presenter.presenter_interface.AddTenantPresenter;
import com.example.propertymanagement.view.view_interface.AddTenantView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTenantPresenterImp implements AddTenantPresenter {

    private AddTenantView addTenantView;

    public AddTenantPresenterImp(AddTenantView addTenantView) {
        this.addTenantView = addTenantView;

    }

    @Override
    public void addTenant(final String name,final String email,final String address,final String mobile, final String propertyId,final String landlordId) {
        Log.d("hi", "you entered the add tenant part");
        final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.addTenant(name, email, address, mobile, propertyId, landlordId);
        Log.d("hi", "call in queue");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Log.d("hi", "response is:" + response.body());
                String s = null;
                String s1 = null;
                try {
                    s = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (s.equals("Email already exist")) {
                    addTenantView.showMessage(s);
                } else if (s.equals("successfully added")) {
                    addTenantView.showMessage(s);
                } else {

//                    GsonBuilder builder = new GsonBuilder();
//                    Gson gson = builder.create();
//                    AddTenantMessage message = gson.fromJson(response.body().toString(), AddTenantMessage.class);
//                    Log.d("hi", "message is:" + message.toString());
//                    ArrayList<String> arrayList = message.getArrayList();
//                    s1 = arrayList.get(0);
                    Call<AddTenantMessage> call1 = apiInterface.addTenant1(name, email, address, mobile, propertyId, landlordId);
                    call1.enqueue(new Callback<AddTenantMessage>() {
                        @Override
                        public void onResponse(Call<AddTenantMessage> call, Response<AddTenantMessage> response) {
                            AddTenantMessage  message = response.body();
                            ArrayList<String> arrayList = message.getArrayList();
                            String s1 = arrayList.get(0);

                            addTenantView.showMessage(s1);
                        }

                        @Override
                        public void onFailure(Call<AddTenantMessage> call, Throwable t) {
                            Log.d("tenant", "t1 is:" + t.getMessage());

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.d("tenant", "t is:" + t.getMessage());

            }
        });
    }
}
