package com.qq.common;
/*
 * message type:
 * 1->succeed in login
 * 2->failed in login
 * 3->normal message
 * 4->get online-friend
 * 5->return online-friend
 */

import java.util.Date;

import javax.sql.rowset.JdbcRowSet;

public class Message implements java.io.Serializable{
	private String messageType;
	private String message;
	private String senderName;
	private String receiverName;
	private String sendTime;
	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	} 


	public Message(String mes,String sender,String receiver){
		message = mes;
		senderName = sender;
		receiverName = receiver;
		sendTime = new Date().toString();
		
	}
	public Message(){
		
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

}
