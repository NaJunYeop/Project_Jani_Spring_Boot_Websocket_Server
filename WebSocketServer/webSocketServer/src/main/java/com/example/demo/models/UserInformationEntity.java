package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="user_information")
public class UserInformationEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	private String email;
	
	public static class Builder implements Buildable {
		private String userName;
		private String phoneNumber;
		private String email;

		public Builder (String userName) {
			this.userName = userName;
		}
		
		public Builder setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}
		
		public Builder setEmail(String email) {
			this.email = email;
			return this;
		}

		@Override
		public UserInformationEntity build() {
			return new UserInformationEntity(this);
		}
	}
	
	public UserInformationEntity() {
		
	}
	
	public UserInformationEntity(Builder builder) {
		this.userName = builder.userName;
		this.phoneNumber = builder.phoneNumber;
		this.email = builder.email;
	}
	
	public void setName(String userName) {
		this.userName = userName;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
}
