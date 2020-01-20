package cn.stuManagementSys.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.stuManagementSys.entity.Student;
import cn.stuManagementSys.service.StudentService;

public class ModifyView implements ItemListener {

	private JDialog modifyJD = null;
	
	private String oldSno; // 保存原学号
	private JLabel title = null;
	private JLabel sno = null;
	private JLabel sname = null;
	private JLabel ssex = null;

	private JRadioButton male = null;
	private JRadioButton female = null;
	private ButtonGroup group = null;

	private JLabel sdate = null;
	private JLabel sdepartment = null;
	private JLabel smajor = null;

	private JTextField snoT = null;
	private JTextField snameT = null;
	private JTextField smajort = null;

	private int STARTYEAR = 1990;
	private int ENDYEAR = 2010;

	private String[] department = { "农学院", "园艺园林学院", "生命科学学院", "经贸学院", "管理学院", "信息科学与技术学院", "自动化学院", "轻工食品学院", "机电工程学院", "化学化工学院",
			"环境科学与工程学院 " };

	// 年月日的选择框
	private JComboBox<String> departmentBox;
	private JComboBox<String> cboYear;
	private JComboBox<String> cboMonth;
	private JComboBox<String> cboDay;

	// 年月日标签
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;

	private JButton btok = null;
	
	private StudentService studentService = new StudentService();

	public ModifyView(Student student, JFrame mf) {
		oldSno = student.getSno();
		initialize(student, mf);
	}

	public void initialize(Student student, JFrame mf) {
		
		modifyJD = new JDialog();

		Font fontT = new Font("宋体", Font.BOLD + Font.ITALIC, 30);
		Font fontL = new Font("宋体", Font.BOLD + Font.ITALIC, 15);

		((JPanel) modifyJD.getContentPane()).setOpaque(false);
		ImageIcon img = new ImageIcon("src/images/BackGroup3.jpg");
		JLabel background = new JLabel(img);
		modifyJD.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());

		modifyJD.setModal(true);
		modifyJD.setLayout(null);
		
		title = new JLabel("基本信息");
		title.setBounds(320, 85, 130, 30);
		title.setFont(fontT);
		title.setForeground(Color.RED);
		modifyJD.add(title);

		sno = new JLabel("学号");
		sno.setBounds(220, 130, 50, 30);
		sno.setFont(fontL);
		snoT = new JTextField(student.getSno());
		snoT.setBounds(260, 130, 100, 30);
		modifyJD.add(snoT);
		modifyJD.add(sno);

		sname = new JLabel("姓名");
		sname.setBounds(380, 130, 50, 30);
		sname.setFont(fontL);
		snameT = new JTextField(student.getName());
		snameT.setBounds(420, 130, 100, 30);
		modifyJD.add(snameT);
		modifyJD.add(sname);

		ssex = new JLabel("性别");
		ssex.setBounds(220, 170, 50, 30);
		ssex.setFont(fontL);
		male = new JRadioButton("男", student.getSex().equals("男"));
		female = new JRadioButton("女", student.getSex().equals("女"));
		male.setOpaque(false);
		female.setOpaque(false);

		group = new ButtonGroup();
		group.add(male);
		group.add(female);
		male.setBounds(260, 170, 40, 30);
		female.setBounds(310, 170, 40, 30);
		modifyJD.add(male);
		modifyJD.add(female);
		modifyJD.add(ssex);

		sdate = new JLabel("出生日期");
		sdate.setBounds(220, 210, 90, 30);
		sdate.setFont(fontL);
		jLabel1 = new JLabel("年");
		jLabel2 = new JLabel("月");
		jLabel3 = new JLabel("日");
		cboYear = new JComboBox<String>();
		cboMonth = new JComboBox<String>();
		cboDay = new JComboBox<String>();
		AddInfo(student);

		modifyJD.add(sdate);
		modifyJD.add(cboYear);
		modifyJD.add(jLabel1);
		modifyJD.add(cboMonth);
		modifyJD.add(jLabel2);
		modifyJD.add(cboDay);
		modifyJD.add(jLabel3);
		cboYear.setBounds(290, 210, 60, 30);
		jLabel1.setBounds(350, 210, 20, 30);
		cboMonth.setBounds(370, 210, 50, 30);
		jLabel2.setBounds(420, 210, 20, 30);
		cboDay.setBounds(440, 210, 50, 30);
		jLabel3.setBounds(490, 210, 20, 30);
		cboMonth.addItemListener(this);

		departmentBox = new JComboBox<String>(department);
		sdepartment = new JLabel("ϵ��");
		sdepartment.setBounds(250, 250, 50, 30);
		sdepartment.setFont(fontL);
		departmentBox.setBounds(290, 250, 200, 30);
		modifyJD.add(sdepartment);
		modifyJD.add(departmentBox);
		for (int i = 0; i < department.length; i++) {
			if (student.getDepartment().equals(departmentBox.getItemAt(i))) {
				departmentBox.setSelectedIndex(i);
				break;
			}
		}

		smajor = new JLabel("专业");
		smajor.setBounds(250, 290, 50, 30);
		smajor.setFont(fontL);
		smajort = new JTextField(student.getMajor());
		smajort.setBounds(290, 290, 200, 30);
		modifyJD.add(smajor);
		modifyJD.add(smajort);

		btok = new JButton("确认修改");
		btok.setBounds(310, 340, 100, 30);
		btok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String year = (String) cboYear.getSelectedItem();
				String month = (String) cboMonth.getSelectedItem();
				String day = (String) cboDay.getSelectedItem();
				String date = year + "-" + month + "-" + day;
				
				String department = (String) departmentBox.getSelectedItem();
				
				String sex = null;
				if (male.isSelected()) {
					sex = "男";
				} else {
					sex = "女";
				}
				
				String sno = snoT.getText();
				String sname = snameT.getText();
				String smajor = smajort.getText();
				
				if(sno.equals("") || sno == null) {
					JOptionPane.showMessageDialog(mf, "学号不能为空", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				} else if(studentService.queryStudentBySno(sno) != null  & !(oldSno.equals(snoT.getText()))) {
					JOptionPane.showMessageDialog(mf, "学号已存在，请重新输入", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					Student student = new Student(sno, sname, sex, date, department, smajor);
					studentService.updateStudent(student, oldSno);
					JOptionPane.showMessageDialog(mf, "修改成功!", "提示消息", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		modifyJD.add(btok);

		modifyJD.setSize(749, 497);

		modifyJD.setLocationRelativeTo(mf);// 居中显示
		modifyJD.setVisible(true);
		modifyJD.setResizable(false);

	}

	void AddInfo(Student student) {
		String stuyear = (String) student.getBirthDate().subSequence(0, 4);
		String stumonth = (String) student.getBirthDate().substring(5, 7);
		String studay = (String) student.getBirthDate().substring(8, 10);
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
		// 日下拉选择框
		for (int i = 0; i < 31; i++) {
			if (i <= 8)
				cboDay.addItem("0" + (i + 1));
			else {
				cboDay.addItem("" + (i + 1));
			}
		}
		// 显示出当前学生的出生日期
		for (int i = 0; i < (ENDYEAR - STARTYEAR); i++) {
			if (stuyear.equals(cboYear.getItemAt(i))) {
				cboYear.setSelectedIndex(i);
				for (int j = 0; j < 12; j++) {
					if (stumonth.equals(cboMonth.getItemAt(j))) {
						cboMonth.setSelectedIndex(j);
						for (int j2 = 0; j2 < 31; j2++) {
							if (studay.equals(cboDay.getItemAt(j2))) {
								cboDay.setSelectedIndex(j2);
								break;
							}
						}
					}
				}
			}
		} 
	}

	public void itemStateChanged(ItemEvent arg0) {
		
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
				if (j <= 8) {
					cboDay.addItem("0" + (j + 1));
				} else {
					cboDay.addItem("" + (j + 1));
				}
			} 
		} 
	}
}
