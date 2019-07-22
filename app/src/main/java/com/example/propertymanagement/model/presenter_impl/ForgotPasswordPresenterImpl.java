package com.example.propertymanagement.model.presenter_impl;

import android.content.Context;

import com.example.propertymanagement.model.interactor.interactor_impl.ForgotPasswordInteractorImpl;
import com.example.propertymanagement.presenter.data_listeners.ForgotPasswordListener;
import com.example.propertymanagement.presenter.presenter_interface.ForgotPasswordPresenter;
import com.example.propertymanagement.view.view_interface.ForgotPasswordView;

public class ForgotPasswordPresenterImpl implements ForgotPasswordPresenter, ForgotPasswordListener {

    private ForgotPasswordView forgotPasswordView;
    private ForgotPasswordInteractorImpl forgotPasswordInteractor;

    public ForgotPasswordPresenterImpl(ForgotPasswordView forgotPasswordView){
        this.forgotPasswordView = forgotPasswordView;
        this.forgotPasswordInteractor = new ForgotPasswordInteractorImpl(this);
    }

    @Override
    public void onSuccess(String msg, String email, String password) {
        forgotPasswordView.getPasswordSuccess(msg, email, password);
    }

    @Override
    public void onFailure(String msg) {
        forgotPasswordView.getPasswordFailure(msg);
    }

    @Override
    public void getResponseFromApi(Context context, String email) {
        forgotPasswordInteractor.getRetrofitCall(context, email);
    }
}
