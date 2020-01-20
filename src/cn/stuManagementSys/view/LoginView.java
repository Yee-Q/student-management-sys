package cn.stuManagementSys.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.stuManagementSys.exception.UserException;
import cn.stuManagementSys.service.UserService;

public class LoginView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserService();
	
	// 定义组件
	private JPanel unamePanel, pwdPanel, buttonPanel, loginTitle;// 面板
	private JLabel titleLabel, unameLabel, pwdLabel;// 标签
	private JButton loginButton, clearButton;// 按钮
	private JTextField unameTextField;// 文本
	private JPasswordField pwdTextField;// 密码

	// 构造函数
	public LoginView() {
		
		// 创建面板并设为透明
		loginTitle = new JPanel();
		unamePanel = new JPanel();
		pwdPanel = new JPanel();
		buttonPanel = new JPanel();
		loginTitle.setOpaque(false);
		unamePanel.setOpaque(false);
		pwdPanel.setOpaque(false);
		buttonPanel.setOpaque(false);
		((JPanel) this.getContentPane()).setOpaque(false);
		
		// 插入背景图
		ImageIcon img = new ImageIcon("src/images/BackGroup1.jpg");
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		// 创建标签
		Font font = new Font("宋体", Font.BOLD + Font.ITALIC, 20);//标题样式
		titleLabel = new JLabel("欢迎进入学生信息管理系统");
		unameLabel = new JLabel("用户名");
		pwdLabel = new JLabel("密    码");
		titleLabel.setFont(font);
		
		// 创建按钮  
		loginButton = new JButton("登录");
		clearButton = new JButton("重置");
		
		// 注册监听器
		loginButton.addActionListener(this);
		clearButton.addActionListener(this);
		
		// 创建文本框  
		unameTextField = new JTextField(15);
		
		// 创建密码框  
		pwdTextField = new JPasswordField(15);
		
		//将三个输入框改为透明
		unameTextField.setOpaque(false);
		pwdTextField.setOpaque(false);
		
		// 设置布局管理
		this.setLayout(new GridLayout(4, 1));// 网格式布局

		// 加入各个组件
		loginTitle.add(titleLabel);

		unamePanel.add(unameLabel);
		unamePanel.add(unameTextField);

		pwdPanel.add(pwdLabel);
		pwdPanel.add(pwdTextField);
		
		buttonPanel.add(loginButton);
		buttonPanel.add(clearButton);

		// 加入到JFrame  
		this.add(loginTitle);
		this.add(unamePanel);
		this.add(pwdPanel);
		this.add(buttonPanel);

		// 设置窗体
		this.setTitle("学生基本信息管理系统");// 窗体标签
		this.setSize(400, 320);// 窗体大小
		this.setLocationRelativeTo(null);// 在屏幕中间显示
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 退出关闭JFrame
		this.setVisible(true);// 显示窗体
		this.addWindowListener(new WindowAdapter() {  
			        
			public void windowClosing(WindowEvent e) {  
		              
				//退出
			}
		});
		this.setResizable(false);// 锁定窗体 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String username = unameTextField.getText();
		String password = new String(pwdTextField.getPassword());
		
		if (e.getActionCommand() == "登录")
		{
			//校验信息
			if (username.equals("")) {
				JOptionPane.showMessageDialog(this, "需输入用户名", "提示消息", JOptionPane.ERROR_MESSAGE);
			} else if (password.equals("")) {
				JOptionPane.showMessageDialog(this, "需输入密码", "提示消息", JOptionPane.ERROR_MESSAGE);
			} else {				
				try {
					//开始登录
					userService.Login(username, password);
					this.dispose();
					new MainView();
				} catch (UserException ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage(), "提示消息", JOptionPane.ERROR_MESSAGE);
				}
			}
		} 
		else if (e.getActionCommand() == "重置")// 清空输入框
			clear();
	}
	
	//重置
	public void clear() {
		
		unameTextField.setText("");
		pwdTextField.setText("");
	}
	
	
}
