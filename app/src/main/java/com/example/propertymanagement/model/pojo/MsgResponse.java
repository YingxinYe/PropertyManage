package com.example.propertymanagement.model.pojo;

import com.google.gson.annotations.SerializedName;

public class MsgResponse {

    @SerializedName("msg")
    String[] msgArray=new String[]{};

    public String[] getMsgArray() {
        return msgArray;
    }

    public void setMsgArray(String[] msgArray) {
        this.msgArray = msgArray;
    }
}
