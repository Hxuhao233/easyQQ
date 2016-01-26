package com.qq.view;
/*
 * 聊天界面
 */
import javax.swing.*;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
public class Chat extends JFrame{
	
	JTextField textField;
	JTextArea textArea;
	JButton sendButton;
	JPanel panel;
	public Chat(){
		textArea = new JTextArea();
		textField = new JTextField(15);
		sendButton = new JButton("发送");
		panel = new JPanel(/*new GridLayout(1, 2)*/);
		panel.add(textField);
		panel.add(sendButton);
		

		this.add(textArea,"Center");
		this.add(panel, "South");
		Toolkit tk=Toolkit.getDefaultToolkit();
		Image img=tk.getImage("image/qq.gif"); /*mouse.gif是你的图标*/
		Cursor cu=tk.createCustomCursor(img,new Point(10,10),"stick");
		this.setCursor(cu);
		//Image image = (new ImageIcon(getClass().getResource("image/qq.gif")).getImage());
		//this.setIconImage(image);
		this.setTitle("Chating!");
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	static public void main(String []args){
		Chat chat = new Chat();
	}

}
