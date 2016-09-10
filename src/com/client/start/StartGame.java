package com.client.start;

import java.awt.Font;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.client.tools.ListTool;
import com.client.ui.ClientFrame;

/**
 * 启动五子棋联网游戏 
 * @author Mr.Bubbles  
 * 
 */
public class StartGame {
	public static void main(String[] args){
		//套用新的界面框架
		try {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
		}
		//字体设置
		String[] DEFAULT_FONT  = new String[]{
			    "Table.font"
			    ,"TableHeader.font"
			    ,"CheckBox.font"
			    ,"Tree.font"
			    ,"Viewport.font"
			    ,"ProgressBar.font"
			    ,"RadioButtonMenuItem.font"
			    ,"ToolBar.font"
			    ,"ColorChooser.font"
			    ,"ToggleButton.font"
			    ,"Panel.font"
			    ,"TextArea.font"
			    ,"Menu.font"
			    ,"TableHeader.font"
			    // ,"TextField.font"
			    ,"OptionPane.font"
			    ,"MenuBar.font"
			    ,"Button.font"
			    ,"Label.font"
			    ,"PasswordField.font"
			    ,"ScrollPane.font"
			    ,"MenuItem.font"
			    ,"ToolTip.font"
			    ,"List.font"
			    ,"EditorPane.font"
			    ,"Table.font"
			    ,"TabbedPane.font"
			    ,"RadioButton.font"
			    ,"CheckBoxMenuItem.font"
			    ,"TextPane.font"
			    ,"PopupMenu.font"
			    ,"TitledBorder.font"
			    ,"ComboBox.font"
			};
			// 调整默认字体
			for (int i = 0; i < DEFAULT_FONT.length; i++)
			    UIManager.put(DEFAULT_FONT[i],new Font("微软雅黑", Font.PLAIN,14));
		ListTool.getInstance().addPlayer("Waiting for players...");
		ClientFrame.getInstance();
	}
}
