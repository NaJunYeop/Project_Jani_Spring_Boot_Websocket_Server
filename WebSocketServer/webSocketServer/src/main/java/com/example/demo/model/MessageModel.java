package com.example.demo.model;

public class MessageModel {
	private String senderSideDate;
    private String senderName;
    private String senderChatChannel;
    private String contents;

    public MessageModel() {

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
