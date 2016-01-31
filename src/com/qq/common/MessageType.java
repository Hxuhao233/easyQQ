package com.qq.common;
/*
 * message type:
 * 1->succeed in login
 * 2->failed in login
 * 3->normal message
 * 4->get online-friend
 * 5->return online-friend
 */

public interface MessageType {
	String LoginSucceedMessage = "1";
	String LoginFailMessage = "2";
	String NormalMessage = "3";
	String GetOnlineFriendsMessage = "4";
	String ReturnOnlineFriendsMessage = "5";
	
}
