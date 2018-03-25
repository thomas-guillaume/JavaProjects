package com.Thomas.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/DeleteTodoServlet")
public class DeleteTodoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TodoDBUtil todoDBUtil;
	
	
	@Resource(name="jdbc/tododb")
	private DataSource dataSource;
	
	
	int id;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		todoDBUtil = new TodoDBUtil(dataSource);
	}
	
	
	public DeleteTodoServlet() {
		super();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id=Integer.parseInt(request.getParameter("TodoId"));
		todoDBUtil.deleteTodo(id);
		doPost(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("TodoControllerServlet");
	}
}
