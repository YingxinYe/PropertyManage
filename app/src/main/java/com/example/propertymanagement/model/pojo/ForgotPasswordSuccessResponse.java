package com.example.propertymanagement.model.pojo;

public class ForgotPasswordSuccessResponse {

    private String msg;
    private String useremail;
    private String userpassword;

    public ForgotPasswordSuccessResponse(){}

    public ForgotPasswordSuccessResponse(String msg, String useremail, String userpassword) {
        this.msg = msg;
        this.useremail = useremail;
        this.userpassword = userpassword;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
}
