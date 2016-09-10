package com.client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.client.gamedata.GameData;
import com.client.tools.IOTool;
import com.client.tools.MessageTool;
import com.client.ui.MessagePanel;

public class SendMessageListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		if(GameData.isConnected==false){
			JOptionPane.showConfirmDialog(null,"请先登录！",
				     "未登录",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
		}
		else if(GameData.opponentID.equals("")){
			JOptionPane.showConfirmDialog(null,"请先开始游戏！",
				     "没有玩家",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
		}
		else if(MessagePanel.getInstance().getSendField().getText().equals("")){
			JOptionPane.showConfirmDialog(null,"请不要发送空消息！",
				     "空消息提示",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION);
		}
		else{
			String sendMessage=MessagePanel.getInstance().getSendField().getText();
			IOTool.getInstance().getWriter().println("SAY:"+GameData.myID+"#"+GameData.opponentID+"#"+sendMessage); //发送消息
			MessagePanel.getInstance().getSendField().setText("");
			MessageTool.getInstance().addMessage("我说："+sendMessage);
		}
	}

}
