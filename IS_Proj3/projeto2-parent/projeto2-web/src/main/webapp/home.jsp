<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	
	<!-- CSS -->
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/style.css" rel="stylesheet">
	<body>
		<jsp:include page="header.jsp" />
		
		<div class="container">
			I'm home!
			<%
				String name = (String) session.getAttribute("name");
				out.println(name);
			%>
			
		</div>
		
		<script src="resources/js/jquery-3.2.1.min.js"></script>
    		<script src="resources/js/bootstrap.min.js"></script>
    		
	</body>
</html>