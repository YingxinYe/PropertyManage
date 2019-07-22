package com.example.propertymanagement.view.view_interface;

import com.example.propertymanagement.model.pojo.User;

public interface LoginView {

    void loginSuccess(String message, User user);
    void loginFail(String message);
}
