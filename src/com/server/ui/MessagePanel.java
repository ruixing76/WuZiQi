package com.server.ui;

import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.client.tools.ListTool;
import com.server.tools.MessageTool;

import javafx.scene.layout.Border;

/**
 * 
 * 服务器端的消息显示界面，显示服务器的更新消息
 * @author Mr.Bubbles
 */
public class MessagePanel extends JPanel{
	private static MessagePanel messagePanel = null;
	private JScrollPane scrollPane=new JScrollPane();
	private JTextArea messageArea=MessageTool.getMessageArea();
	public static MessagePanel getInstance() {
		if (messagePanel == null) {
			messagePanel = new MessagePanel();
		}
		return messagePanel;
	}
	private MessagePanel(){
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createTitledBorder("服务器消息"));
		add(scrollPane);
		scrollPane.setViewportView(messageArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
}
