package com.example.demo.models;

public class RegisterModel {
	
    private int regId;
    private String regUserName;
    private String regPassword;

    public RegisterModel(String regUserName, String regPassword) {
        this.regUserName = regUserName;
        this.regPassword = regPassword;
    }

    public int getRegId() {
        return regId;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public String getRegUserName() {
        return regUserName;
    }

    public void setRegUserName(String regUserName) {
        this.regUserName = regUserName;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }
}
