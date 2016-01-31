package com.qq.client.tools;

import java.net.*;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.view.Chat;
import com.qq.view.FriendList;

import java.io.*;

public class ConnectToServerThread extends Thread {
	private Socket socket;
	boolean bool ;
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ConnectToServerThread(Socket s) {
		// TODO Auto-generated constructor stub
		socket = s;
		bool = true;
	}
	public  void closeSocket(){
		if(socket.isConnected())
			try {
				System.out.println("socket is close!");
				socket.close();
				bool = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void run(){
		while(true){
			ObjectInputStream objectInputStream = null;
			try {
				if(socket.isClosed()){
					System.out.println("socket is close in ConnectToServerThread");
				}
				objectInputStream = 
							new ObjectInputStream(socket.getInputStream());

					Message message = (Message)objectInputStream.readObject();
					if(message.getMessageType().equals(MessageType.NormalMessage)){
							System.out.println( "From " + message.getSenderName() + " to " + message.getReceiverName() 
												+" in "+ message.getSendTime() +":\n"
												+message.getMessage() + " in ConnectToServerThread \r\n");
						Chat chat = ChatManager.getChat(message.getReceiverName()+ "  "+
												message.getSenderName());
						System.out.println(message.getSenderName()+"  "+message.getReceiverName());
						if(chat==null){
							System.out.println("null of chat");
						}else{
							chat.showNewMessage(message);
						}
					}else if(message.getMessageType().equals(MessageType.ReturnOnlineFriendsMessage)){
						
						System.out.println("get online friends on ConnectToServerThread");
						String receiver = message.getReceiverName();
						System.out.println("Friendlist of " + receiver);
						FriendList friendList =FriendListManager.getFriendList(receiver);
						friendList.updateOnlineFriends(message);
						
						 
					}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			/*	System.out.println("socket is Close");
				if(socket.isClosed()){
					System.out.println("socket is close in ConnectToServerThread");
				}
				break;*/
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{

			}
		}
	}
}
