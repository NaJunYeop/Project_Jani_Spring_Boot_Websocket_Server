package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.models.RegisterModel;

@Entity
@Table(name="register_model_entity")
public class ServRegisterModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="serv_reg_id")
	private Integer servRegId;
	
	@Column(name="reg_id")
	private int regId;
	
	@Column(name="reg_user_name")
	private String regUserName;
	
	@Column(name="reg_password")
	private String regPasswrod;
	
	public ServRegisterModel(RegisterModel registerModel) {
		this.regId = registerModel.getRegId();
		this.regUserName = registerModel.getRegUserName();
		this.regPasswrod = registerModel.getRegPassword();
	}

	public Integer getServRegId() {
		return servRegId;
	}

	public void setServRegId(Integer servRegId) {
		this.servRegId = servRegId;
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

	public String getRegPasswrod() {
		return regPasswrod;
	}

	public void setRegPasswrod(String regPasswrod) {
		this.regPasswrod = regPasswrod;
	}
	
}
