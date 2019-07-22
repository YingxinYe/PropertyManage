package com.example.propertymanagement.presenter.data_listeners;

public interface ForgotPasswordListener {

    void onSuccess(String msg, String email, String password);
    void onFailure(String msg);
}
