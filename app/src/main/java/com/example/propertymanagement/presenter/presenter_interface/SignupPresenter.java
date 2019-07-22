package com.example.propertymanagement.presenter.presenter_interface;

import android.content.Context;

public interface SignupPresenter {

    void getResponseFromApi(Context context, String email, String landlord_email, String password, String account_for);
}
