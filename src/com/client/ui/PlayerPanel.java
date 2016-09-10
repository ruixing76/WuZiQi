package com.client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.client.listener.ChallengeListener;
import com.client.listener.EscapeListener;
import com.client.listener.FlushListener;
import com.client.listener.ListListener;
import com.client.tools.ListTool;
/**
 * 客户端玩家列表面板
 * 包含一个Jlist显示玩家，两个按钮分别为“挑战他”，和“不下了”
 * 可以动态刷新在线玩家
 * 点击玩家可以进行对战
 * @author Mr.Bubbles
 *
 */
public class PlayerPanel extends JPanel {

	private static PlayerPanel playerpanel = null;

	private JScrollPane playerPane=new JScrollPane();
	private JList<String> playerList=ListTool.getPlayerList();
	
	private JButton challengeBtn=new JButton("挑战他");
	private JButton escapeBtn=new JButton("不下了");
	private JButton flushBtn=new JButton("刷新");
	private JPanel buttonBar=new JPanel();
	
	public static PlayerPanel getInstance() {
		if (playerpanel == null) {
			playerpanel = new PlayerPanel();
		}
		return playerpanel;
	}
	
	private PlayerPanel(){  //制作布局
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("玩家列表"));  //添加边框
		add(playerPane,BorderLayout.CENTER);
		add(buttonBar,BorderLayout.SOUTH);
		playerList.addMouseListener(new ListListener());
		playerList.setFixedCellWidth(240);  //设置固定单元格大小，不会跟随内容自动调整
		//playerPane.setSize(20, 500);
		playerPane.setViewportView(playerList);
		playerPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); //只有横向滚动条
		//playerPane.setVerticalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		buttonBar.setLayout(new BorderLayout());
		buttonBar.add(challengeBtn,BorderLayout.CENTER);
		buttonBar.add(escapeBtn,BorderLayout.EAST);
		buttonBar.add(flushBtn,BorderLayout.WEST);
		challengeBtn.addActionListener(new ChallengeListener());
		escapeBtn.addActionListener(new EscapeListener());
		escapeBtn.setEnabled(false);
		flushBtn.addActionListener(new FlushListener());
	}
	
	public void setEscapeEnabledInvalid(){
		escapeBtn.setEnabled(false);
	}
	public void setEscapeEnabledValid(){
		escapeBtn.setEnabled(true);
	}


}
