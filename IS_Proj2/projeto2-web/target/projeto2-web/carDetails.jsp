<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	
	<body>
		<jsp:include page="header.jsp" />
		
		<div class="container">
		

			<div class="col-md-4">
				<div class="row">
					<c:choose>
						<c:when test="${car.imageURL!=null}">
							<img src="<c:out value="${car.imageURL}"/>" width="100%">
						</c:when>
						<c:otherwise>
			            		<img src="resources/imgs/car.png" width="100%">
						</c:otherwise>
					</c:choose>
					<img src="<c:out value="${car.imageURL}"/>" width="100%">
				</div>
				<div class="row">
					FAV
				</div>
				
				<c:if test="${sessionScope.userId==car.user.id}">
					<div class="row">
						<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							        <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
							      </div>
							      <div class="modal-body">
							        <p>You are about to delete this car, this procedure is irreversible.</p>
							        <p>Do you want to proceed?</p>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							        
							        <c:url value="./DeleteCar" var="deleteCar">
									    <c:param name="carId" value="${car.id}" />
									</c:url>
							        <button type="button" onclick="document.location = '${deleteCar}';" class="btn btn-danger btn-ok">Delete</button>
							      </div>
							    </div>
							  </div>
							</div>
							<a href="#" data-record-id="23" data-toggle="modal" data-target="#confirm-delete">
							  Delete Car</a>
							  <br/>
							<a href="javascript:editData();">Edit</a>
					</div>
				</c:if>
			</div>
			<div class="col-md-8">
				<div id="details">
					<table class="table">
						<tr>
							<td><h3>Brand:</h3> </td>
							<td><c:out value="${car.brand}"/></td>
						</tr>
						<tr>
							<td><h3>Model:</h3> </td>
							<td><c:out value="${car.model}"/></td>
						</tr>
						<tr>
							<td><h3>Cylinder Capacity (cm<sup>3</sup>):</h3> </td>
							<td><c:out value="${car.cylinderCapacity}"/></td>
						</tr>
						<tr>
							<td><h3>Power (cv):</h3> </td>
							<td><c:out value="${car.power}"/></td>
						</tr>
						<tr>
							<td><h3>Fuel:</h3> </td>
							<td><c:out value="${car.fuel}"/></td>
						</tr>
						<tr>
							<td><h3>Distance (km):</h3> </td>
							<td><c:out value="${car.distance}"/></td>
						</tr>
						<tr>
							<td><h3>Register Date:</h3> </td>
							<td><c:out value="${car.registerDate}"/></td>
						</tr>
						<tr>
							<td><h3>Price (€):</h3> </td>
							<td><c:out value="${car.price}"/></td>
						</tr>
					
					</table>
				</div>
				<div id="edit">
					<form action="./CarDetails">
						<table class="table">
							<input type="hidden" name="carId" value="${car.id}"/>
							<tr>
								<td><h3>Brand:</h3> </td>
								<td><input class="form-control"  type="text" name="brand" value="${car.brand}" placeholder="Brand" required="required"></td>
							</tr>
							<tr>
								<td><h3>Model:</h3> </td>
								<td><input class="form-control"  type="text" name="model" value="${car.model}" placeholder="Model" required="required"></td>
							</tr>
							<tr>
								<td><h3>Cylinder Capacity (cm<sup>3</sup>):</h3> </td>
								<td><input class="form-control"  type="text" name="cylinderCapacity" value="${car.cylinderCapacity}" placeholder="Cylinder Capacity" required="required"></td>
							</tr>
							<tr>
								<td><h3>Power (cv):</h3> </td>
								<td><input class="form-control"  type="text" name="power" value="${car.power}" placeholder="Power" required="required"></td>
							</tr>
							<tr>
								<td><h3>Fuel:</h3> </td>
								<td><input class="form-control"  type="text" name="fuel" value="${car.fuel}" placeholder="Fuel" required="required"></td>
							</tr>
							<tr>
								<td><h3>Distance (km):</h3> </td>
								<td><input class="form-control"  type="text" name="distance" value="${car.distance}" placeholder="Distance" required="required"></td>
							</tr>
							<tr>
								<td><h3>Register Date:</h3> </td>
								<td><input class="form-control"  type="text" name="registerDate" value="${car.registerDate}" placeholder="Register Date" required="required"></td>
							</tr>
							<tr>
								<td><h3>Price (€):</h3> </td>
								<td><input class="form-control"  type="text" name="price" value="${car.price}" placeholder="Price" required="required"></td>
							</tr>
							<tr>
								<td><h3>Image URL</h3> </td>
								<td><input class="form-control"  type="text" name="imageURL" value="${car.imageURL}" placeholder="Image URL" required="required"></td>
							</tr>
						</table>
						<input type="submit" name="action" type="button" value="edit">
						<input onClick="javascript:cancelEdit()" type="button" value="Cancel" name="cancel"/>
					</form>
				</div>
			</div>
		</div>
    		
    		<script>
	    		function editData(){
	    			document.getElementById("edit").style.display = "block";
	    			document.getElementById("details").style.display = "none";
	    		}
	    		
	    		function cancelEdit(){
	    			document.getElementById("edit").style.display = "none";
	    			document.getElementById("details").style.display = "block";
	    		}
    		</script>
	</body>
</html>

