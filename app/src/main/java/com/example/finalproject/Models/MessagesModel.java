package com.example.finalproject.Models;

public class MessagesModel{
    String mobile,message;

    public MessagesModel(){

    }

    public MessagesModel(String mobile, String message) {
        this.mobile = mobile;
        this.message = message;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
