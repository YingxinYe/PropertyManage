package com.example.propertymanagement.model.interactor.interactor_impl;

import android.content.Context;
import android.util.Log;

import com.example.propertymanagement.model.interactor.ineractor_interface.LoginInteractor;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.LoginFailureResponse;
import com.example.propertymanagement.model.pojo.LoginSuccessResponse;
import com.example.propertymanagement.model.pojo.User;
import com.example.propertymanagement.presenter.data_listeners.LoginListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.xml.sax.helpers.LocatorImpl;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginListener loginListener;
    LoginSuccessResponse loginSuccessResponse;

    public LoginInteractorImpl(LoginListener loginListener){
        this.loginListener = loginListener;
    }

    @Override
    public void getRetrofitCall(Context context, String email, String password) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginSuccessResponse> call = apiInterface.login(email, password);
        Log.i("Mytag", "url: " + call.request().url().toString());

        call.enqueue(new Callback<LoginSuccessResponse>() {
            @Override
            public void onResponse(Call<LoginSuccessResponse> call, Response<LoginSuccessResponse> response) {
                loginSuccessResponse = response.body();

                User user = new User();
                user.setUserid(loginSuccessResponse.getUserid());
                user.setUsertype(loginSuccessResponse.getUsertype());
                user.setUseremail(loginSuccessResponse.getUseremail());
                user.setAppapikey(loginSuccessResponse.getAppapikey());
                Log.i("Mytag", "user: " + user.getUserid());

                loginListener.onSuccess("Successfully Login!", user);
            }

            @Override
            public void onFailure(Call<LoginSuccessResponse> call, Throwable t) {
                Log.i("Mytag", "t: " + t);
                loginListener.onFailure("Login Failed!");
            }
        });
    }
}
