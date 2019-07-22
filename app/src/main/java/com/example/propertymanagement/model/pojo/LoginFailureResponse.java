package com.example.propertymanagement.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginFailureResponse {

    @SerializedName(value = "msg")
    ArrayList<Double> msg = new ArrayList<>();

    public LoginFailureResponse(){}

    public LoginFailureResponse(ArrayList<Double> msg) {
        this.msg = msg;
    }

    public ArrayList<Double> getMsg() {
        return msg;
    }

    public void setMsg(ArrayList<Double> msg) {
        this.msg = msg;
    }
}
