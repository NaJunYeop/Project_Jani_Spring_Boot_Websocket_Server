package com.example.demo.model;

public class RegisterModel {
	private String userName;
	private String phoneNumber;
	private String email;
	
	public String getUserName() {
		return userName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "\nName : " + userName +
				"\nPhone Number : " + phoneNumber +
				"\nEmail : " + email;
	}
}
