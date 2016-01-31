package com.qq.client.model;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.qq.client.tools.ConnectToServerThread;
import com.qq.client.tools.ConnectToServerThreadManager;
import com.qq.common.Message;
import com.qq.common.User;

/*
 * client connect to server
 */
public class ConnectToServer{
	private  Socket socket ;
public  Socket getSocket() {
		return socket;
	}
public  void closeSocket(){
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
			//starts a Thread for this client to server
			if(socket.isClosed()){
				System.out.println("socket is close in ConnectToServer");
			}
			ConnectToServerThread connectToServerThread = 
					new ConnectToServerThread(socket);
			connectToServerThread.start();
			ConnectToServerThreadManager.addThread(((User)o).getQQNumber(), connectToServerThread);
			bool = true;		
			System.out.println("Connect to server!!!");
		}
		return bool;
	}
	public String SendQQNumberToServer(Object o){
		return null;
		
	}
	public void SendMessageToServer(Object o)  throws UnknownHostException, IOException{

		
	}


}
