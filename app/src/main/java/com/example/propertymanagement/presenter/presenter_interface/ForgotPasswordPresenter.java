package com.example.propertymanagement.presenter.presenter_interface;

import android.content.Context;

public interface ForgotPasswordPresenter {
    void getResponseFromApi(Context context, String email);
}
