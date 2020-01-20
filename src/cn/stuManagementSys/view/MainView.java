package cn.stuManagementSys.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private ImageIcon imgBg = null;
	private JLabel imgLabel = null;
	
	private JPanel jContentPane = null;
	
	private JMenuBar jMenuBar = null;
	private JMenu menuFile = null;
	private JMenu menuHelp = null;
	private JMenuItem miPwd = null;
	private JMenuItem miExit = null;
	private JMenuItem miContent = null;
	private JMenuItem miAbout = null;
	private JMenuItem miUpdate = null;
	
	private JToolBar toolBar = null;
	
	private JButton tbAdd = null;
	private JButton tbSearch = null;
	private JButton tbNew = null;
	
	/**
	 * 构造方法
	 */
	public MainView() {
		super();
		initalize();
	}
	
	/**
	 * 初始化方法
	 */
	public void initalize() {
		
		loadBackgroup(this);
		((JPanel)(this.getContentPane())).setOpaque(false);
		
		this.setTitle("学生信息管理系统");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//居中显示
		this.setVisible(true);
		this.setResizable(false);
		
		this.setContentPane(getJContentPane());
		this.setJMenuBar(getJJMenuBar());	
	}
	
	/**
	 * 加载背景
	 * @param mainf
	 */
	public void loadBackgroup(JFrame mainf) {
		imgBg = new ImageIcon("src/images/BackGroup2.png");
		imgLabel = new JLabel(imgBg);
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0,0,imgBg.getIconWidth(),imgBg.getIconHeight());
	}
	
	/**
	 * 获取容器
	 * @return
	 */
	private JPanel getJContentPane() {
		if(jContentPane == null) {
			 jContentPane = new JPanel();
			 jContentPane.setOpaque(false);
			 jContentPane.setLayout(new BorderLayout());
			 jContentPane.add(getToolBar(), BorderLayout.NORTH);	//容器里获取工具栏
		}
		return jContentPane;
	}
	
	/**
	 * 初始化工具栏
	 * @return
	 */
	private JToolBar getToolBar() {
		if(toolBar == null) {
			toolBar = new JToolBar();
			toolBar.add(getTbAdd());
			toolBar.add(getTbSearch());
			toolBar.add(getTbNew());
		}
		return toolBar;
	}
	
	/**
	 * 初始化工具栏按钮
	 * @return
	 */
	private JButton getTbAdd() {
		if(tbAdd == null) {
			tbAdd = new JButton("新增");
			
			tbAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						showInsertView();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return tbAdd;
	}
	
	private JButton getTbSearch() {
		if(tbSearch == null) {
			tbSearch = new JButton("查看");
			
			tbSearch.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						tbSearch.setEnabled(false);
						showSearchView();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}					
				}
			});
		}
		return tbSearch;
	}
	
	private JButton getTbNew() {
		if(tbNew == null) {
			tbNew = new JButton("新建用户");
			
			tbNew.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showCreateView();
				}
			});
		}
		return tbNew;
	}
	
	/**
	 * 初始化菜单
	 */
	private JMenuBar getJJMenuBar() {
		if(jMenuBar == null) {
			jMenuBar = new JMenuBar();
			jMenuBar.add(getMenuFile());
			jMenuBar.add(getMenuHelp());
		}
		return jMenuBar;
	}
	
	/**
	 * 初始化菜单文件项
	 */
	private JMenu getMenuFile() {
		if(menuFile == null) {
			menuFile = new JMenu();
			menuFile.setText("文件");
			menuFile.add(getMiPwd());
			menuFile.addSeparator();
			menuFile.add(getMiExit());
		}
		return menuFile;
	}
	

	
	/**
	 * 初始化菜单帮助项
	 */
	private JMenu getMenuHelp() {
		if(menuHelp == null) {
			menuHelp = new JMenu();
			menuHelp.setText("帮助");
			menuHelp.add(getMiContent());
			menuHelp.addSeparator();
			menuHelp.add(getMiUpdate());
			menuHelp.addSeparator();
			menuHelp.add(getMiAbout());
		}
		return menuHelp;
	}
	
	/**
	 * 重置密码
	 */
	private JMenuItem getMiPwd() {
		if(miPwd == null) {
			miPwd = new JMenuItem("密码设置");
			
			miPwd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					showUpdatePWView();
				}
			});
		}
		return miPwd;
	}
	
	/**
	 * 退出
	 */
	private JMenuItem getMiExit() {
		if(miExit == null) {
			miExit = new JMenuItem("退出");
			
			miExit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					exit();
				}
			});
		}
		return miExit;
	}
	
	/**
	 * 使用手册
	 */
	private JMenuItem getMiContent() {
		if(miContent == null) {
			miContent = new JMenuItem("使用手册");
			
			miContent.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					showHelpView();
				}
			});
		}
		return miContent;
	}
	
	/**
	 * 升级
	 */
	private JMenuItem getMiUpdate() {
		if(miUpdate == null) {
			miUpdate = new JMenuItem("升级");
			
			miUpdate.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					showUpdateView();
				}
			});
		}
		return miUpdate;
	}
	
	/**
	 * 关于
	 */
	private JMenuItem getMiAbout() {
		if(miAbout == null) {
			miAbout = new JMenuItem("关于");
			
			miAbout.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					showAboutView();
				}
			});
		}
		return miAbout;
	}
	
	/**
	 * 显示查询窗口
	 * @throws SQLException 
	 */
	private void showSearchView() throws SQLException {
		
		jContentPane.add(getSearchView(tbSearch, this), BorderLayout.CENTER);
	}
	
	
	/**
	 * 获取查询页面
	 * @return
	 * @throws SQLException
	 */		
	public SearchView getSearchView(JButton tbSearch, JFrame mFrame) throws SQLException {	
		return new SearchView(tbSearch, mFrame);
	}
	
	/**
	 * 显示新增窗口
	 * @throws SQLException
	 */
	private void showInsertView() throws SQLException {
		this.setEnabled(false);
		new InsertView(this);
	}
		
	/**
	 * 显示新建用户窗口
	 */
	private void showCreateView() {
		new CreateView(this);
	}
	
	/**
	 * 返回登录界面
	 */
	private void exit() {
		this.dispose();
		new LoginView();
	}
	
	/**
	 * 显示帮助界面
	 */
	private void showHelpView() {
		new HelpView(this);
	}
	
	/**
	 * 显示升级界面
	 */
	private void showUpdateView() {
		new UpdateView(this);
	}
	
	/**
	 * 显示关于界面
	 */
	private void showAboutView() {
		new AboutView(this);
	}
	
	/**
	 * 显示修改密码界面
	 */
	private void showUpdatePWView() {
		new UpdatePwView(this);
	}
}
