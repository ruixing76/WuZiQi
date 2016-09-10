package com.client.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.client.gamedata.GameData;
import com.client.tools.IOTool;

public class FlushListener implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		if(GameData.isConnected){
			IOTool.getInstance().getWriter().println("LIST:"+GameData.myID+"#");
		}
	}

}
