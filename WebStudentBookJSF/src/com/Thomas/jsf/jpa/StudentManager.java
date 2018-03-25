package com.Thomas.jsf.jpa;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

@ManagedBean //register the class student as JSF resource
@SessionScoped

public class StudentManager {
	
	private List<Student> students;
	
	
	public StudentManager()
	{
		this.students = new ArrayList<Student>();
	}

	
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> Students) {
		this.students = Students;
	}
	
	
	public void loadStudents() throws NamingException, SQLException
	{
		StudentDbUtil studentDbUtil = new StudentDbUtil();
		this.students = studentDbUtil.getStudents();
	}
	
	public String addStudent(Student stu) throws NamingException
	{
		StudentDbUtil studentDbUtil = new StudentDbUtil();
		studentDbUtil.addStudent(stu);
		return "list-student";
	}
	
	public String loadStudent(int id) throws NamingException
	{
		StudentDbUtil studentDbUtil = new StudentDbUtil();
		StudentEntity theStudent = studentDbUtil.loadStudent(id);
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		requestMap.put("student", theStudent);
		return "edit-student";
	}
	
	public String updateStudent(Student stu) throws NamingException
	{
		StudentDbUtil studentDbUtil = new StudentDbUtil();
		studentDbUtil.updateStudent(stu);
		return "list-student";
	}
	
	public String deleteStudent(int id) throws Exception
	{
		StudentDbUtil studentDbUtil = new StudentDbUtil();
		studentDbUtil.deleteStudent(id);
		return "list-student";
	}
}
