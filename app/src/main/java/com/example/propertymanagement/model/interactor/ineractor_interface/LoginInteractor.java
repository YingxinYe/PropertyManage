package com.example.propertymanagement.model.interactor.ineractor_interface;

import android.content.Context;

public interface LoginInteractor {

    void getRetrofitCall(Context context, String email, String password);
}
