package com.client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.client.gamedata.GameData;
import com.client.ui.ChessBoard;
/**
 * 重来按钮的监听事件
 * @author Mr.Bubbles
 *
 */
public class ResetListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		GameData.Reset();
		JOptionPane.showConfirmDialog(null, "现在可以重新和其他玩家游玩啦！", "已经和对方断开连接", JOptionPane.DEFAULT_OPTION,
				JOptionPane.DEFAULT_OPTION);
	}
	
}
