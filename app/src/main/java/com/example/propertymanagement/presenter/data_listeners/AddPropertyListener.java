package com.example.propertymanagement.presenter.data_listeners;

import android.content.Context;

public interface AddPropertyListener  {

    void onSuccessConnect(Context context,String message);
    void onFailConnect(Context context,String message);
}
