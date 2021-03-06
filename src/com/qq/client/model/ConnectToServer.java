package com.qq.client.model;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.qq.common.Message;

/*
 * client connect to server
 */
public class ConnectToServer{
	private static Socket socket ;
public static Socket getSocket() {
		return socket;
	}
public static void closeSocket(){
	if(socket.isConnected())
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}


public ConnectToServer() throws UnknownHostException, IOException{
	socket = new Socket("127.0.0.1", 9999);
}

	
	public boolean SendUserInfoToServer(Object o) throws IOException, ClassNotFoundException{
		boolean bool = false;
//		Socket socket = new Socket("127.0.0.1",	9999);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				socket.getOutputStream());
		objectOutputStream.writeObject(o);
		
		ObjectInputStream objectInputStream = new ObjectInputStream(
				socket.getInputStream());
		
		Message message = (Message)objectInputStream.readObject();
		
		if(message.getMessageType().equals("1")){
			bool = true;
		}
		return bool;
	}
	public String SendQQNumberToServer(Object o){
		return null;
		
	}
	public void SendMessageToServer(Object o)  throws UnknownHostException, IOException{

		
	}


}
