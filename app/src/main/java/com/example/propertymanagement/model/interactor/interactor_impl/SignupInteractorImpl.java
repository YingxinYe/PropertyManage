package com.example.propertymanagement.model.interactor.interactor_impl;

import android.content.Context;
import android.util.Log;

import com.example.propertymanagement.model.interactor.ineractor_interface.SignupInteractor;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.presenter.data_listeners.SignupListener;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupInteractorImpl implements SignupInteractor {

    String message = "";
    private SignupListener signupListener;

    public SignupInteractorImpl(SignupListener signupListener){
        this.signupListener = signupListener;
    }

    @Override
    public void getRetrofitCall(Context context, String email, String landlord_email, String password, String account_for) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiInterface.register(email, landlord_email, password, account_for);
        Log.i("Mytag", "url: " + call.request().url().toString());

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    message = response.body().string().trim();
                } catch (IOException e) {
                    Log.i("Mytag", "error: " + e);
                }
                Log.i("Mytag", "response: " + message);
                if(message.equals("successfully registered")){
                    signupListener.onSuccess(message);
                }else if(message.equals("Email already exsist")){
                    Log.i("Mytag", "email exist");
                    signupListener.onFailure(message);
                }else{
                    signupListener.onFailure("Format Wrong");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Mytag", "t: " + t);
                signupListener.onFailure("Signup Failed");
            }
        });
    }
}
