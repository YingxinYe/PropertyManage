package com.example.propertymanagement.model.interactor.ineractor_interface;

import android.content.Context;

public interface AddPropertyInteractor {

    void GetRetrofitCall(Context context,
                         String propertyaddress,
                         String propertycity,
                         String propertystate,
                         String propertycountry,
                         String propertystatus,
                         String propertypurchaseprice,
                         String propertymortageinfo,
                         String user_id,
                         String user_type,
                         String latitude,
                         String longitude);
}
