<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
		<!-- Reference Bootstrap files -->
		<link rel="stylesheet"
			 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
		<title>User update form</title>
	</head>
	<body>
		<div id="container">
			<%@ include file="navbar.jsp" %>
			<div id="content" class="col-sm-10 offset-sm-1">
				<h3>Update user</h3>
				
				<form:form action="${pageContent.request.contextPath}/users/update/${schoolUserForUpdate.id}"
				modelAttribute="schoolUserForUpdate" method="POST" class="form-horizontal">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					
					<!-- Username -->
					<div style="margin-bottom: 25px" class="input-group">
						<form:label path="username">Username</form:label>
						<form:input path="username" placeholder="username" class="form-control"/>
						<form:errors path="username" cssClass="error"/>
					</div>
					
					<!-- Introduction -->
					<div style="margin-bottom: 25px" class="input-group">
						<form:label path="introduction">Introduction</form:label>
						<form:input path="introduction" placeholder="introduction" class="form-control"/>
						<form:errors path="introduction" cssClass="error"/>
					</div>
					
					<!-- First name -->
					<div style="margin-bottom: 25px" class="input-group">
						<form:label path="firstName">First name</form:label>
						<form:input path="firstName" placeholder="first name" class="form-control"/>
						<form:errors path="firstName" cssClass="error"/>
					</div>
					
					<!-- Last name -->
					<div style="margin-bottom: 25px" class="input-group">
						<form:label path="lastName">Last name</form:label>
						<form:input path="lastName" placeholder="last name" class="form-control"/>
						<form:errors path="lastName" cssClass="error"/>
					</div>
					
					<!-- Email -->
					<div style="margin-bottom: 25px" class="input-group">
						<form:label path="email">Email</form:label>
						<form:input path="email" placeholder="email" class="form-control"/>
						<form:errors path="email" cssClass="error"/>
					</div>
					
					<div class="form-group controls">
						<input type="submit" value="Save" class="btn btn-primary"/>
					</div>
				
				</form:form>
			</div>
		</div>
	</body>
</html>