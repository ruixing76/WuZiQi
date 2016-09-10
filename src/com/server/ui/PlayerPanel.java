package com.server.ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.client.tools.ListTool;
import com.server.tools.MessageTool;

/*
 * 
 * 服务器端的玩家列表，会实时更新
 */
public class PlayerPanel extends JPanel {
	private static PlayerPanel playerPanel = null;

	private JList<String> playerList = ListTool.getPlayerList();
	private JScrollPane playerPane = new JScrollPane();

	public static PlayerPanel getInstance() {
		if (playerPanel == null) {
			playerPanel = new PlayerPanel();
		}
		return playerPanel;
	}

	private PlayerPanel() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("玩家列表"));
		add(playerPane);
		playerPane.setViewportView(playerList);
	}

}
