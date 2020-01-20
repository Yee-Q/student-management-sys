package cn.stuManagementSys.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.stuManagementSys.entity.Student;
import cn.stuManagementSys.service.StudentService;

public class InsertView extends JFrame implements ActionListener, ItemListener {

	private static final long serialVersionUID = 1L;

	private JPanel jptitle, jp1, jp2, jp3, jp4, jp5, jp6, jpbutton;
	private JLabel jltitle, labSno, labName, labSex, labBirthDate, labDepartment, labMajor;
	private JRadioButton rbMale;
	private JRadioButton rbfamale;
	private ButtonGroup bgSex;
	private JButton jbSearch, jbNotarize, jbClear;
	private JTextField jtSno, jtName, jtMajor;
	private int STARTYEAR = 1990;// 年份的开始值
	private int ENDYEAR = 2010;// 年份的结束值
	private String[] tie = { "农学院", "园艺园林学院", "生命科学学院", "经贸学院", "管理学院", "信息科学与技术学院", "自动化学院", "轻工食品学院", "机电工程学院",
			"化学化工学院", "环境科学与工程学院 " };

	// 年月日的选择框
	private JComboBox<String> tieBox = new JComboBox<String>(tie);
	private JComboBox<String> cboYear;
	private JComboBox<String> cboMonth;
	private JComboBox<String> cboDay;

	// 年月日标签
	private JLabel labYear;
	private JLabel labMonth;
	private JLabel labDay;

	private StudentService studentService = new StudentService();

	// 构造方法创建面板学号、姓名、性别、出生日期、专业
	public InsertView(JFrame mf) throws SQLException {

		// 创建面板
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		jptitle = new JPanel();
		jpbutton = new JPanel();

		jptitle.setOpaque(false);
		jp1.setOpaque(false);
		jp2.setOpaque(false);
		jp3.setOpaque(false);
		jp4.setOpaque(false);
		jp5.setOpaque(false);
		jp6.setOpaque(false);
		jpbutton.setOpaque(false);

		// 插入背景图
		((JPanel) this.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("src/images/BackGroup3.jpg");
		JLabel background = new JLabel(img);
		this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

		// 年月日的选择框
		cboYear = new JComboBox<String>();
		cboMonth = new JComboBox<String>();
		cboDay = new JComboBox<String>();

		// 年月日标签
		labYear = new JLabel("年");
		labMonth = new JLabel("月");
		labDay = new JLabel("日");

		// 创建标签
		jltitle = new JLabel("添加学生成员");
		Font fontT = new Font("宋体", Font.BOLD + Font.ITALIC, 30);
		Font fontL = new Font("宋体", Font.BOLD + Font.ITALIC, 15);
		jltitle.setFont(fontT);
		jltitle.setForeground(Color.red);
		labSno = new JLabel("学    号");
		labName = new JLabel("姓    名");
		labSex = new JLabel("性    别");
		labBirthDate = new JLabel("出生日期");
		labDepartment = new JLabel("系    别");
		labMajor = new JLabel("专    业");
		labSno.setFont(fontL);
		labName.setFont(fontL);
		labSex.setFont(fontL);
		labBirthDate.setFont(fontL);
		labDepartment.setFont(fontL);
		labMajor.setFont(fontL);

		// 创建文本框,单选框
		jtSno = new JTextField(11);
		jtName = new JTextField(11);
		rbMale = new JRadioButton("男");
		rbfamale = new JRadioButton("女");
		rbMale.setSelected(true);
		bgSex = new ButtonGroup();
		bgSex.add(rbMale);
		bgSex.add(rbfamale);
		jtMajor = new JTextField(11);
		rbfamale.setOpaque(false);
		rbMale.setOpaque(false);

		bgSex = new ButtonGroup();
		// 创建按钮
		jbSearch = new JButton("重命名检索");
		jbNotarize = new JButton("确认");
		jbClear = new JButton("重置");

		// 将各个按钮注册监听器
		jbSearch.addActionListener(this);
		jbNotarize.addActionListener(this);
		jbClear.addActionListener(this);
		cboMonth.addItemListener(this);

		// 将各组件依次加入面板
		jptitle.add(jltitle);// 标题
		jp1.add(labSno);
		jp2.add(labName);
		jp3.add(labSex);
		jp4.add(labBirthDate);
		jp5.add(labDepartment);
		jp6.add(labMajor);// 标签
		jp1.add(jtSno);
		jp2.add(jtName);
		jp3.add(rbMale);
		jp3.add(rbfamale);// 单选框
		jp4.add(cboYear);
		jp4.add(labYear);
		jp4.add(cboMonth);
		jp4.add(labMonth);
		jp4.add(cboDay);
		jp4.add(labDay);

		jp5.add(tieBox);
		jp6.add(jtMajor);// 文本框
		jp1.add(jbSearch);
		jpbutton.add(jbNotarize);
		jpbutton.add(jbClear);// 按钮

		// 将各个面板加入窗口
		this.add(jptitle);
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		this.add(jp6);
		this.add(jpbutton);

		jptitle.setBounds(250, 40, 200, 40);
		jp1.setBounds(250, 90, 350, 40);
		jp2.setBounds(250, 140, 250, 40);
		jp3.setBounds(250, 190, 200, 40);
		jp4.setBounds(250, 240, 320, 40);
		jp5.setBounds(250, 290, 270, 40);
		jp6.setBounds(250, 340, 250, 40);
		jpbutton.setBounds(250, 390, 250, 40);

		this.setLayout(null);// 绝对布局
		// 初始化日期选择框
		AddInfo();
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				// 添加事件:
				mf.setEnabled(true);// 将主界面再设置为可操作的
			}
		});
		// 设置窗体
		this.setTitle("添加学生信息");// 窗体标签
		this.setSize(749, 500);// 窗体大小
		this.setLocationRelativeTo(null);// 在屏幕中间显示(居中显示
		this.setVisible(true);// 显示窗体
		// 锁定窗体 
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "重命名检索") {

			if (jtSno.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "学号不可为空！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
			} else {
				// 重命名检索
				String sno = jtSno.getText();
				if (sno.equals("") || sno == null) {
					JOptionPane.showMessageDialog(this, "学号不可为空！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (studentService.queryStudentBySno(sno) == null)
						JOptionPane.showMessageDialog(this, "该学号可以使用", "提示消息", JOptionPane.INFORMATION_MESSAGE);
					else {
						JOptionPane.showMessageDialog(this, "该学号不可使用，请重新输入", "提示消息", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} else if (e.getActionCommand() == "确认") {

			if (jtSno.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "学号不可为空！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
			} else if (jtName.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "姓名不可为空！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
			} else if (jtMajor.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "专业名不可为空！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
			} else {
				// 插入
				String year = (String) cboYear.getSelectedItem();
				String month = (String) cboMonth.getSelectedItem();
				String day = (String) cboDay.getSelectedItem();
				Student student = new Student();
				student.setSno(jtSno.getText());
				student.setName(jtName.getText());
				if (rbMale.isSelected()) {
					student.setSex("男");
				} else {
					student.setSex("女");
				}
				student.setBirthDate(year + "-" + month + "-" + day);
				student.setDepartment((String) tieBox.getSelectedItem());
				student.setMajor(jtMajor.getText());
				
				if (studentService.queryStudentBySno(student.getSno()) == null) {
					studentService.insertStudent(student);
					JOptionPane.showMessageDialog(this, "插入成功！", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "插入失败，该学号已被注册", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} else if (e.getActionCommand() == "重置")

			jtSno.setText("");
			jtName.setText("");
			cboYear.setSelectedIndex(0);
			cboMonth.setSelectedIndex(0);
			cboDay.setSelectedIndex(0);
			tieBox.setSelectedIndex(0);
			jtMajor.setText("");
		}

	void AddInfo() {

		// 年下拉选择框
		for (int i = STARTYEAR; i < ENDYEAR; i++) {
			cboYear.addItem("" + i);
		}

		// 月下拉选择框
		for (int i = 0; i < 12; i++) {
			if (i <= 8)
				cboMonth.addItem("0" + (i + 1));
			else {
				cboMonth.addItem("" + (i + 1));
			}
		}
	}

	// 月份选择监听器
	@Override
	public void itemStateChanged(ItemEvent e) {

		Object obj = cboMonth.getSelectedItem();// 取得选中月份

		if (obj != null) {
			cboDay.removeAllItems();// 清空日的下拉列表框

			int month = Integer.valueOf(obj.toString());
			int days = 31;
			if (month == 4 || month == 6 || month == 9 || month == 11) {
				days = 30;
			} else if (month == 2) {
				// 取得选中年份
				int year = Integer.parseInt(cboYear.getSelectedItem().toString());
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					// 是闰年
					days = 29;
				} else {
					// 不是闰年
					days = 28;
				}
			}

			for (int j = 0; j < days; j++) {
				if (j <= 8)
					cboDay.addItem("0" + (j + 1));
				else {
					cboDay.addItem("" + (j + 1));
				}
			}
		}
	}
}