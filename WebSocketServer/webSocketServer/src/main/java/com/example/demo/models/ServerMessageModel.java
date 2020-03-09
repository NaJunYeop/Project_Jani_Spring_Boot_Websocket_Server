package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="server_message_model")
public class ServerMessageModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name="client_id")
	private Integer clientId;

	@Column(name="sender_side_date")
	private String senderSideDate;
	
	@Column(name="sender_name")
    private String senderName;
	
	@Column(name="sender_chat_channel")
    private String senderChatChannel;
	
    private String contents;
	
    public ServerMessageModel() {
    	
    }
	/*
	 * public ServerMessageModel(MessageModel messageModel) { this.clientId =
	 * messageModel.getId(); this.senderChatChannel =
	 * messageModel.getSenderChatChannel(); this.senderName =
	 * messageModel.getSenderName(); this.senderSideDate =
	 * messageModel.getSenderSideDate(); this.contents = messageModel.getContents();
	 * }
	 */
	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	public String getSenderSideDate() {
        return senderSideDate;
    }

    public void setSenderSideDate(String senderSideDate) {
        this.senderSideDate = senderSideDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderChatChannel() {
        return senderChatChannel;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSenderChatChannel(String senderChatChannel) {
        this.senderChatChannel = senderChatChannel;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    
}
