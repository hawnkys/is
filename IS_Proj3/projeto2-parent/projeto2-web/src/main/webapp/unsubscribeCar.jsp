<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
	</head>
	
	<body>
		<jsp:include page="header.jsp" />
			<div class="container">
				<div id="form-group col-md-12">
					<h1>Unsubscribe car</h1><br>
					<form action="http://localhost:8081/unfollow">
						<input id="email" class="form-control" name="email" value="${email}" type="hidden">
						<input id="brand" class="form-control" type="text" name="brand" placeholder="Brand" required="required">
						<input type="submit" class="button" value="Unsubscribe Car">
					</form>
				</div>
			</div>
	</body>
	
</html>