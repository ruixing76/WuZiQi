package com.server.ui;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.server.tools.MessageTool;
import com.client.tools.ListTool;
import com.server.net.ServerThread;

/*
 * 服务器类
 * 界面包含PlayerList，和MessagePanel
 * 
 */
public class ServerFrame extends JFrame {
	private static ServerFrame server = null;
	private boolean isConnected = false;
	private MessagePanel messagePanel = MessagePanel.getInstance();
	private PlayerPanel playerPanel = PlayerPanel.getInstance();

	public static ServerFrame getInstance() {
		if (server == null) {
			server = new ServerFrame();
		}
		return server;
	}

	private ServerFrame() {
		setTitle("五子棋联网版服务端");
		setSize(600, 700);
		setLocation(100, 200);
		setLayout(new GridLayout());
		add(messagePanel);
		add(playerPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		startServer();
	}

	// 启动服务器，绑定端口开始监听
	private void startServer(){
		ServerSocket ss=null;
		try{
			isConnected=true;	//表明服务器上线
			ss=new ServerSocket(6666);
			MessageTool.getInstance().addMessage("服务器开启！");
			ListTool.getInstance().addPlayer("等待玩家......");
			//ListTool.getInstance().addPlayer("等待玩家......");
			while(isConnected){
				Socket socket=ss.accept();
				//System.out.println("客户端连接成功");
				Thread serverThread=new Thread(new ServerThread(socket));
				serverThread.start();
			}
		}catch(Exception e){
			JOptionPane.showConfirmDialog(null,"端口已经绑定！请不要启动两个服务器！",
				     "端口占用警告",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			dispose();
		}
	}
}
