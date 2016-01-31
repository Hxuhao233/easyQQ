package com.qq.client.tools;

import java.util.HashMap;
import java.util.Iterator;

import com.qq.view.FriendList;

public class FriendListManager {
	private static HashMap hashMap = new HashMap<String, FriendList>();
	public static void addFriendList(String QQnumber,FriendList friendList){
		hashMap.put(QQnumber, friendList);
	}
	
	public static FriendList getFriendList(String QQnumber){
		return (FriendList)hashMap.get(QQnumber);
	}
	
	
}
