package com.example.propertymanagement.model.interactor.ineractor_interface;

import android.content.Context;

public interface SignupInteractor {
    void getRetrofitCall(Context context, String email, String landlord_email, String password, String account_for);
}
