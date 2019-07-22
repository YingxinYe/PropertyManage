package com.example.propertymanagement.presenter.data_listeners;

import com.example.propertymanagement.model.pojo.User;

public interface LoginListener {

    void onSuccess(String message, User user);
    void onFailure(String message);
}
