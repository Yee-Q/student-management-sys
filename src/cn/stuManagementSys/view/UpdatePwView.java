package cn.stuManagementSys.view;

import java.awt.Container;
import java.awt.Font;
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

import cn.stuManagementSys.exception.UserException;
import cn.stuManagementSys.service.UserService;

public class UpdatePwView {

	private JDialog f = new JDialog();
	private JPanel p = new JPanel();
	private Container c = f.getContentPane();

	private JLabel titleJLabel = new JLabel("请修改您的密码");
	private JLabel acccountJLabel = new JLabel("账户:");
	private JLabel oldPwJLabel = new JLabel("旧密码:");
	private JLabel newPwJLabel = new JLabel("新密码:");
	private Font font = new Font("宋体", Font.BOLD + Font.ITALIC, 20);// 标题样式
	private JTextField accounTextField = new JTextField(15);
	private JPasswordField oldPwJPasswordField = new JPasswordField(15);
	private JPasswordField newPwJPasswordField = new JPasswordField(15);

	private JButton saveJButton = new JButton("保存");
	
	private UserService userService = new UserService();

	public UpdatePwView(JFrame mf) {
		titleJLabel.setFont(font);
		accounTextField.setOpaque(false);
		oldPwJPasswordField.setOpaque(false);
		newPwJPasswordField.setOpaque(false);
		p.setLayout(null);
		p.setOpaque(false);
		((JPanel) f.getContentPane()).setOpaque(false);

		// 调整组件位置
		titleJLabel.setBounds(20, 10, 150, 40);
		acccountJLabel.setBounds(110, 55, 110, 40);
		accounTextField.setBounds(160, 63, 120, 24);
		oldPwJLabel.setBounds(99, 85, 110, 40);
		oldPwJPasswordField.setBounds(160, 95, 120, 24);
		newPwJLabel.setBounds(99, 115, 110, 40);
		newPwJPasswordField.setBounds(160, 125, 120, 24);

		saveJButton.setBounds(150, 200, 100, 30);

		saveJButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				String username = accounTextField.getText();
				String oldPw = String.valueOf(oldPwJPasswordField.getPassword());
				String newPw = String.valueOf(newPwJPasswordField.getPassword());

				if (username.equals("") || username == null) {
					JOptionPane.showMessageDialog(null, "用户名不能为空");
				} else if (oldPw.equals("") || oldPw == null) {
					JOptionPane.showMessageDialog(null, "旧密码不能为空");
				} else if (newPw.equals("") || newPw == null) {
					JOptionPane.showMessageDialog(null, "新密码不能为空");
				} else {
					try {
						userService.UpdatePw(username, oldPw, newPw);
						JOptionPane.showMessageDialog(f, "修改成功", "提示消息", JOptionPane.INFORMATION_MESSAGE);
					} catch (UserException e1) {
						JOptionPane.showMessageDialog(f, e1.getMessage(), "提示消息", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		acccountJLabel.setFont(new Font("宋体", Font.BOLD, 15));
		oldPwJLabel.setFont(new Font("宋体", Font.BOLD, 15));
		newPwJLabel.setFont(new Font("宋体", Font.BOLD, 15));
		saveJButton.setFont(new Font("宋体", Font.BOLD, 15));

		p.add(titleJLabel);
		p.add(acccountJLabel);
		p.add(oldPwJLabel);
		p.add(newPwJLabel);
		p.add(accounTextField);
		p.add(oldPwJPasswordField);
		p.add(newPwJPasswordField);
		p.add(saveJButton);
		c.add(p);
		p.setOpaque(false);
		((JPanel) f.getContentPane()).setOpaque(false);

		// 插入背景图
		ImageIcon img = new ImageIcon("src/images/BackGroup1.png");
		JLabel background = new JLabel(img);
		f.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		
		f.setModal(true);
		f.setTitle("修改密码");
		f.setSize(400, 320);
		f.setLocationRelativeTo(mf);
		f.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}
}
