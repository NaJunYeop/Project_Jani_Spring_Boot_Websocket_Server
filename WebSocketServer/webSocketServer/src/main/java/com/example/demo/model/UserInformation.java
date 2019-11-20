package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserInformation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String phoneNumber;
	private String email;
	
	public static class Builder implements Buildable {
		private Integer id;
		private String name;
		private String phoneNumber;
		private String email = null;

		public Builder (String name, String phoneNumber) {
			this.name = name;
			this.phoneNumber = phoneNumber;
		}
		
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		@Override
		public UserInformation build() {
			return new UserInformation(this);
		}
	}
	
	public UserInformation(Builder builder) {
		this.name = builder.name;
		this.phoneNumber = builder.phoneNumber;
		this.email = builder.email;
	}
	
	public void setName() {
		this.name = name;
	}
	
	public void setPhoneNumber() {
		this.phoneNumber = phoneNumber;
	}
	
	public void setEmail() {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
}
