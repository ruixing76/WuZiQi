package com.client.net;

import java.io.IOException;

import com.client.gamedata.GameData;
import com.client.tools.IOTool;

/**
 * 循环接收服务器的消息
 * 
 * @author Mr.Bubbles
 *
 */
public class ClientThread implements Runnable {

	public ClientThread() {

	}

	public void run() {
		while (GameData.isConnected) {
			try {
				String message = IOTool.getInstance().getReader().readLine();
				new Resolver(message).resolve();
			} catch (IOException e) {
				e.printStackTrace();
			} // 得到协议内容
		}
	}

}
