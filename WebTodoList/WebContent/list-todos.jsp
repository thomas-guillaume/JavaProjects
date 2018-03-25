<%@ page import="java.util.*,com.Thomas.web.jdbc.*" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="CSS/style.css">
<title>Web ToDo List</title>
</head>
<body>
<div id="wrapper">
	<form action="LoginServlet" method="get">
		<c:if test="${!empty sessionScope.username}">
			<p><input type="submit" value="Logout"/> Welcome ${sessionScope.username} !</p>
		</c:if>
	</form>
	<br>
	<div id="header">
		<h2>ESILV Engineer School</h2>
	</div>
</div>
<div id="container">
	<div id="content">
		<form action="AddTodoServlet" method="get">
			<input type="submit" value="Add Todo"/>
		</form>
		<br>
		<table>
			<tr>
				<th>Description </th>
				<th>Action </th>
			</tr>
			<c:forEach var="tempTodo" items="${TODO_LIST }">
				<c:url var="EditLink" value= "EditTodoServlet">
					<c:param name="TodoId" value="${tempTodo.id}"/>
				</c:url>
				<c:url var="DeleteLink" value= "DeleteTodoServlet">
					<c:param name="TodoId" value="${tempTodo.id}"/>
				</c:url>
				<tr>
					<td> ${tempTodo.description}</td>
					<td> <a href="${EditLink }">Edit</a><span>|</span><a href="${DeleteLink }">Delete</a></td>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>