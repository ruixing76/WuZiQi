package com.server.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 服务器存储类管理工具 对外提供统一的接口
 * 
 * @author Mr.Bubbles
 *
 */
public class HashMapTool {
	private static HashMapTool hashMapTool = null;
	private HashMap<String, Player> playerMap = null; // 存储已上线玩家
	private HashMap<Player, Player> battleMap = null; // 存储配对的玩家双方

	public static HashMapTool getInstance() {
		if (hashMapTool == null) {
			hashMapTool = new HashMapTool();
		}
		return hashMapTool;
	}

	// 获取玩家Map
	public HashMap<String, Player> getPlayerMap() { // 可以返回用户列表，是否破坏封装性
		if (playerMap == null) {
			playerMap = new HashMap<String, Player>();
		}
		return playerMap;
	}

	// 获取忙碌玩家Map
	public HashMap<Player, Player> getBattleMap() { //
		if (battleMap == null) {
			battleMap = new HashMap<Player, Player>();
		}
		return battleMap;
	}

	public void addPlayer(String playerID, Player player) {
		getPlayerMap().put(playerID, player);
	}

	public void dropPlayer(String playerID) {
		getPlayerMap().remove(playerID);
	}

	public void removeAllPlayer() {
		getPlayerMap().clear();
	}

	public Player getPlayer(String playerID) {
		return getPlayerMap().get(playerID);
	}

	// // 将两个玩家进行匹配，放入忙碌玩家Map
	// public void putToBusy(String fromID, String toID) {
	// Player fromPlayer=getPlayerMap().get(fromID);
	// Player toPlayer=getPlayerMap().get(toID);
	// getBattleMap().put(fromPlayer, toPlayer);
	// getPlayerMap().remove(fromID);
	// getPlayerMap().remove(toID);
	// System.out.println(getPlayerMap().size());
	// }
	//
	// // 两个玩家对战结束，将他们释放回玩家Map
	// public void releaseFromBusy(String fromID, String toID) {
	// Iterator iter = getBattleMap().entrySet().iterator();
	// while (iter.hasNext()) {
	// Map.Entry entry = (Map.Entry) iter.next();
	//
	// Player p1=(Player)entry.getKey();
	// System.out.println(p1.getPlayerID());
	// if(p1.getPlayerID().equals(fromID)){
	// Player p2=getBattleMap().get(p1);
	// getBattleMap().remove(p1,p2);
	// getPlayerMap().put(p1.getPlayerID(), p1);
	// getPlayerMap().put(p2.getPlayerID(), p2);
	//
	// System.out.println(getPlayerMap().size());
	// break;
	//
	// }
	// else if(p1.getPlayerID().equals(toID)){
	// Player p2=getBattleMap().get(p1);
	// getBattleMap().remove(p1,p2);
	// getPlayerMap().put(p1.getPlayerID(), p1);
	// getPlayerMap().put(p2.getPlayerID(), p2);
	// System.out.println(getPlayerMap().size());
	// break;
	// }
	// }
	//
	// }

	// 获取玩家列表（用作协议内容发送）
	public String getPlayerList() {
		StringBuffer strbuf = new StringBuffer();
		Iterator iter = getPlayerMap().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String playerID = (String) entry.getKey();
			if (!getPlayerMap().get(playerID).getStatus()) {  //忙碌的跳过
				strbuf = strbuf.append(playerID);
				strbuf = strbuf.append("#");
			}
		}
		return strbuf.toString();
	}
}
