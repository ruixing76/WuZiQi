package com.client.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * 游戏JPanel，包括一个设置服务器IP和端口的JPanel以及棋盘JPanel
 * @author Mr.Bubbles
 *
 */
public class GamePanel extends JPanel {
	private static GamePanel gamePanel=null;
	private LoginPanel loginPanel=LoginPanel.getInstance();
	private ChessBoard chessBoard=ChessBoard.getInstance();
	private StatusPanel statusPanel=StatusPanel.getInstance();
	
	public static GamePanel getInstance(){
		if(gamePanel==null){
			gamePanel=new GamePanel();
		}
		return gamePanel;
	}
	private GamePanel(){
		this.setLayout(new BorderLayout());
		add(loginPanel,BorderLayout.NORTH);
		add(chessBoard,BorderLayout.CENTER);
		add(statusPanel,BorderLayout.SOUTH);
	}
}
