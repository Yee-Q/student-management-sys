package cn.stuManagementSys.service;

import java.util.List;

import cn.stuManagementSys.dao.StudentDao;
import cn.stuManagementSys.entity.Student;

public class StudentService {

	private StudentDao studentDao = new StudentDao();
	
	/**
	 * 根据条件返回符合条件的学生二维数组
	 * @param student
	 * @return
	 */
	public List<Student> queryStuListByFilter(Student student) {

		return studentDao.queryStuListByFilter(student);

	}

	/**
	 * 查询全部学生的信息
	 * @param student
	 * @return
	 */
	public List<Student> queryAllStudent() {
		
		return studentDao.queryAllStudents();
	}
	
	/**
	 * 根据学号删除学生信息 
	 * @param sno
	 */
	public void deleteStudentBySno(String sno) {
		
		studentDao.deleteStudentBySno(sno);
	}
	
	/**
	 * 根据学号查询相应学生信息
	 * @param sno
	 * @return
	 */
	public Student queryStudentBySno(String sno) {
		
		return studentDao.queryStudentBySno(sno);
	}
	
	/**
	 * 插入学生信息
	 * @param student
	 */
	public void insertStudent(Student student) {
		
		studentDao.insertStudent(student);
	}
	
	/**
	 * 删除查询出来的全部学生信息
	 * @param stuList
	 */
	public void deleteStudentsByList(List<Student> stuList) {
		
		studentDao.deleteStudentsByList(stuList);
	}
	
	/**
	 * 修改学生信息
	 * @param student
	 */
	public void updateStudent(Student student, String oldSno) {
		
		studentDao.updateStudent(student, oldSno);
	}

}
