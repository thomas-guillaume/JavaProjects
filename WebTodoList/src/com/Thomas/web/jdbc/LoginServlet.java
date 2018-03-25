package com.Thomas.web.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private Cookie [] cookies;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (session != null) {
			session.invalidate();
			session = null;
		}
		
		//Cookie
		cookies = request.getCookies();
		if(cookies!= null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("username"))
					request.setAttribute("username", cookie.getValue()) ;
			}
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("username");
		String password= request.getParameter("password");
		
		//Cookie
		Cookie cookie = new Cookie("username", username);
		cookie.setMaxAge(60*60*24) ; // in seconds, here for 24 hours
		response.addCookie(cookie);
		
		//Session
		session = request.getSession();
		session.setAttribute("username", username);
		
		
		int status;
		
		
		//verification in the "database"
		status = identification(username, password);
		
		if (status==1) {
			response.sendRedirect("TodoControllerServlet");
		}
		else {
			if (status==2) {
				response.sendRedirect("TodoControllerServletForStudents");
			}
			else {
				request.setAttribute("Error", "Invalid Username and/or Password. Please reenter.");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}
	}
	
	private int identification(String username, String password) {
		int status;
		
		// verification of the username and the password and give a status according to the role
		if (username.equals("Nada") && password.equals("1111")) {
			status=1;
		}
		else {
			if (username.equals("Thomas") && password.equals("0000")) {
				status=2;
			}
			else {
				status=0;
			}
		}
		return status;
	}
}
