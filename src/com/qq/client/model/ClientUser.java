package com.qq.client.model;

import java.io.IOException;

import com.qq.common.User;

public class ClientUser {

	public boolean CheckLogin(User user) throws ClassNotFoundException, IOException{
		return new ConnectToServer().SendUserInfoToServer(user);
		
	}
}
