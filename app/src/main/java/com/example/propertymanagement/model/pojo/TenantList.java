package com.example.propertymanagement.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TenantList {

    @SerializedName("Tenants")
    private ArrayList<Tenant> arrayList;

    public ArrayList<Tenant> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Tenant> arrayList) {
        this.arrayList = arrayList;
    }
}
