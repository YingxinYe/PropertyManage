package com.example.propertymanagement.model.presenter_impl;

import android.content.Context;

import com.example.propertymanagement.model.interactor.ineractor_interface.SignupInteractor;
import com.example.propertymanagement.model.interactor.interactor_impl.SignupInteractorImpl;
import com.example.propertymanagement.presenter.data_listeners.SignupListener;
import com.example.propertymanagement.presenter.presenter_interface.SignupPresenter;
import com.example.propertymanagement.view.view_interface.SignupView;

public class SignupPresenterImpl implements SignupPresenter, SignupListener {

    private SignupView signupView;
    private SignupInteractorImpl signupInteractor;

    public SignupPresenterImpl(SignupView signupView){
        this.signupView = signupView;
        this.signupInteractor = new SignupInteractorImpl(this);
    }

    @Override
    public void getResponseFromApi(Context context, String email, String landlord_email, String password, String account_for) {
        signupInteractor.getRetrofitCall(context, email, landlord_email, password, account_for);
    }

    @Override
    public void onSuccess(String message) {
        signupView.signupSuccess(message);
    }

    @Override
    public void onFailure(String message) {
        signupView.signupFail(message);
    }
}
