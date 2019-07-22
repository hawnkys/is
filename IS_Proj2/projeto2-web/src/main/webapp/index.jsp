<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	</head>
	
	<!-- CSS -->
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/style.css" rel="stylesheet">
	
	<body>
		<div class="loginmodal-container">
			<div id="login">
				<h1>Login to Your Account</h1><br>
				<form action="./Authentication">
				
					<input type="text" name="username" placeholder="Username" required="required">
					<input type="password" name="password" placeholder="Password" required="required">
					<input type="submit" name="action" class="login loginmodal-submit" value="login">
				</form>
						
				<div class="login-help">
					<a href="javascript:showRegister();">Register</a>
				</div>
				
				<c:if test="${error != null}">
					<div class="error">
						<c:out value = "${error}"/>
					</div>
				</c:if>
			</div>
			
			<div id="register">
				<h1>Register New Account</h1><br>
				<form action="./Authentication">
					<input type="text" name="name" placeholder="Name" required="required">
					<input type="text" name="email" placeholder="Email" required="required">
					<input type="text" name="username" placeholder="Username" required="required">
					<input type="password" name="password" placeholder="Password" required="required">
					<input type="submit" name="action" class="login loginmodal-submit" value="register">
				</form>
						
				<div class="login-help">
					<a href="javascript:showLogin()">Login</a>
				</div>
			</div>
			
		</div>
		
		<script src="resources/js/jquery-3.2.1.min.js"></script>
    		<script src="resources/js/bootstrap.min.js"></script>
    		
    		<script>
    		function showRegister(){
    			document.getElementById("login").style.display = "none";
    			document.getElementById("register").style.display = "block";
    		}
    		
    		function showLogin(){
    			document.getElementById("login").style.display = "block";
    			document.getElementById("register").style.display = "none";
    		}
    		
    		
    		</script>
	</body>
	
</html>