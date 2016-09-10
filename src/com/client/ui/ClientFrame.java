package com.client.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.client.gamedata.GameData;

/**
 * 五子棋游戏客户端
 * 
 * @author Mr.Bubbles
 *
 */
public class ClientFrame extends JFrame {

	private static ClientFrame client = null;
	private GamePanel gamePanel = GamePanel.getInstance();
	private FunctionPanel functionPanel = FunctionPanel.getInstance();

	public static ClientFrame getInstance() {
		if (client == null)
			client = new ClientFrame();
		return client;
	}

	// 构造函数，总体绘制
	public ClientFrame() {
		this.setTitle("五子棋对战客户端");
		this.setSize(GameData.FrameWidth, GameData.FrameHeight);
		this.setLocation(100, 100);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 添加面板组件
		this.setLayout(new BorderLayout());

		this.add(gamePanel, BorderLayout.CENTER);
		this.add(PlayerPanel.getInstance(), BorderLayout.WEST);
		this.add(MessagePanel.getInstance(), BorderLayout.EAST);
		this.setVisible(true);

	}
}
