package com.client.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.client.listener.ResetListener;

public class StatusPanel extends JPanel {
	private static StatusPanel statusPanel = null;
	private JButton resetBtn = new JButton("重来");
	private JLabel statusLbl = new JLabel("当前状态：未登录");
	private JLabel myID= new JLabel("                       您的ID：登录获取ID");

	public static StatusPanel getInstance() {
		if (statusPanel == null) {
			statusPanel = new StatusPanel();
		}
		return statusPanel;
	}
	
	public JLabel getStatus(){
		return statusLbl;
	}

	public void setStatusToOn(){
		statusLbl.setText("当前状态：已登录");
	}
	public void setStatusToOff(){
		statusLbl.setText("当前状态：未登录");
	}
	public void setResetStatusValid(){
		resetBtn.setEnabled(true);
	}
	public void setResetStatusInValid(){
		resetBtn.setEnabled(false);
	}
	public void setID(String ID){
		myID.setText("                       您的ID："+ID);
	}
	public StatusPanel(){
		this.setLayout(new BorderLayout());
		this.add(resetBtn,BorderLayout.EAST);
		this.add(statusLbl,BorderLayout.WEST);
		this.add(myID, BorderLayout.CENTER);
		resetBtn.setEnabled(false);
		resetBtn.addActionListener(new ResetListener());
	}
	
	
}
