package com.Thomas.jsf.jpa;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="student")
@Table(name="student")
public class StudentEntity {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id ;
	
	@NotNull @Size(min=2, max=20)
	private String first_name ;
	
	@NotNull @Size(min=2, max=20)
	private String last_name;
	
	private String email;


	public StudentEntity() {
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return first_name;
	}
	public void setFirstname(String firstname) {
		this.first_name = firstname;
	}

	public String getLastname() {
		return last_name;
	}
	public void setLastname(String lastname) {
		this.last_name = lastname;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
