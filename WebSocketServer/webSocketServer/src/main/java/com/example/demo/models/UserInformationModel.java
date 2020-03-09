package com.example.demo.models;

import com.example.demo.entities.ServRegisterModel;

public class UserInformationModel {
	private int userInfoId;
    private String userInfoOwner;
    private String userInfoUserName;
    
    public UserInformationModel() {
    	
    }
    
    public UserInformationModel(ServRegisterModel servRegisterModel) {
    	this.userInfoUserName = servRegisterModel.getRegUserName();
    }
    
	public int getUserInfoId() {
		return userInfoId;
	}
	public void setUserInfoId(int userInfoId) {
		this.userInfoId = userInfoId;
	}
	public String getUserInfoOwner() {
		return userInfoOwner;
	}
	public void setUserInfoOwner(String userInfoOwner) {
		this.userInfoOwner = userInfoOwner;
	}
	public String getUserInfoUserName() {
		return userInfoUserName;
	}
	public void setUserInfoUserName(String userInfoUserName) {
		this.userInfoUserName = userInfoUserName;
	}
    
    
}
