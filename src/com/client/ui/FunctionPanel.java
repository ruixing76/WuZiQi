package com.client.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import javafx.scene.layout.Border;

/**
 * 功能区，包括一个玩家列表JPanel和一个消息发送的JPanel
 * @author Mr.Bubbles
 *
 */
public class FunctionPanel extends JPanel{
	private static FunctionPanel functionPanel=null;
	
	private MessagePanel messagePanel=MessagePanel.getInstance();
	private PlayerPanel playerPanel=PlayerPanel.getInstance();
	
	public static FunctionPanel getInstance(){
		if(functionPanel==null){
			functionPanel=new FunctionPanel();
		}
		return functionPanel;
	}
	private FunctionPanel(){
		setLayout(new BorderLayout());
		add(playerPanel,BorderLayout.WEST);
		add(messagePanel,BorderLayout.CENTER);
	}
}
