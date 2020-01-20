package cn.stuManagementSys.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	
		private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
		private static final String DBURL = "jdbc:mysql://localhost:3306/stumanagerdba?serverTimezone=UTC";
		private static final String DBUSER = "root";
		private static final String DBPASS = "123"; 
		
		public static Connection getConnection() {
			
			Connection conn = null;
			
			try { 
				Class.forName(DBDRIVER); 
				conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
				return conn;
			} catch (Exception e) { 
					e.printStackTrace();
					return null;
			} 
		}
	
}
