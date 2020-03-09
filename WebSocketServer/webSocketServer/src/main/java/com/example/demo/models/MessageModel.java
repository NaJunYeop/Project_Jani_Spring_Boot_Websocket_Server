package com.example.demo.models;

public class MessageModel {

    private int msgId;
    private int msgIsRead;
    private int msgCount;
    private String msgOwner;
    private String chatChannel;
    private String msgSenderName;
    private String msgSenderSideDate;
    private String msgContent;
    
    public MessageModel() {
    	
    }

    public MessageModel(int msgIsRead, int msgCount, String msgOwner, String chatChannel, String msgSenderName, String msgSenderSideDate, String msgContent) {
        this.msgIsRead = msgIsRead;
        this.msgCount = msgCount;
        this.msgOwner = msgOwner;
        this.chatChannel = chatChannel;
        this.msgSenderName = msgSenderName;
        this.msgSenderSideDate = msgSenderSideDate;
        this.msgContent = msgContent;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getMsgIsRead() {
        return msgIsRead;
    }

    public void setMsgIsRead(int msgIsRead) {
        this.msgIsRead = msgIsRead;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public String getMsgOwner() {
        return msgOwner;
    }

    public void setMsgOwner(String msgOwner) {
        this.msgOwner = msgOwner;
    }

    public String getChatChannel() {
        return chatChannel;
    }

    public void setChatChannel(String chatChannel) {
        this.chatChannel = chatChannel;
    }

    public String getMsgSenderName() {
        return msgSenderName;
    }

    public void setMsgSenderName(String msgSenderName) {
        this.msgSenderName = msgSenderName;
    }

    public String getMsgSenderSideDate() {
        return msgSenderSideDate;
    }

    public void setMsgSenderSideDate(String msgSenderSideDate) {
        this.msgSenderSideDate = msgSenderSideDate;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
