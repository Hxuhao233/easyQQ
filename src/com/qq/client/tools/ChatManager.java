package com.qq.client.tools;
/*
 * a class for managing Chat;
 */
import java.util.*;

import com.qq.view.Chat;
public class ChatManager {
	/*
	 * String : Sender+"  "+Receiver
	 */
	private static HashMap hashMap = new HashMap<String, Chat>(); 
	public static void addChat(String senderAndReceiver,Chat chat){
		
		hashMap.put(senderAndReceiver, chat);
	}
	public static Chat getChat(String senderAndReceiver){
	
		return (Chat)hashMap.get(senderAndReceiver);
	}
	public static void Delete(String senderAndReceiver){ 
		hashMap.remove(senderAndReceiver);
	}
	
	  public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
