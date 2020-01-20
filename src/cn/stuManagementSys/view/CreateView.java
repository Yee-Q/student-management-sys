package cn.stuManagementSys.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.stuManagementSys.entity.User;
import cn.stuManagementSys.service.UserService;

public class CreateView extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	// 定义组件
	private JPanel titleJPanel, accountJPanel, pwJPanel, btJPanel;
	private JLabel titleJLabel, accountJLabel, pwJLabel;
	private JTextField accountJTF;
	private JPasswordField pwJTF;
	private JButton registJButton, refreshJButton;

	private UserService userService = new UserService();

	public CreateView(JFrame mf) {

		// 创建面板
		titleJPanel = new JPanel();
		accountJPanel = new JPanel();
		pwJPanel = new JPanel();
		btJPanel = new JPanel();

		// 各个面板设为透明
		titleJPanel.setOpaque(false);
		accountJPanel.setOpaque(false);
		pwJPanel.setOpaque(false);
		btJPanel.setOpaque(false);

		// 将窗体设为透明
		((JPanel) this.getContentPane()).setOpaque(false);

		// 插入背景
		ImageIcon img = new ImageIcon("src/images/BackGroup1.png");
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

		// 创建标签
		Font fonttitle = new Font("宋体", Font.BOLD + Font.ITALIC, 30);// 标题样式
		titleJLabel = new JLabel("注册账号");
		accountJLabel = new JLabel("账号");
		pwJLabel = new JLabel("密码");
		titleJLabel.setFont(fonttitle);

		// 创建文本框
		accountJTF = new JTextField(15);// 账号
		pwJTF = new JPasswordField(15);// 密码
		accountJTF.setOpaque(false);
		pwJTF.setOpaque(false);

		// 创建按钮
		registJButton = new JButton("注册");
		refreshJButton = new JButton("重置");

		// 按钮注册监听器
		registJButton.addActionListener(this);
		refreshJButton.addActionListener(this);

		// 将各个组件加入面板中
		titleJPanel.add(titleJLabel);
		accountJPanel.add(accountJLabel);
		accountJPanel.add(accountJTF);
		pwJPanel.add(pwJLabel);
		pwJPanel.add(pwJTF);
		btJPanel.add(registJButton);
		btJPanel.add(refreshJButton);

		// 设置布局管理
		this.setModal(true);
		this.setLayout(new GridLayout(4, 1));// 网格式布局

		// 导入面板
		this.add(titleJPanel);
		this.add(accountJPanel);
		this.add(pwJPanel);
		this.add(btJPanel);
		this.setTitle("注册");// 窗体标签
		this.setSize(400, 320);// 窗体大小
		this.setLocationRelativeTo(mf);// 居中显示
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);// 显示窗体
		this.setResizable(false);// 锁定窗体 
		this.setModal(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "注册") {

			String username = accountJTF.getText();
			String password = new String(pwJTF.getPassword());

			// 校验
			if (username.equals("") || username == null) {
				JOptionPane.showMessageDialog(this, "用户名不可为空!", "提示消息", JOptionPane.ERROR_MESSAGE);
			} else if (password.equals("") || password == null) {
				JOptionPane.showMessageDialog(this, "密码不可为空!", "提示消息", JOptionPane.ERROR_MESSAGE);
			} else if (password.length() < 6) {
				JOptionPane.showMessageDialog(this, "密码长度不可小于6位字符！", "提示消息", JOptionPane.ERROR_MESSAGE);
			} else if (password.length() > 15) {
				JOptionPane.showMessageDialog(this, "密码长度不可大于15位字符！", "提示消息", JOptionPane.ERROR_MESSAGE);
			} else {
				if (userService.searchUserByUsername(username) != null) {
					JOptionPane.showMessageDialog(this, "该用户名已被注册，请重新输入", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					User user = new User();
					user.setUsername(username);
					user.setPassword(password);
					userService.createUser(user);
					JOptionPane.showMessageDialog(this, "注册成功！您的账户名为" + username, "提示消息",
							JOptionPane.INFORMATION_MESSAGE);
					accountJTF.setText("");
					pwJTF.setText("");
				}
			}

		} else if (e.getActionCommand() == "重置") {
			accountJTF.setText("");
			pwJTF.setText("");
		} 
	}
}
