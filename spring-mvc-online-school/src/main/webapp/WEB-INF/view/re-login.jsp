<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

		<!-- Reference Bootstrap files -->
		<link rel="stylesheet"
			 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
		<title>User login</title>
	</head>
	<body>
		
		<div class="container">
			<div style="text-align: center">
				<h3>Update confirmed</h3>
				<h4>Please log in with the new username</h4>
			</div>
			<div id="loginbox" style="margin-top: 50px;"
			class="col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2 mainbox">
				<div class="panel panel-info">
	
					<div class="panel-heading">
						<div class="panel-title">Sign In</div>
					</div>
	
					<div style="padding-top: 30px" class="panel-body">
						<!-- Login form -->
						<form action="${pageContext.request.contextPath}/authenticateTheUser" 
						method="POST" class="form-horizontal">
							<div class="form-group">
								<c:if test="${param.error != null}">
									<div class="alert alert-danger col-xs-offset-1 col-xs-10"">
										Invalid username and password
									</div>
								</c:if>
								
								<c:if test="${param.logout != null}">
									<div class="alert alert-success col-xs-offset-1 col-xs-10">
										You have been logged out
									</div>
								</c:if>
							</div>
							
							<!-- User name -->			
							<div style="margin-bottom: 25px" class="input-group">
								<input type="text" name="username" placeholder="username" class="form-control"/>
							</div>
							
							<!-- Password -->			
							<div style="margin-bottom: 25px" class="input-group">
								<input type="password" name="password" placeholder="password" class="form-control"/>
							</div>
							
							<div style="margin-top: 10px" class="form-group,col-sm-6 controls">
								<input type="submit" class="btn btn-success" value="Login"/>
							</div>
							
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>