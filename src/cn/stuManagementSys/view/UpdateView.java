package cn.stuManagementSys.view;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class UpdateView extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JLabel labTitle = null;
	private JTextArea talInfo = null;
	
	public UpdateView(JFrame mf) {
		
		this.setTitle("升级");
		this.setModal(true);
		this.setSize(500, 200);
		this.setLayout(null);
		this.setLocationRelativeTo(mf);
		
		labTitle = new JLabel("升级");
		labTitle.setBounds(new Rectangle(220, 11, 136, 21));
		labTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		talInfo = new JTextArea();
		talInfo.setBounds(new Rectangle(70, 60, 430, 150));
		talInfo.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		talInfo.setLineWrap(true);
		talInfo.setWrapStyleWord(true);
		talInfo.setOpaque(false);
		talInfo.setEditable(false);//设置为不可编辑
		
		talInfo.setText("目前版本为1.0, 功能有限, 请随时关注系统升级消息!敬请期待!");
		
		this.add(labTitle);
		this.add(talInfo);
		
		this.setVisible(true);
	}
}
