package com.client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.client.gamedata.GameData;
import com.client.net.ClientThread;
import com.client.tools.IOTool;
import com.client.ui.ClientFrame;
import com.client.ui.LoginPanel;
import com.client.ui.StatusPanel;
/**
 * 客户端登录按钮监听器
 * @author Mr.Bubbles
 *
 */
public class LoginListener implements ActionListener {
	private static LoginListener loginListener = null;

	public static LoginListener getInstance() { // 登录按钮监听器单例模式
		if (loginListener == null) {
			loginListener = new LoginListener();
		}
		return loginListener;
	}

	public void actionPerformed(ActionEvent e) {
		String serverIP = LoginPanel.getOpIP().getText().trim();
		int port = Integer.parseInt(LoginPanel.getOpPort().getText().trim());
		 // 已经连接过了
		if (GameData.isConnected) {
			JOptionPane.showConfirmDialog(null, "您已经登录！", "连接警告", JOptionPane.DEFAULT_OPTION,
					JOptionPane.ERROR_MESSAGE);
		}
		// 建立连接
		else {
			try {
				Socket socket = new Socket(serverIP, port);
				GameData.isConnected = true;
				IOTool.getInstance().setWriter(socket.getOutputStream());
				IOTool.getInstance().setReader(socket.getInputStream());
				Thread clientThread = new Thread(new ClientThread());
				clientThread.start();
				StatusPanel.getInstance().setStatusToOn();
				
				// 测试发送
				GameData.myID=String.valueOf(this.hashCode());
//				ClientFrame.getInstance().setTitle(GameData.myID);
				StatusPanel.getInstance().setID(GameData.myID);
				IOTool.getInstance().getWriter().println("HELLO:" + GameData.myID);

			} catch (UnknownHostException e1) { // 连接失败的时候处理
				JOptionPane.showConfirmDialog(null, "服务器未开启", "连接错误", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showConfirmDialog(null, "服务器连接错误", "连接错误", JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

}
