package com.Thomas.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TodoDBUtil {
	
	private DataSource dataSource;
	

	public TodoDBUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	
	public List<Todo> getTodos() throws Exception {
		List<Todo> todos= new ArrayList<Todo>();
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todolist order by description";
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
				int id = myRs.getInt("id");
				String description=myRs.getString("description");
				Todo tempTodo= new Todo(id, description);
				todos.add(tempTodo);
			}
			return todos;
		} finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	public void addTodo(Todo todo){
		Connection myConn=null;
		PreparedStatement myStmt = null;
		ResultSet myRs= null;
		try {
			myConn = dataSource.getConnection();
			String sql = "INSERT INTO Todolist (description) VALUES (?)";
			myStmt = myConn.prepareStatement(sql);
			String description = todo.getDescription();
			myStmt.setString(1, description);
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	public Todo fetchTodo(int id) {
		Connection myConn=null;
		Statement myStmt = null;
		ResultSet myRs= null;
		Todo todo=null;
		try {
			myConn = dataSource.getConnection();
			myStmt= myConn.createStatement();
			String sql= "select * from todolist where id="+id;
			myRs = myStmt.executeQuery(sql);
			while(myRs.next()){
				String description=myRs.getString("description");
				todo = new Todo(id,description);
			}
			return todo;
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		} finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	public void updateTodo(Todo todo) {
		Connection myConn=null;
		PreparedStatement myStmt = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "update todolist set description=? where id=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, todo.getDescription());
			myStmt.setInt(2,todo.getId());
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			close(myConn,myStmt,null);
		}
	}
	
	
	public void deleteTodo(int id){
		Connection myConn=null;
		PreparedStatement myStmt = null;
		ResultSet myRs= null;
		try {
			myConn = dataSource.getConnection();
			String sql = "DELETE FROM Todolist WHERE id=" + id;
			myStmt = myConn.prepareStatement(sql);
			myStmt.execute();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		finally{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	public void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
			if(myStmt!=null)
				myStmt.close();
			if(myRs!=null)
				myRs.close();
			if(myConn!=null)
				myConn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
