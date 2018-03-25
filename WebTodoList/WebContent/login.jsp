<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="CSS/style.css">
<title>Login Page</title>
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h2>ESILV Engineer School</h2>
	</div>
</div>
<div id="container">
	<div id="content">
		<form action="LoginServlet" method="post">
			<fieldset>
				<legend>Sign In</legend>
				<p>
        			<label>Username</label> : <input type="text" name="username" value=${username } />
        			<label>Password</label> : <input type="password" name="password" />
        			<br>
        			<br>
       				<span>${Error }</span>
    			</p>
    		</fieldset>
    		<br>
			<input type="submit" value="Login"/>
		</form>
	</div>
</div>
</body>
</html>