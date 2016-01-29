package com.qq.common;
/*
 * QQNumer 
 * QQPassword
 */
public class User implements java.io.Serializable{
	private String QQNumber;
	private String Password;
	
	public User(String QQnumber,String password){
		QQNumber = QQnumber;
		Password = password;
	}
	public String getQQNumber() {
		return QQNumber;
	}
	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		this.Password = password;
	}
	
}
