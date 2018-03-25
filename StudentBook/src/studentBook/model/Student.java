package studentBook.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import studentBook.controller.DBManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
	
	private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty groupId;
    private final StringProperty birthday;
    private final StringProperty gender;
    private final IntegerProperty mark;
    private final StringProperty comment;
    
    // Default constructor.
    public Student() {
        this(null, null);
    }
    
    // Constructor with some initial data.
    public Student(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.groupId = new SimpleStringProperty("");
        this.birthday = new SimpleStringProperty("");
        this.gender = new SimpleStringProperty("");
        this.mark = new SimpleIntegerProperty(0);
        this.comment = new SimpleStringProperty("-");
    }
	
	public String getFirstName() {
		return firstName.get();
	}
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public StringProperty firstNameProperty() {
        return firstName;
    }
	public String getLastName() {
		return lastName.get();
	}
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public StringProperty lastNameProperty() {
        return lastName;
    }
	public String getGroupID() {
		return groupId.get();
	}
	public void setGroupID(String groupId) {
		this.groupId.set(groupId);
	}
	public String getBirthday() {
		return birthday.get();
	}
	public void setBirthday(String birthday) {
		this.birthday.set(birthday);
	}
	public String getGender() {
		return gender.get();
	}
	public void setGender(String gender) {
		this.gender.set(gender);
	}
	public Integer getMark() {
		return mark.get();
	}
	public void setMark(Integer mark) {
		this.mark.set(mark);
	}
	public String getComment() {
		return comment.get();
	}
	public void setComment(String comment) {
		this.comment.set(comment);
	}
	
	
	public void saveStudent(){
		Connection conn = DBManager.Connector();
		if (conn==null) System.exit(1);
		else {
			PreparedStatement pst;
			String insertString = "INSERT INTO Student (FirstName, LastName, GroupID, Birthday, Gender, Mark, Comment) VALUES (?, ?, ?, ?, ?, ?, ?)";
			try {
				pst = conn.prepareStatement(insertString);
				StringProperty firstName = this.firstName;
				StringProperty lastName = this.lastName;
				StringProperty groupId = this.groupId;
				StringProperty birthday = this.birthday;
				StringProperty gender = this.gender;
				IntegerProperty mark = this.mark;
				StringProperty comment = this.comment;
				pst.setString(1, firstName.getValue());
				pst.setString(2, lastName.getValue());
				pst.setString(3, groupId.getValue());
				pst.setString(4, birthday.getValue());
				pst.setString(5, gender.getValue());
				pst.setInt(6, mark.getValue());
				pst.setString(7, comment.getValue());
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}