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
		
			<div class="col-lg-12 col-sm-12">
			    <div class="card hovercard">
			        <div class="card-background"></div>
			        <div class="useravatar">
			            <img alt="" src="resources/imgs/user.png">
			        </div>
			        <div class="card-info"> <span class="card-title"><c:out value = "${user.name}"/></span>
			
			        </div>
			    </div>
			    <div class="btn-pref btn-group btn-group-justified btn-group-lg" role="group" aria-label="...">
			        <div class="btn-group" role="group">
			            <button type="button" id="stars" class="btn btn-primary" href="#tab1" data-toggle="tab"><i class="fa fa-user" aria-hidden="true"></i>
			                <div class="hidden-xs">Profile Info</div>
			            </button>
			        </div>
			        <div class="btn-group" role="group">
			            <button type="button" id="favorites" class="btn btn-default" href="#tab2" data-toggle="tab"><i class="fa fa-star-o" aria-hidden="true"></i>
			                <div class="hidden-xs">Favorites</div>
			            </button>
			        </div>
			        <div class="btn-group" role="group">
			            <button type="button" id="following" class="btn btn-default" href="#tab3" data-toggle="tab"><i class="fa fa-list" aria-hidden="true"></i>
			                <div class="hidden-xs">My Cars</div>
			            </button>
			        </div>
			    </div>
		
		        <div class="well">
		      	<div class="tab-content">
			        <div class="tab-pane fade in active" id="tab1">
			          <div id="details">
			          		
			          		<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							  <div class="modal-dialog">
							    <div class="modal-content">
							      <div class="modal-header">
							        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							        <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
							      </div>
							      <div class="modal-body">
							        <p>You are about to delete this ser account, this procedure is irreversible.</p>
							        <p>Do you want to proceed?</p>
							      </div>
							      <div class="modal-footer">
							        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							        <button type="button" onClick="location.href='./DeleteUser'" class="btn btn-danger btn-ok">Delete</button>
							      </div>
							    </div>
							  </div>
							</div>
							<a style="float: right; margin-left: 25px;" href="#" data-record-id="23" data-toggle="modal" data-target="#confirm-delete">
							  Delete User</a>
			          		<a style="float: right" href="javascript:editData();">Edit</a>
			          		
				          <h3>Name:</h3> <c:out value = "${user.name}"/>
				          <h3>Username:</h3> <c:out value = "${user.username}"/>
				          <h3>Password:</h3> <c:out value = "${user.password}"/>
				          <h3>Email:</h3> <c:out value = "${user.email}"/>
			          </div>
			          <div id="edit">
			          	<form action="./MyProfile">
							<h3>Name: </h3><input type="text" name="name" value="${user.name}" placeholder="Name" required="required">
							<h3>Username: </h3><input type="text" name="username" value="${user.username}" placeholder="Username" required="required">
							<h3>Password: </h3><input type="password" name="password" value="${user.password}" placeholder="Password" required="required">
							<h3>Email: </h3><input type="text" name="email" value="${user.email}" placeholder="Email" required="required">
							<br/>
							<br/>
							<input type="submit" name="action" type="button" value="edit">
							<input onClick="javascript:cancelEdit()" type="button" value="Cancel" name="cancel"/>
						</form>
			          </div>
			        </div>
			        <div class="tab-pane fade in" id="tab2">
			          <h3></h3>
			        </div>
			        <div class="tab-pane fade in" id="tab3">
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
			    		<c:forEach items="${myCars}" var="list">
			    		
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

