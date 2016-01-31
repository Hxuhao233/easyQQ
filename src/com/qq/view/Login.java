package com.qq.view;
/*
 * 登录界面
 */
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.channels.ScatteringByteChannel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;

import com.qq.client.model.ClientUser;
import com.qq.client.tools.ConnectToServerThread;
import com.qq.client.tools.ConnectToServerThreadManager;
import com.qq.client.tools.FriendListManager;
import com.qq.common.*;
public class Login extends JFrame implements MouseListener{
	//north side
	JLabel jbl1;
	
	//middle side
	JTabbedPane jtp1;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField QQNumberText;
	JPasswordField QQPassWordText;
	JCheckBox box1,box2;

	
	
	//south side
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public Login()throws IOException, ClassNotFoundException{
		jbl1 = new JLabel(new ImageIcon("image/tou.gif"));
	//	jbl1.setFont(new Font(name, style, size));
		this.add(jbl1,"North");
		
		jp2 = new JPanel(new GridLayout(3, 3));
		jp3 = new JPanel();
		jp4 = new JPanel();
		
		jp2_jbl1 = new JLabel("QQ号码",JLabel.CENTER);
		jp2_jbl2 = new JLabel("QQ密码",JLabel.CENTER);
		jp2_jbl3 = new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4 = new JLabel("密码保护",JLabel.CENTER);
		jp2_jb1 = new JButton("清除");
		QQNumberText = new JTextField();
		QQPassWordText = new JPasswordField();
		box1 = new JCheckBox("隐身登录");
		box2 = new JCheckBox("记住密码");
		
		jp2.add(jp2_jbl1);
		jp2.add(QQNumberText);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(QQPassWordText);
		jp2.add(jp2_jbl3);
		jp2.add(box1);
		jp2.add(box2);
		jp2.add(jp2_jbl4);
		
		jp3 = new JPanel();
		jp4 = new JPanel();
		jtp1 = new JTabbedPane();
		
		
		jtp1.add("QQ号码", jp2);
		jtp1.add("手机号",jp3);
		jtp1.add("电子邮箱",jp4);
		this.add(jtp1,"Center");
	
		
		jp1 = new JPanel();
		jp1_jb1 = new JButton("登录");
		jp1_jb2 = new JButton("取消");
		jp1_jb3 = new JButton("注册");
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		this.add(jp1,"South");
		jp1_jb1.addMouseListener(this);
		this.setSize(348,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String []args) throws ClassNotFoundException, IOException{
		Login login = new Login();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jp1_jb1){
		User userInfo = new User(
				QQNumberText.getText(),new String(QQPassWordText.getPassword()));
		ClientUser user = new ClientUser();
			try {
				if(user.CheckLogin(userInfo)){
					if(ConnectToServerThreadManager.getThread(QQNumberText.getText()).getSocket().isClosed()){
						System.out.println("socket is close in Login");
					}
					FriendList friendList = new FriendList(QQNumberText.getText());
					FriendListManager.addFriendList(QQNumberText.getText(), friendList);
					//send the message to get the online-friends
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(
							ConnectToServerThreadManager.getThread(QQNumberText.getText())
							.getSocket().getOutputStream());
					Message message = new Message();
					message.setSenderName(QQNumberText.getText());;
					message.setMessageType(MessageType.GetOnlineFriendsMessage);
					objectOutputStream.writeObject(message);
					
					
					this.dispose();
				}else{
					JOptionPane.showMessageDialog(this, "The QQ Number or Password is wrong!");
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
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
