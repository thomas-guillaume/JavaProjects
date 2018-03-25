package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Student {
	
	String name;
	String gender;
	
	public Student(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void saveStudent(){
		Connection conn = DBManager.Connector();
		if (conn==null) System.exit(1);
		else {
			PreparedStatement pst;
			String insertString = "INSERT INTO Student (Name, Gender) VALUES (?, ?)";
			try {
				pst = conn.prepareStatement(insertString);
				String name = this.name;
				String gender = this.gender;
				pst.setString(1, name);
				pst.setString(2, gender);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
