package com.example.propertymanagement.model.presenter_impl;

import android.content.Context;

import com.example.propertymanagement.model.interactor.interactor_impl.LoginInteractorImpl;
import com.example.propertymanagement.model.pojo.User;
import com.example.propertymanagement.presenter.data_listeners.LoginListener;
import com.example.propertymanagement.presenter.presenter_interface.LoginPresenter;
import com.example.propertymanagement.view.view_interface.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginListener {

    private LoginView loginView;
    private LoginInteractorImpl loginInteractor;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl(this);
    }

    @Override
    public void onSuccess(String message, User user) {
        loginView.loginSuccess(message, user);
    }

    @Override
    public void onFailure(String message) {
        loginView.loginFail(message);
    }

    @Override
    public void getResponseFromApi(Context context, String email, String password) {
        loginInteractor.getRetrofitCall(context, email, password);
    }
}
