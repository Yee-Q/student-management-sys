package cn.stuManagementSys.entity;

public class Student {
	
	private String sno;
	private String name;
	private String sex;
	private String birthDate;
	private String department;
	private String major;
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public Student(String sno, String name, String sex, String birthDate, String department, String major) {
		super();
		this.sno = sno;
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.department = department;
		this.major = major;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
