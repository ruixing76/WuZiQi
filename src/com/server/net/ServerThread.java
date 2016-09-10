package com.server.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 对于每个客户端的处理线程
 * 
 * @author Mr.Bubbles
 *
 */
public class ServerThread implements Runnable {
	private boolean isConnected = false; // 客户端连接
	private Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 负责解析服务端的报文信息，送给Resolver解析器解析
	public void run() {
		isConnected = true;
		String playerID = null;
		try {
			while (isConnected) {
				InputStream playerReader=socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(playerReader));
				String message = reader.readLine();
				String[] messageSplit = message.split(":");
				playerID = messageSplit[1].split("#")[0].trim();
//				System.out.println(playerID);
				new Resolver(playerID, message,socket).resolve();
			}
		} catch (Exception e) { // 读取输入输出流的时候发生了异常，直接判定客户端下线
								// 走的时候还要发个GOODBYE好像不太好233
			isConnected = false;
//			System.out.println("玩家已经下线");
			new Resolver().playerOffLine(playerID);  //玩家下线的处理过程
			//e.printStackTrace();
		}
	}

}
