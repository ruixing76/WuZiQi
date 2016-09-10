package com.client.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 用于管理客户端的输入输出系统
 * 
 * @author Mr.Bubbles
 *
 */
public class IOTool {
	private static IOTool ioTool = null;
	private PrintStream writer = null;
	private BufferedReader reader = null;

	public static IOTool getInstance() {
		if (ioTool == null) {
			ioTool = new IOTool();
		}
		return ioTool;
	}

	public PrintStream getWriter() {
		return writer;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setWriter(OutputStream out) {  //使用原始输入输出流进行构造
		this.writer = new PrintStream(out,true);
	}

	public void setReader(InputStream in) {
		this.reader = new BufferedReader(new InputStreamReader(in));
	}
}
