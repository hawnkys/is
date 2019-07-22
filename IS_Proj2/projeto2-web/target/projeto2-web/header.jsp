<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	<!-- CSS -->
	<link href="resources/css/font-awesome.min.css" rel="stylesheet">
	<link href="resources/css/bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/style.css" rel="stylesheet">
	
	<body>
	    <div class="navbar-more-overlay"></div>
		<nav class="navbar navbar-inverse navbar-fixed-top animate">
		<div class="container navbar-more visible-xs">
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Search for...">
						<span class="input-group-btn">
							<button class="btn btn-default" type="submit">Submit</button>
						</span>
					</div>
				</div>
			</form>
			<ul class="nav navbar-nav">
				<li>
					<a href="./AddCar">
						<i class="menu-icon fa fa-plus" aria-hidden="true"></i>
						Add Cars
					</a>
				</li>
				<li>
					<a href="./CarList">
						<i class="menu-icon fa fa-car" aria-hidden="true"></i>
						Search Cars
					</a>
				</li>
				<li>
					<a href="./MyProfile">
						<i class="menu-icon fa fa-user" aria-hidden="true"></i>
						My Profile
					</a>
				</li>
				<li>
					<a href="./SignOut">
						<i class="menu-icon fa fa-sign-out" aria-hidden="true"></i>
						Sign Out
					</a>
				</li>
				
			</ul>
		</div>
		<div class="container">
			<div class="navbar-header hidden-xs">
				<a class="navbar-brand" href="./Home">Home</a>
			</div>

			<ul class="nav navbar-nav navbar-right mobile-bar">
				<li class="hidden-xs">
					<a href="./AddCar">
						<i class="menu-icon fa fa-plus" aria-hidden="true"></i>
						Add Cars
					</a>
				</li>
				<li class="hidden-xs">
					<a href="./CarList">
						<i class="menu-icon fa fa-car" aria-hidden="true"></i>
						Search Cars
					</a>
				</li>
				<li class="hidden-xs">
					<a href="./MyProfile">
						<i class="menu-icon fa fa-user" aria-hidden="true"></i>
						My Profile
					</a>
				</li>
				<li class="hidden-xs">
					<a href="./SignOut">
						<i class="menu-icon fa fa-sign-out" aria-hidden="true"></i>
						Sign Out
					</a>
				</li>
				
			</ul>
		</div>
	</nav>

	<script src="resources/js/jquery-3.2.1.min.js"></script>
    	<script src="resources/js/bootstrap.min.js"></script>
    		
	</body>
</html>