package com.qq.view;
/*
 * 聊天界面
 * 
 */
import javax.swing.*;

import com.qq.client.model.ConnectToServer;
import com.qq.client.tools.ConnectToServerThread;
import com.qq.client.tools.ConnectToServerThreadManager;
import com.qq.common.Message;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class Chat extends JFrame implements MouseListener{
	
	JTextField textField;
	JTextArea textArea;
	JButton sendButton;
	JPanel panel;
	
	private String UserName;
	private String FriendName;
	
	public Chat(String user,String friend){
		UserName = user;
		FriendName = friend;
		
		textArea = new JTextArea();
		textField = new JTextField(15);
		sendButton = new JButton("发送");
		sendButton.addMouseListener(this);
		panel = new JPanel(/*new GridLayout(1, 2)*/);
		panel.add(textField);
		panel.add(sendButton);
		

		this.add(textArea,"Center");
		this.add(panel, "South");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Image img=tk.getImage("image/qq.gif"); /*mouse.gif是你的图标*/
		Cursor cu=tk.createCustomCursor(img,new Point(10,10),"stick");
		this.setIconImage(img);
		//Image image = (new ImageIcon(getClass().getResource("image/qq.gif")).getImage());
		//this.setIconImage(image);
		this.setTitle(user +" Chating with " + friend +" !");
		this.setSize(400,300);
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void showNewMessage(Message message){
		String info = "From " + message.getSenderName() + " to " + message.getReceiverName() 
		+" in "+ message.getSendTime() +":\n"
		+message.getMessage() + "\r\n";
		textArea.append(	info);
	}
	
	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getFriendName() {
		return FriendName;
	}

	public void setFriendName(String friendName) {
		FriendName = friendName;
	}
	static public void main(String []args){
		Chat chat = new Chat("p","q");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Message message = new Message(textField.getText(), UserName, FriendName);
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(
					ConnectToServerThreadManager.getThread(UserName).getSocket().getOutputStream());
			objectOutputStream.writeObject(message);
			objectOutputStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			if(objectOutputStream!=null)
				try {
					objectOutputStream.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		//message.setSendTime(sendTime);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
