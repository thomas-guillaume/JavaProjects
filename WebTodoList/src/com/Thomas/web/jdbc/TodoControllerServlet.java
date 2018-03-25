package com.Thomas.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TodoControllerServlet")
public class TodoControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TodoDBUtil todoDBUtil;
	
	
	@Resource(name="jdbc/tododb")
	private DataSource datasource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		todoDBUtil = new TodoDBUtil(datasource);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			listTodos(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String d= req.getParameter("description");
		Todo todo = new Todo(d);
		todoDBUtil.addTodo(todo);
		try {
			listTodos(req,resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void listTodos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Todo> todos = todoDBUtil.getTodos();
		request.setAttribute("TODO_LIST", todos);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-todos.jsp");
		dispatcher.forward(request, response);
	}
}
