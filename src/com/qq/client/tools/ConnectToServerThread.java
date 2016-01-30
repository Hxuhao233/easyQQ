package com.qq.client.tools;

import java.net.*;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.qq.common.Message;
import com.qq.view.Chat;

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
		while(bool){
			ObjectInputStream objectInputStream = null;
			try {
		/*		if(socket!=null&&
						socket.getInputStream().read()!=-1){
		*/			
				objectInputStream = 
							new ObjectInputStream(socket.getInputStream());

					Message message = (Message)objectInputStream.readObject();
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
					
					//objectInputStream.close();
		/*		}else{
					bool = false;
				}*/
					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{

			}
		}
	}
}
