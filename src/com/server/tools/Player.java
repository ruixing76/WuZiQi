package com.server.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 玩家类 包括玩家的基本信息和每个玩家所有的Socket
 * 
 * @author Mr.Bubbles
 *
 */
public class Player {

	private String playerID = null;
	private String playerName = null;
	private String opponentID = null;
	private boolean isBusy = false;
	private Socket socket = null;
	private PrintStream writer = null;
	private BufferedReader reader = null;

	public Player(String playerID, Socket socket) {
		this.playerID = playerID;
		this.socket = socket;
	}
	public boolean getStatus(){
		return this.isBusy;
	}
	public void setBusy(){
		this.isBusy=true;
	}
	public void releaseBusy(){
		this.isBusy=false;
	}
	public String getPlayerID() {
		return playerID;
	}

	public Socket getPlayerSocket() {
		return socket;
	}

	public PrintStream getWriter() {
		return writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setWriter(OutputStream out) { // 使用原始输入输出流进行构造
		this.writer = new PrintStream(out, true);
	}

	public void setReader(InputStream in) {
		this.reader = new BufferedReader(new InputStreamReader(in));
	}
	
}
