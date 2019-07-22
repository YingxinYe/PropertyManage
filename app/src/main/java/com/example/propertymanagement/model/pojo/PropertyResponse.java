package com.example.propertymanagement.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class PropertyResponse implements Serializable {

    @SerializedName("Property")
    ArrayList<Property> mlist=new ArrayList<>();

    public ArrayList<Property> getMlist() {
        return mlist;
    }

    public void setMlist(ArrayList<Property> mlist) {
        this.mlist = mlist;
    }
}
