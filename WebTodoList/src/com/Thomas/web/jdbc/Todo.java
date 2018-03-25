package com.Thomas.web.jdbc;

public class Todo {
	
	// attributes
	private int id;
	private String description;
	
	
	// getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	// constructors
	public Todo(int id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public Todo(String description) {
		super();
		this.description = description;
	}
	
	
	// tostring method
	@Override
	public String toString() {
		return "Todo " + id + " : " + description;
	}	
}
