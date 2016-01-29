package com.qq.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
/*
 * 联系人列表
 */
import java.awt.event.*;
import javax.swing.*;

public class FriendList extends JFrame{
	JScrollPane totalList;
	JButton friendButton,unknownButton,blackButton;
	JPanel totalPanel,visibleList,unvisibleList;

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
	
		JLabel []jbLabels = new JLabel[50];
		for(int i =0;i<jbLabels.length;i++){
			jbLabels[i] = new JLabel(i+1+"", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			visibleList.add(jbLabels[i]);
			jbLabels[i].addMouseListener(new MouseListener() {
				
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
							Chat chat  = new Chat(QQNumber.trim(),friendNumber.trim());
							Thread thread = new Thread(chat);
							thread.start();
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
		
		
		this.add(totalPanel,"Center");
		this.setTitle(QQnumber );
		this.setSize(140, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public String getQQNumber() {
		return QQNumber;
	}


	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}

	
	
	static public void main(String []args){
		//FriendList visibleList = new FriendList();
}
}
