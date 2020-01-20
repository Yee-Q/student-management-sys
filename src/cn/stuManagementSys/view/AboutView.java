package cn.stuManagementSys.view;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AboutView extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JLabel labTitle = null;
	private JTextArea talInfo = null;
	
	public AboutView(JFrame mf) {
		
		this.setTitle("关于我们");
		this.setModal(true);
		this.setSize(500, 200);
		this.setLayout(null);
		this.setLocationRelativeTo(mf);
		
		labTitle = new JLabel("关于我们");
		labTitle.setBounds(new Rectangle(200, 11, 136, 21));
		labTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		talInfo = new JTextArea();
		talInfo.setBounds(new Rectangle(70, 60, 430, 150));
		talInfo.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		talInfo.setLineWrap(true);
		talInfo.setWrapStyleWord(true);
		talInfo.setOpaque(false);
		talInfo.setEditable(false);//设置为不可编辑
		
		talInfo.setText("版本1.0\n" + "本系统为免费系统, 欢迎交流学习!禁止一切非法用途, 否则后果自负!");
		
		this.add(labTitle);
		this.add(talInfo);
		
		this.setVisible(true);
	}
}
