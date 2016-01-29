package com.qq.client.tools;

import java.net.*;

import com.qq.common.Message;

import java.io.*;

public class ConnectToServerThread extends Thread {
	private Socket socket;
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public ConnectToServerThread(Socket s) {
		// TODO Auto-generated constructor stub
		socket = s;
	}
	
	public void run(){
		while(true){
			try {
				ObjectInputStream objectInputStream = 
						new ObjectInputStream(socket.getInputStream());
				Message message = (Message)objectInputStream.readObject();
				System.out.println( "From " + message.getSenderName() + " to " + message.getReceiverName() 
										+" in "+ message.getSendTime() +":\n"
										+message.getMessage() + "\r\n");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
