package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friend_model")
public class FriendModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="friend_name")
	private String friendName;
	
	public String getFriendName() {
		return friendName;
	}
	
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
}
