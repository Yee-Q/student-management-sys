package cn.stuManagementSys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.stuManagementSys.entity.Student;
import cn.stuManagementSys.utils.JdbcUtil;

public class StudentDao {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	/**
	 * 根据条件返回符合条件的学生信息
	 * 
	 * @param student
	 * @return
	 */
	public List<Student> queryStuListByFilter(Student student) {

		List<Student> stuList = new ArrayList<Student>();

		String sql = "SELECT * FROM students WHERE 1<2";

		try {
			con = JdbcUtil.getConnection();

			if (student.getSno() != null && !student.getSno().equals(""))
				sql += " AND sno=?";
			if (student.getName() != null && !student.getName().equals(""))
				sql += " and name=?";
			if (student.getSex() != null && !student.getSex().equals(""))
				sql += " and sex=?";
			if (student.getBirthDate() != null && !student.getBirthDate().equals(""))
				sql += " and birthDate=?";
			if (student.getDepartment() != null && !student.getDepartment().equals(""))
				sql += " and department=?";
			if (student.getMajor() != null && !student.getMajor().equals(""))
				sql += " and major=?";
			pstmt = con.prepareStatement(sql);
			int index = 0;

			if (student.getSno() != null && !student.getSno().equals(""))
				pstmt.setString(++index, student.getSno());
			if (student.getName() != null && !student.getName().equals(""))
				pstmt.setString(++index, student.getName());
			if (student.getSex() != null && !student.getSex().equals(""))
				pstmt.setString(++index, student.getSex());
			if (student.getBirthDate() != null && !student.getBirthDate().equals(""))
				pstmt.setString(++index, student.getBirthDate());
			if (student.getDepartment() != null && !student.getDepartment().equals(""))
				pstmt.setString(++index, student.getDepartment());
			if (student.getMajor() != null && !student.getMajor().equals(""))
				pstmt.setString(++index, student.getMajor());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Student stu = new Student();
				stu.setSno(rs.getString(1));
				stu.setName(rs.getString(2));
				stu.setSex(rs.getString(3));
				stu.setBirthDate(rs.getString(4));
				stu.setDepartment(rs.getString(5));
				stu.setMajor(rs.getString(6));
				stuList.add(stu);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		return stuList;
	}

	/**
	 * 查询全部学生信息
	 * 
	 * @return
	 */
	public List<Student> queryAllStudents() {

		List<Student> stuList = new ArrayList<Student>();

		String sql = "SELECT * FROM students";

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				stuList.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6)));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return stuList;
	}

	/**
	 * 根据学号删除学生信息
	 * 
	 * @param sno
	 */
	public void deleteStudentBySno(String sno) {

		String sql = "DELETE FROM students WHERE sno=?";

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, sno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 根据学号查询学生信息
	 * 
	 * @param sno
	 * @return
	 */
	public Student queryStudentBySno(String sno) {

		String sql = "SELECT * FROM students WHERE sno=?";
		Student stu = null;

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, sno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stu = new Student();
				stu.setSno(rs.getString(1));
				stu.setName(rs.getString(2));
				stu.setSex(rs.getString(3));
				stu.setBirthDate(rs.getString(4));
				stu.setDepartment(rs.getString(5));
				stu.setMajor(rs.getString(6));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		return stu;
	}

	/**
	 * 插入学生信息
	 * 
	 * @param student
	 */
	public void insertStudent(Student student) {

		String sql = "INSERT INTO students VALUES(?,?,?,?,?,?)";

		try {
			con = JdbcUtil.getConnection();

			String sno = student.getSno();
			String sname = student.getName();
			String ssex = student.getSex();
			String sbirthday = student.getBirthDate();
			String stie = student.getDepartment();
			String smajor = student.getMajor();

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sno);
			pstmt.setString(2, sname);
			pstmt.setString(3, ssex);
			pstmt.setString(4, sbirthday);
			pstmt.setString(5, stie);
			pstmt.setString(6, smajor);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 删除查询出来的全部学生信息
	 * 
	 * @param stuList
	 */
	public void deleteStudentsByList(List<Student> stuList) {

		String sql = "DELETE FROM students WHERE sno=?";

		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < stuList.size(); i++) {
				pstmt.setString(1, stuList.get(i).getSno());
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 修改学生信息
	 * 
	 * @param student
	 */
	public void updateStudent(Student student, String oldSno) {

		try {
			con = JdbcUtil.getConnection();

			String sql = "UPDATE students SET sno=?, name=?, sex=?, birthDate=?, department=?, major=? where sno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getSno());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getSex());
			pstmt.setString(4, student.getBirthDate());
			pstmt.setString(5, student.getDepartment());
			pstmt.setString(6, student.getMajor());
			pstmt.setString(7, oldSno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
