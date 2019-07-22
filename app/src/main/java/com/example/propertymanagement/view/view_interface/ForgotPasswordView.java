package com.example.propertymanagement.view.view_interface;

public interface ForgotPasswordView {
    void getPasswordSuccess(String msg, String email, String password);
    void getPasswordFailure(String msg);
}
