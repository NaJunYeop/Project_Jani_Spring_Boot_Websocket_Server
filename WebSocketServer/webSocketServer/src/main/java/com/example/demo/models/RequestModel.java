package com.example.demo.models;
import java.util.ArrayList;

public class RequestModel {

    private int reqId;
    private int reqType;
    private String chatChannel;
    private String reqSenderName;
    private String reqReceiverName;
    
    public RequestModel() {
    	
    }

    public RequestModel(int reqType, String reqSenderName, String reqReceiverName) {
        this.reqType = reqType;
        this.reqSenderName = reqSenderName;
        this.reqReceiverName = reqReceiverName;
    }

    public int getReqId() {
        return reqId;
    }

    public void setReqId(int reqId) {
        this.reqId = reqId;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public String getChatChannel() {
        return chatChannel;
    }

    public void setChatChannel(String chatChannel) {
        this.chatChannel = chatChannel;
    }

    public String getReqSenderName() {
        return reqSenderName;
    }

    public void setReqSenderName(String reqSenderName) {
        this.reqSenderName = reqSenderName;
    }

    public String getReqReceiverName() {
        return reqReceiverName;
    }

    public void setReqReceiverName(String reqReceiverName) {
        this.reqReceiverName = reqReceiverName;
    }
}
