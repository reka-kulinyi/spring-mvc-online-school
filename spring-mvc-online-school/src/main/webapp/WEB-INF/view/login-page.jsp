<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Online school - Login</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<!-- Reference Bootstrap files -->
	<link rel="stylesheet"
		 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
		class="col-md-3 col-md-offset-2 col-sm-6 col-sm-offset-2">
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
						<div class="alert alert-success col-xs-offset-1 col-xs-10"">
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
		<br/>
		<div class="col-sm-6 controls">
			<a href="${pageContext.request.contextPath}/register/registrationForm"
			class="btn btn-primary" role="button">
			Register new user</a>
		</div>
	</div>
	
</body>
</html>