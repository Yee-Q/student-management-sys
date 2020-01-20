package cn.stuManagementSys.view;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;

import cn.stuManagementSys.entity.Student;
import cn.stuManagementSys.service.StudentService;

public class SearchView extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	private JSplitPane jSplitPane = null;
	private JPanel jPanel1 = null;
	private DefaultTableModel tableModel = null;
	private JScrollPane jScrollPane = null;
	private JTable tblAddress = null;
	private JPopupMenu popupMenu = null;

	private JLabel labSex = null;
	private JLabel labSno = null;
	private JLabel labName = null;
	private JLabel labBirthDate = null;
	private JLabel labDepart = null;
	private JLabel labMajor = null;

	private JRadioButton male = null;
	private JRadioButton female = null;
	private ButtonGroup group = null;

	private JTextField txtSno = null;
	private JTextField txtName = null;
	private JTextField txtDate = null;
	private JTextField txtDepartment = null;
	private JTextField txtMajor = null;

	private JButton btnSearch = null;
	private JButton btnClear = null;
	private JButton btnDelete = null;
	private JButton btnRefresh = null;

	private String[] tableTitles = { "学号", "姓名", "性别", "出生日期", "系别", "专业" };
	private int rowindex;

	private StudentService studentService = new StudentService();

	private List<Student> stuList = null;

	private JFrame mFrame;

	/**
	 * 构造方法
	 * 
	 * @throws SQLException
	 */
	public SearchView(JButton tbSearch, JFrame mFrame) throws SQLException {
		initialize(tbSearch);
	}

	/**
	 * 初始化方法
	 * 
	 * @throws SQLException
	 */
	private void initialize(JButton tbSearch) throws SQLException {

		this.setBounds(new Rectangle(0, 0, 700, 500));
		this.setClosable(true);
		this.setTitle("查询");
		this.setVisible(true);
		this.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosed(InternalFrameEvent e) {
				tbSearch.setEnabled(true);
			}
		});
		stuList = studentService.queryAllStudent();
		this.setContentPane(getJContentPane());
		((JPanel) (this.getJContentPane())).setOpaque(false);

		/**
		 * 加载背景
		 * 
		 * @param mainf
		 */
		ImageIcon imgBg = new ImageIcon("src/images/BackGroup8.png");
		JLabel imgLabel = new JLabel(imgBg);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, imgBg.getIconWidth(), imgBg.getIconHeight());
	}

	/**
	 * 获取容器
	 * 
	 * @return
	 * @throws SQLException
	 */
	private JPanel getJContentPane() throws SQLException {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setOpaque(false);
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJSplitPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * 获取分割面板
	 * 
	 * @return
	 * @throws SQLException
	 */
	private JSplitPane getJSplitPane() throws SQLException {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setOpaque(false);
			jSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);// 设置方向
			jSplitPane.setDividerSize(2);// 设置分隔符的大小
			jSplitPane.setDividerLocation(260);// 设置分隔位置
			jSplitPane.setTopComponent(getJScrollPane());// 设置顶部窗格
			jSplitPane.setBottomComponent(getJPanel1());
			jSplitPane.setResizeWeight(1.0D);// 顶部窗格获得所有额外空间
		}
		return jSplitPane;
	}

	/**
	 * 获取底部窗格
	 * 
	 * @return
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			labSno = new JLabel("学号");
			labSno.setBounds(new Rectangle(20, 17, 30, 20));
			labName = new JLabel("姓名");
			labName.setBounds(new Rectangle(290, 19, 30, 20));

			labSex = new JLabel("性别");
			labSex.setBounds(550, 20, 30, 20);
			male = new JRadioButton("男");
			female = new JRadioButton("女");
			male.setContentAreaFilled(false);
			female.setContentAreaFilled(false);
			group = new ButtonGroup();
			group.add(male);
			group.add(female);
			male.setBounds(600, 16, 40, 30);
			female.setBounds(640, 16, 40, 30);

			labBirthDate = new JLabel("出生日期");
			labBirthDate.setBounds(new Rectangle(20, 60, 80, 20));
			labDepart = new JLabel("系别");
			labDepart.setBounds(new Rectangle(20, 103, 60, 20));
			labMajor = new JLabel("专业");
			labMajor.setBounds(new Rectangle(20, 146, 60, 20));

			jPanel1 = new JPanel();

			jPanel1.setOpaque(false);
			jPanel1.setLayout(null);

			jPanel1.add(labSno, null);
			jPanel1.add(labName, null);

			jPanel1.add(labSex);
			jPanel1.add(male);
			jPanel1.add(female);

			jPanel1.add(labBirthDate, null);
			jPanel1.add(labDepart, null);
			jPanel1.add(labMajor, null);

			jPanel1.add(getTxtSno(), null);
			jPanel1.add(getTxtName(), null);
			jPanel1.add(getTxtDate(), null);
			jPanel1.add(getTxtDepartment(), null);
			jPanel1.add(getTxtMajor(), null);
			jPanel1.add(getBtnSearch(), null);
			jPanel1.add(getBtnClear(), null);
			jPanel1.add(getBtnDelete(), null);
			jPanel1.add(getBtnRefresh(), null);
		}
		return jPanel1;
	}

	private JTextField getTxtSno() {
		if (txtSno == null) {
			txtSno = new JTextField();
			txtSno.setBounds(new Rectangle(80, 17, 160, 23));
		}
		return txtSno;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setBounds(new Rectangle(340, 17, 160, 23));
		}
		return txtName;
	}

	private JTextField getTxtDate() {
		if (txtDate == null) {
			txtDate = new JTextField();
			txtDate.setBounds(new Rectangle(80, 60, 160, 23));
		}
		return txtDate;
	}

	private JTextField getTxtDepartment() {
		if (txtDepartment == null) {
			txtDepartment = new JTextField();
			txtDepartment.setBounds(new Rectangle(80, 103, 160, 23));
		}
		return txtDepartment;
	}

	private JTextField getTxtMajor() {
		if (txtMajor == null) {
			txtMajor = new JTextField();
			txtMajor.setBounds(new Rectangle(80, 146, 160, 22));
		}
		return txtMajor;
	}

	private JButton getBtnSearch() {
		btnSearch = new JButton("确定");
		btnSearch.setBounds(new Rectangle(300, 70, 90, 90));
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String sex = null;
				if (male.isSelected()) {
					sex = "男";
				} else if (female.isSelected()) {
					sex = "女";
				} else {
					sex = "";
				}
				if ((txtSno.getText().equals("") | txtSno.getText() == null)
						&& (txtName.getText().equals("") | txtName.getText() == null) && (sex.equals("") | sex == null)
						&& (txtDate.getText().equals("") | txtDate.getText() == null)
						&& (txtDepartment.getText().equals("") | txtDepartment.getText() == null)
						&& (txtMajor.getText().equals("") | txtMajor.getText() == null)) {
					JOptionPane.showMessageDialog(null, "请先输入数据!", "提示消息", JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						// 得到数据
						Student student = new Student();
						student.setSno(txtSno.getText());
						student.setName(txtName.getText());
						student.setSex(sex);
						student.setBirthDate(txtDate.getText());
						student.setDepartment(txtDepartment.getText());
						student.setMajor(txtMajor.getText());
						stuList = studentService.queryStuListByFilter(student);

						tblAddress.setModel(gettableModel());
						tblAddress.updateUI();
					} catch (SQLException e1) {
						throw new RuntimeException(e1);
					}
				}
			}
		});
		return btnSearch;
	}

	private JButton getBtnClear() {
		btnClear = new JButton("重置");
		btnClear.setBounds(new Rectangle(420, 70, 90, 90));
		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				txtSno.setText("");
				txtName.setText("");
				group.clearSelection();
				txtDate.setText("");
				txtDepartment.setText("");
				txtMajor.setText("");
			}
		});
		return btnClear;
	}

	private JButton getBtnDelete() {
		btnDelete = new JButton("全部删除");
		btnDelete.setBounds(new Rectangle(540, 70, 90, 90));

		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 全部删除
				if (stuList != null) {
					int n = JOptionPane.showConfirmDialog(null, "确认是否删除?", "确认对话框", JOptionPane.YES_NO_OPTION);
					if (n == JOptionPane.YES_OPTION) {
						studentService.deleteStudentsByList(stuList);
						stuList.clear();
						try {
							tblAddress.setModel(gettableModel());
							tblAddress.updateUI();
						} catch (SQLException e1) {
							throw new RuntimeException(e1);
						}
					}
				}
			}
		});
		return btnDelete;
	}

	private JButton getBtnRefresh() {
		btnRefresh = new JButton("刷新");
		btnRefresh.setBounds(new Rectangle(660, 70, 90, 90));
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 刷新
				try {
					stuList = studentService.queryAllStudent();
					tblAddress.setModel(gettableModel());
					tblAddress.updateUI();
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}
			}
		});
		return btnRefresh;
	}

	private JScrollPane getJScrollPane() throws SQLException {

		jScrollPane = new JScrollPane();
		jScrollPane.setOpaque(false);
		jScrollPane.setViewportView(getTblAddress());

		return jScrollPane;
	}

	private JTable getTblAddress() throws SQLException {
		tblAddress = new JTable(gettableModel()) {

			private static final long serialVersionUID = 1L;

			// 使表格单元格不具备编辑功能
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tblAddress.setOpaque(false);
		popupMenu = new JPopupMenu();
		JMenuItem delMenItem = new JMenuItem();
		JMenuItem updMenItem = new JMenuItem();
		updMenItem.setText("修改");
		updMenItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 修改
				Student student = new Student();
				student.setSno((String) (tblAddress.getValueAt(rowindex, 0)));
				student.setName((String) (tblAddress.getValueAt(rowindex, 1)));
				student.setSex((String) (tblAddress.getValueAt(rowindex, 2)));
				student.setBirthDate((String) (tblAddress.getValueAt(rowindex, 3)));
				student.setDepartment((String) (tblAddress.getValueAt(rowindex, 4)));
				student.setMajor((String) (tblAddress.getValueAt(rowindex, 5)));
				new ModifyView(student, mFrame);
			}
		});

		delMenItem.setText("删除");
		delMenItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 删除并刷新
				int n = JOptionPane.showConfirmDialog(null, "确认是否删除?", "确认对话框", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					String sno = ((String) (tblAddress.getValueAt(rowindex, 0)));
					studentService.deleteStudentBySno(sno);
					for (int i = 0; i < stuList.size(); i++) {
						if (stuList.get(i).getSno().equals(sno)) {
							stuList.remove(i);
							break;
						}
					}
				} else if (n == JOptionPane.NO_OPTION) {
					return;
				}
				try {
					tblAddress.setModel(gettableModel());
					tblAddress.updateUI();
				} catch (SQLException e1) {
					throw new RuntimeException(e1);
				}
			}
		});

		popupMenu.add(updMenItem);
		popupMenu.add(delMenItem);

		tblAddress.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAddress.setEnabled(true);
		tblAddress.setToolTipText("右键选择修改或删除");
		tblAddress.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				rowindex = tblAddress.rowAtPoint(e.getPoint());
				if (e.getButton() == MouseEvent.BUTTON3) {
					tblAddress.setRowSelectionInterval(rowindex, rowindex);
					popupMenu.show(tblAddress, e.getX(), e.getY());
				}
			}

		});
		return tblAddress;
	}

	private DefaultTableModel gettableModel() throws SQLException {

		tableModel = new DefaultTableModel(getObjectsFromStuList(stuList), tableTitles);

		return tableModel;
	}

	/**
	 * 将学生信息链表转化为二维数组
	 * 
	 * @param stuList
	 * @return
	 */
	public Object[][] getObjectsFromStuList(List<Student> stuList) {

		Object[][] stuDyadicArray = new Object[stuList.size()][6];

		for (int i = 0; i < stuDyadicArray.length; i++) {
			for (int j = 0; j < 6; j++) {
				switch (j) {
				case 0:
					stuDyadicArray[i][j] = stuList.get(i).getSno();
					break;
				case 1:
					stuDyadicArray[i][j] = stuList.get(i).getName();
					break;
				case 2:
					stuDyadicArray[i][j] = stuList.get(i).getSex();
					break;
				case 3:
					stuDyadicArray[i][j] = stuList.get(i).getBirthDate();
					break;
				case 4:
					stuDyadicArray[i][j] = stuList.get(i).getDepartment();
					break;
				case 5:
					stuDyadicArray[i][j] = stuList.get(i).getMajor();
					break;
				}
			}
		}
		return stuDyadicArray;
	}
}
