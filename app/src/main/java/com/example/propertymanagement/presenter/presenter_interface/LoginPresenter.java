package com.example.propertymanagement.presenter.presenter_interface;

import android.content.Context;

public interface LoginPresenter {

    void getResponseFromApi(Context context, String email, String password);
}
