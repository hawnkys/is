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
		
			<div id="search">
 				<a data-toggle="collapse" data-target="#searchOptions"><i class="fa fa-search" aria-hidden="true" style="cursor: pointer;"></i> Refine Search</a>
 				<br/>
				<div id="searchOptions" class="collapse col-md-12">
					
					<form action="./CarList">
					<div class="input-group" style="margin-top: 20px; margin-bottom: 20px;">
		                <div class="input-item col-md-12">
		                    <input name="text" type="text" class="form-control input-lg" placeholder="Search for Model or Brand" />
		                </div>
			            <br/>
			            <div class="input-item col-md-3">
			            		Min Price: <input name="minPrice" type="text" class="form-control input-lg" />
			            </div>
			            <div class="input-item col-md-3">
			            		Max Price: <input name="maxPrice" type="text" class="form-control input-lg" />
			            </div>
			            
			            <div class="input-item col-md-3">
			            		Min km: <input name="minKm" type="text" class="form-control input-lg" />
			            </div>
			            <div class="input-item col-md-3">
			            		Max km: <input name="maxKm" type="text" class="form-control input-lg" />
			            </div>
			            <div class="input-item col-md-6">
			            		Order by: 
			            		<label class="radio-inline"><input type="radio" name="orderBy" value="price" checked="">Price</label>
							<label class="radio-inline"><input type="radio" name="orderBy" value="brand">Brand</label>
			            </div>
			            <div class="input-item col-md-6">
			            		<label class="radio-inline"><input type="radio" name="orderType" value="asc" checked="">Asc</label>
							<label class="radio-inline"><input type="radio" name="orderType" value="desc">Desc</label>
			            </div>
			            <!-- 
			            <div class="input-item col-md-3">
			            		<select class="form-control" name="fromDate">
							   <c:forEach var = "i" begin = "1950" end = "2017">
							        <option><c:out value = "${i}"/></option>
							   </c:forEach>
							 </select>
			            </div>
			             -->
			            
						<input type="submit" name="action" class="login loginmodal-submit" value="search" style="width: 100px; height: 50px; float: right; margin: 10px;">
						</div>
					</form>
				</div>
			</div>
			<br/>
			<br/>
		
			<table class="table">
				<thead>
			    		<tr>
			        		<th></th>
			        		<th>Brand</th>
				        <th>Model</th>
				        <th>Cylinder Capacity (cm<sup>3</sup>)</th>
				        <th>Power (cv)</th>
				        <th>Fuel</th>
				        <th>Distance (km)</th>
				        <th>Register Date</th>
				        <th>Price (€)</th>
			      	</tr>
			    </thead>
			    <tbody>
			    		<c:forEach items="${lc}" var="list">
			    		
			    			<c:url value="./CarDetails" var="carDetails">
						    <c:param name="carId" value="${list.id}" />
						</c:url>
						
				    		<tr style="cursor: pointer" onclick="document.location = '${carDetails}';">
				    			<td><img src="<c:out value="${list.imageURL}"/>" width="150px"></td>
				        		<td><c:out value="${list.brand}"/></td>
				        		<td><c:out value="${list.model}"/></td>
				        		<td><c:out value="${list.cylinderCapacity}"/></td>
				        		<td><c:out value="${list.power}"/></td>
				        		<td><c:out value="${list.fuel}"/></td>
				        		<td><c:out value="${list.distance}"/></td>
				        		<td><c:out value="${list.registerDate}"/></td>
				        		<td><c:out value="${list.price}"/></td>
				      	</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
    		
	</body>
</html>

