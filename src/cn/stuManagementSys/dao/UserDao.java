package cn.stuManagementSys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.stuManagementSys.entity.User;
import cn.stuManagementSys.utils.JdbcUtil;

public class UserDao {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 根据用户名查找用户
	 * @return
	 * @throws SQLException
	 */
	public User searchUserByUsername(String username) {

		String sql = "SELECT * FROM users WHERE username=?";

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getString(1), rs.getString(2));
			}

		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
		return null;
	}

	/**
	 * 新增用户
	 * @param user
	 */
	public void createUser(User user) {

		String sql = "INSERT INTO users VALUES(?,?)";

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

	/**
	 * 删除指定用户
	 * @param username
	 */
	public void deleteUserByUsername(String username) {

		String sql = "DELETE FROM users WHERE username=?";

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}

	/**
	 * 修改密码
	 * @param newPw
	 * @param username
	 */
	public void updatePwByUsername(String newPw, String username) {

		String sql = "UPDATE users SET password=? WHERE username=?";

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPw);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException();
			}
		}
	}
}
