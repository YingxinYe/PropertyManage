package com.example.propertymanagement.model.interactor.interactor_impl;

import android.content.Context;
import android.util.Log;

import com.example.propertymanagement.model.interactor.ineractor_interface.ForgotPasswordInteractor;
import com.example.propertymanagement.model.network.ApiClient;
import com.example.propertymanagement.model.network.ApiInterface;
import com.example.propertymanagement.model.pojo.ForgotPasswordSuccessResponse;
import com.example.propertymanagement.model.presenter_impl.ForgotPasswordPresenterImpl;
import com.example.propertymanagement.presenter.data_listeners.ForgotPasswordListener;
import com.example.propertymanagement.view.view_interface.ForgotPasswordView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordInteractorImpl implements ForgotPasswordInteractor {

    private ForgotPasswordListener forgotPasswordListener;
    ForgotPasswordSuccessResponse forgotPasswordSuccessResponse;

    public ForgotPasswordInteractorImpl(ForgotPasswordListener forgotPasswordListener){
        this.forgotPasswordListener = forgotPasswordListener;
    }

    @Override
    public void getRetrofitCall(Context context, String email) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ForgotPasswordSuccessResponse> call = apiInterface.forgotPassword(email);
        Log.i("Mytag", "url: " + call.request().url().toString());

        call.enqueue(new Callback<ForgotPasswordSuccessResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordSuccessResponse> call, Response<ForgotPasswordSuccessResponse> response) {
                forgotPasswordSuccessResponse = response.body();

                forgotPasswordListener.onSuccess("Password found succeessfully!", forgotPasswordSuccessResponse.getUseremail(), forgotPasswordSuccessResponse.getUserpassword());
            }

            @Override
            public void onFailure(Call<ForgotPasswordSuccessResponse> call, Throwable t) {
                Log.i("Mytag", "ForgotPassword throwable: " + t);
                forgotPasswordListener.onFailure("Email is not register");
            }
        });
    }
}
