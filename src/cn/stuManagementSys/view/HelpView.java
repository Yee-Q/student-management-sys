package cn.stuManagementSys.view;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class HelpView extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JLabel labTitle = null;
	private JTextArea talInfo = null;
	
	public HelpView(JFrame mf) {
		
		this.setTitle("使用手册");
		this.setModal(true);
		this.setSize(500, 300);
		this.setLayout(null);
		this.setLocationRelativeTo(mf);
		
		labTitle = new JLabel("使用手册");
		labTitle.setBounds(new Rectangle(200, 11, 136, 21));
		labTitle.setFont(new Font("微软雅黑", Font.BOLD, 20));
		
		talInfo = new JTextArea();
		talInfo.setBounds(new Rectangle(30, 60, 430, 150));
		talInfo.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		talInfo.setLineWrap(true);
		talInfo.setWrapStyleWord(true);
		talInfo.setOpaque(false);
		talInfo.setEditable(false);//设置为不可编辑
		
		talInfo.setText("使用本系统首先需输入账号密码登录并进行验证. 登陆成功后即可使用相应权限的操作功能" + "用户可在查询界面通过组合查询获取自己需要的信息."
						+ "   并可修改, 删除对应的查询结果\n" + "另外, 本系统还提供了添加学生信息, 按学号删除或修改学生信息, 浏览全部学生信息与创建新用户等功能!\n" 
						);
		
		this.add(labTitle);
		this.add(talInfo);
		
		this.setVisible(true);
	}
}
