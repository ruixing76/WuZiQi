package com.server.tools;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * 玩家列表的显示管理类 包含一个JList和ListModel，以及一系列操纵的方法
 * 
 * @author Mr.Bubbles
 *
 */
public class ListTool {
	private static ListTool listTool = null;
	private static JList playerList=null;
	private static DefaultListModel listModel=null;
	
	public static ListTool getInstance(){  //单例模式
		if(listTool==null){
			listTool=new ListTool();
		}
		return listTool;
	}
	private static DefaultListModel getListModel(){ //ListModel单例
		if(listModel==null){
			listModel=new DefaultListModel();
		}
		return listModel;
	}
	public static JList getPlayerList(){   //可以作为布局组件
		if(playerList==null){
			playerList=new JList(getListModel());
		}
		return playerList;
	}
	private ListTool(){  //构造函数
		
	}
	public void addPlayer(String playerName){  //添加玩家，显示玩家名称
		getListModel().addElement(playerName);
		//getPlayerList().repaint();
	}
	public void dropPlayer(String playerName){
		getListModel().removeElement(playerName);
	}
	public void removePlayer(){
		getListModel().removeAllElements();
	}
}
