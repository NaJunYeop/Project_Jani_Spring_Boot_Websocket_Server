package com.example.demo.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="chat_room_model")
public class ChatRoomModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name="sender_chat_channel")
    private String senderChatChannel;
	
	@Column(name="chat_room_name")
    private String chatRoomName;
	
	@Column(name="user_Name")
	private String userName;
	
    private int type;

    public String getSenderChatChannel() {
        return senderChatChannel;
    }

    public void setSenderChatChannel(String senderChatChannel) {
        this.senderChatChannel = senderChatChannel;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String getUserName() {
    	return this.userName;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
}