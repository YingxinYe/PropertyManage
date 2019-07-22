package com.example.propertymanagement.presenter.presenter_interface;

import android.content.Context;

public interface AddPropertyPresenter {

    void AddPropertyConnect(Context context,
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
