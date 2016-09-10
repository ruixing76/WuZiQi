package com.server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.client.tools.ListTool;
import com.server.tools.HashMapTool;
import com.server.tools.MessageTool;
import com.server.tools.Player;

/**
 * 服务器游戏协议解析器 只对收到的String进行处理
 * 
 * @author Mr.Bubbles
 *
 */
public class Resolver {
	private String message = null;
	private String playerID = null;
	private Socket socket = null;
	private PrintStream writer = null;
	private BufferedReader reader = null;

	public Resolver(String playerID, String message, Socket socket) {
		this.message = message;
		this.playerID = playerID;
		this.socket = socket;
	}

	// 协议解析的主方法
	public void resolve() {
		try {
			String[] messageSplit = message.split(":");
			String header = messageSplit[0];
			String content = messageSplit[1].trim();
			
			if (header.equals("HELLO")) {
				playerLogin(content);
			} 
			else if(header.equals("LIST")){
				sendPlayerList();
			}
			else if (header.equals("CHALL")) {
				String[] IDSet=content.split("#");
				String fromID=IDSet[0];
				String toID=IDSet[1];
				askPlayerToBattle(fromID, toID);
				flushPlayerList();
			}
			//被挑战方同意挑战，开始游戏
			else if(header.equals("YESCHALL")){
				String[] IDSet=content.split("#");
				String fromID=IDSet[0];
				String toID=IDSet[1];
				agreePlayerToBattle(fromID, toID);
				youAreBlack(toID);
				youAreWhite(fromID);
				HashMapTool.getInstance().getPlayer(fromID).setBusy();
				HashMapTool.getInstance().getPlayer(toID).setBusy();
				flushPlayerList();
				//setBusy(fromID,toID);
				
			}
			else if(header.equals("NOCHALL")){
				String[] IDSet=content.split("#");
				String fromID=IDSet[0];
				String toID=IDSet[1];
				refusePlayerToBattle(fromID, toID);
				flushPlayerList();
			}
			else if(header.equals("YOURTURN")){
				String[] IDSet=content.split("#");
				String toID=IDSet[1];
				sendTurn(content, toID);
				flushPlayerList();
			}
			else if(header.equals("WIN")){
				String[] IDSet=content.split("#");
				String fromID=IDSet[0];
				String toID=IDSet[1];
				sendWin(content, toID);
				//releaseBusy(fromID,toID);
				HashMapTool.getInstance().getPlayer(fromID).releaseBusy();
				HashMapTool.getInstance().getPlayer(toID).releaseBusy();
				flushPlayerList();
			}
			else if(header.equals("LOSE")){
				String[] IDSet=content.split("#");
				String fromID=IDSet[0];
				String toID=IDSet[1];
				sendLose(content, toID);
				//releaseBusy(fromID,toID);
				HashMapTool.getInstance().getPlayer(fromID).releaseBusy();
				HashMapTool.getInstance().getPlayer(toID).releaseBusy();
				flushPlayerList();
			}
			else if(header.equals("SAY")){
				String[] IDSet=content.split("#");
				String toID=IDSet[1];
				sendMessage(content.split("#")[2], toID);
				flushPlayerList();
			}
			
		} catch (Exception e) {
		}
	}

	public Resolver() {

	}

	// 玩家登录
	public void playerLogin(String playerID) throws IOException {
		Player player = new Player(playerID, socket);
		player.setReader(socket.getInputStream());
		player.setWriter(socket.getOutputStream());
		HashMapTool.getInstance().addPlayer(playerID, player);
		MessageTool.getInstance().addMessage("玩家" + playerID + "上线！");
		sendPlayerList();
		flushPlayerList();
	}

	// 玩家下线
	public void playerOffLine(String playerID) {  //如果该玩家正在对战还要进一步处理
		HashMapTool.getInstance().dropPlayer(playerID);
		sendPlayerList();
		flushPlayerList();
		MessageTool.getInstance().addMessage("玩家" + playerID + "下线！");
	}

	// 发送玩家列表
	public void sendPlayerList() {
		HashMap playerMap = HashMapTool.getInstance().getPlayerMap();
		Iterator iter = playerMap.entrySet().iterator();
		String playerList = HashMapTool.getInstance().getPlayerList();
		while (iter.hasNext()) {		//遍历玩家map，给每个玩家发送玩家列表
			try {
				Map.Entry entry = (Map.Entry) iter.next();  
			    Player player = (Player)entry.getValue(); //获取player对象
				writer = player.getWriter();
				writer.println("LIST:" + playerList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//更新服务器玩家列表
	public void flushPlayerList(){
		ListTool.getInstance().removePlayer();
		HashMap playerMap = HashMapTool.getInstance().getPlayerMap();
		Iterator iter = playerMap.entrySet().iterator();
		while (iter.hasNext()) {		
			try {
				Map.Entry entry = (Map.Entry) iter.next();  
			    Player player = (Player)entry.getValue();
				ListTool.getInstance().addPlayer(player.getPlayerID());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//请求玩家
	public void askPlayerToBattle(String fromID,String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("NEWCHALL:"+fromID+"#"+toID);
	}
	//返回对方同意对战的消息
	public void agreePlayerToBattle(String fromID,String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("YESCHALL:"+fromID+"#"+toID);
	}
	//返回不同意对战的消息
	public void refusePlayerToBattle(String fromID,String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("NOCHALL:"+fromID+"#"+toID);
	}
	//通知黑棋先手
	public void youAreBlack(String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("YOUAREBLACK:"+toID);
	}
	//通知白棋后手
	public void youAreWhite(String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("YOUAREWHITE:"+toID);
	}
	//通知toID下棋
	public void sendTurn(String message,String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("YOURTURN:"+message);
	}
	//通知toID对方赢
	public void sendWin(String message,String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("WIN:"+message);
	}
	//通知toID对方输
	public void sendLose(String message,String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("LOSE:"+message);
	}
	//消息发送
	public void sendMessage(String message,String toID){
		HashMapTool.getInstance().getPlayer(toID).getWriter().println("SAY:"+message);
	}

}