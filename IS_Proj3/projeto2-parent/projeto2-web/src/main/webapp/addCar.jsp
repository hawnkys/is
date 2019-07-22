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
					<h1>Add New Car</h1><br>
					<form action="./SetCar">
						<input class="form-control" type="text" name="brand" placeholder="Brand" required="required">
						<input class="form-control" type="text" name="model" placeholder="Model" required="required">
						<input class="form-control" type="text" name="cylinderCapacity" placeholder="Cylinder Capacity" required="required">
						<input class="form-control" type="text" name="distance" placeholder="Distance" required="required">
						<input class="form-control" type="text" name="fuel" placeholder="Fuel" required="required">
						<input class="form-control" type="text" name="power" placeholder="Power" required="required">
						<input class="form-control" type="text" name="price" placeholder="Price" required="required">
						<input class="form-control" type="text" name="registerDate" placeholder="Register Date" required="required">
						<input class="form-control" type="text" name="imageURL" placeholder="Image URL" required="required">
						<input type="submit" name="action" class="button" value="Add Car">
					</form>
							
				</div>
			</div>
			
    		
	</body>
	
</html>