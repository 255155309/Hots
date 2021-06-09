package com.example.qimo.bean;

public class User {
    private String account;
    private String phone;
    private String msg;

    public User(String msg,String account, String phone) {
        this.account = account;
        this.phone = phone;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
