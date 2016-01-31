package com.qq.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
/*
 * 联系人列表
 */
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.*;

import com.qq.client.tools.ChatManager;
import com.qq.client.tools.ConnectToServerThreadManager;
import com.qq.common.Message;

public class FriendList extends JFrame{
	JScrollPane totalList;
	JButton friendButton,unknownButton,blackButton;
	JPanel totalPanel,visibleList,unvisibleList;
	JLabel []friends; 
	private String QQNumber;
	
	public FriendList(String QQnumber){
		QQNumber = QQnumber;
		
		totalPanel = new JPanel(new BorderLayout());
		visibleList = new JPanel(new GridLayout(50, 1,4,4));
		unvisibleList = new JPanel(new GridLayout(2, 1));
		friendButton = new JButton("我的好友");
		unknownButton = new JButton("陌生人");
		blackButton = new JButton("黑名单");
	//	visibleList.setPreferredSize(new Dimension(140, 3000));
	
		friends = new JLabel[50];
		for(int i =0;i<friends.length;i++){
			friends[i] = new JLabel(String.valueOf(i+1), new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			if(String.valueOf(i+1).equals(QQNumber)){
				friends[i].setEnabled(true);
			}else{
				friends[i].setEnabled(false);	
			}
			visibleList.add(friends[i]);
			friends[i].addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel label = (JLabel)e.getSource();
					label.setForeground(Color.black);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel label = (JLabel)e.getSource();
					label.setForeground(Color.red);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					//double click for Starting chat
					if(e.getClickCount() == 2){
							String friendNumber = ((JLabel)e.getSource()).getText();
							System.out.println("chat with " + friendNumber);
							Chat chat  = new Chat(QQNumber,friendNumber);
							ChatManager.addChat(QQNumber+"  "+friendNumber, chat);
							System.out.println(QQNumber+"  "+friendNumber);
					}
					
				}
			});
		}
	
		totalList = new JScrollPane(visibleList);
//		totalList.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
//		totalList.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		totalList.setSize(140,400);
		
		
		totalPanel.add(friendButton,"North");
		totalPanel.add(totalList,"Center");
		unvisibleList.add(unknownButton);
		unvisibleList.add(blackButton);
		totalPanel.add(unvisibleList,"South");
		
		this.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e){
				super.windowClosing(e);
				ConnectToServerThreadManager.Delete(QQNumber);
				
			}
		});
		this.add(totalPanel,"Center");
		this.setTitle(QQnumber );
		this.setSize(140, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void closeThread(){
		Message message = new Message();
		message.setMessageType("4");
		ObjectOutputStream objectOutputStream = null;
		try {
			objectOutputStream = new ObjectOutputStream(
					ConnectToServerThreadManager.getThread(QQNumber).getSocket().getOutputStream());
			objectOutputStream.writeObject(message);
			//objectOutputStream.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
//			if(objectOutputStream!=null)
//				try {
//					objectOutputStream.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

		}
	}
	public String getQQNumber() {
		return QQNumber;
	}


	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}
	public void updateOnlineFriends(Message message){
		String onlineFriendsQQNumber = message.getMessage();
		System.out.println("length"+ onlineFriendsQQNumber.length());
		onlineFriendsQQNumber = 
				onlineFriendsQQNumber.substring(1);
		String friendsQQNumber [] = onlineFriendsQQNumber.split(" ");
		//System.out.println("Friends:"+friendsQQNumber[0] + " " + friendsQQNumber[1]);
		System.out.println("first :" + Integer.valueOf(friendsQQNumber[0].trim()));
		for(int i = 0;i<friendsQQNumber.length;i++){
			friends[Integer.valueOf(friendsQQNumber[i].trim())-1].setEnabled(true);
		}
	}

	
	
	static public void main(String []args){
		FriendList visibleList = new FriendList("1");
		Message message = new Message();
		message.setMessage(" 1 2 3 ");
		visibleList.updateOnlineFriends(message);
	//	FriendList visibleList1 = new FriendList("2");
	//	FriendList visibleList2 = new FriendList("3");
}
}
