package com.example.propertymanagement.model.pojo;

import com.google.gson.annotations.SerializedName;

public class LoginSuccessResponse {
    @SerializedName(value = "msg")
    private String msg;
    @SerializedName(value = "userid")
    private String userid;
    @SerializedName(value = "usertype")
    private String usertype;
    @SerializedName(value = "useremail")
    private String useremail;
    @SerializedName(value = "appapikey")
    private String appapikey;

    public LoginSuccessResponse(){}

    public LoginSuccessResponse(String msg, String userid, String usertype, String useremail, String appapikey) {
        this.msg = msg;
        this.userid = userid;
        this.usertype = usertype;
        this.useremail = useremail;
        this.appapikey = appapikey;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getAppapikey() {
        return appapikey;
    }

    public void setAppapikey(String appapikey) {
        this.appapikey = appapikey;
    }
}
