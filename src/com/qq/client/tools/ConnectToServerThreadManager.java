package com.qq.client.tools;
import java.net.*;
import java.io.*;
import java.util.*;
public class ConnectToServerThreadManager {
	private static HashMap hashMap= new HashMap<String,ConnectToServerThread>();
	public static void addThread(String QQnumber,ConnectToServerThread connectToServerThread){
		hashMap.put(QQnumber, connectToServerThread);
	}
	
	public static ConnectToServerThread getThread(String QQnumber){
		return (ConnectToServerThread)hashMap.get(QQnumber);
	}
	public static void Delete(String QQnumber){
		hashMap.remove(QQnumber);
	}
}
