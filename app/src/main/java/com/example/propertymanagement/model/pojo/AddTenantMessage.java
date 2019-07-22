package com.example.propertymanagement.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddTenantMessage {

    @SerializedName("msg")
    private ArrayList<String> arrayList;

    public ArrayList<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }
}
