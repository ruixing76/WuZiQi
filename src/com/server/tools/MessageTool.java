package com.server.tools;

import javax.swing.JTextArea;

/**
 * JTextArea的包装类，便于多个类进行调用
 * @author Mr.Bubbles
 *
 */
public class MessageTool {
	private static MessageTool messageTool = null;
	private static JTextArea messageArea=null;
	
	public static MessageTool getInstance() {  //单例模式
		if (messageTool == null) {
			messageTool = new MessageTool();
		}
		return messageTool;
	}
	
	private MessageTool(){
		
	}
	
	public static JTextArea getMessageArea(){ //用于布置布局
		if(messageArea==null){
			messageArea=new JTextArea();
			messageArea.setEditable(false);
		}
		return messageArea;
	}
	
	public void addMessage(String message){  //直接使用该类调用，当做工具
		messageArea=getMessageArea();
		messageArea.append(message+"\n\n");
	}
}
