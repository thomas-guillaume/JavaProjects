<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="CSS/add-todo-style.css">
<link type="text/css" rel="stylesheet" href="CSS/style.css">
<title>Edit a Todo</title>
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
		<h3> Edit a Todo</h3>
		<form action="EditTodoServlet" method = "post">
			<table>
				<tbody>
					<tr>
						<td><label>Description : </label> </td>
						<td><input type="text" name = "description" value="${Todo.description }"/></td>
					</tr>
					<tr>
						<td><label></label> </td>
						<td><input type="submit" value = "Save"/></td>
					</tr>
				</tbody>
			</table>
		</form>
		<div style="clear:both;"></div>
		<a href="TodoControllerServlet">Back to List</a>
	</div>
</body>
</html>