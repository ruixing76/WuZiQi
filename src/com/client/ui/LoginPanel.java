package com.client.ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.client.listener.LoginListener;

public class LoginPanel extends JPanel {
	
	private static LoginPanel loginpanel = null;

	private JLabel opIP_lbl=new JLabel("Server IP:");
	private JLabel opPort_lbl=new JLabel("Server Port:");
	private static JTextField opIP_jtf =new JTextField("127.0.0.1");
	private static JTextField opPort_jtf =new JTextField("6666");
	private JButton login_btn = new JButton("Login");

	private LoginPanel() {
		
		//this.setBackground(Color.blue);
		this.setLayout(new GridLayout());
		this.add(opIP_lbl);
		this.add(opIP_jtf);
		this.add(opPort_lbl);
		this.add(opPort_jtf);
		this.add(login_btn);
		login_btn.addActionListener(LoginListener.getInstance());
	}

	public static LoginPanel getInstance() {
		if (loginpanel == null)
			loginpanel = new LoginPanel();
		return loginpanel;
	}
	
	public static JTextField getOpIP(){   //获取IP地址
		if(opIP_jtf==null)
			opIP_jtf=new JTextField("127.0.0.1");
		return opIP_jtf;
	}
	
	public static JTextField getOpPort(){	//获取端口，这两个可以修改成为返回String即可
		if(opPort_jtf==null)
			opPort_jtf=new JTextField("127.0.0.1");
		return opPort_jtf;
	}

}
