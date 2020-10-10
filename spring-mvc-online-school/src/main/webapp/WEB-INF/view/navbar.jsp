<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<style>
			ul{display: flex;
			  align-items: center;
			  justify-content: center;}
		</style>
	</head>
	<body>
		<nav class="navbar navbar-default">
	  		<div class="container-fluid">
		  		<div class="navbar-header">
		  			<h2><a style="color: inherit; text-decoration: none" href="${pageContext.request.contextPath}/">Online School Home Page</a></h2>
		  		</div>
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav navbar-right">
					<li>
						<!-- link to my profile -->
						<security:authorize access="isAuthenticated()">
							<c:url var="myProfileLink" value="/instructors">
								<c:param name="instructorId" value="${currentUser.id}"/>				
							</c:url>
							<a href="${myProfileLink}">My Profile</a>
						</security:authorize>
					</li>
					<li style="margin: 1em 1em; ">
						<a href="${pageContext.request.contextPath}/subjects/all">All subjects</a>
					</li>
					<li>
						<!-- Add login/logout button -->
						<security:authorize access="isAuthenticated()">
							<form:form action="${pageContext.request.contextPath}/logout" method="POST">
								<input type="submit" class="btn btn-outline-secondary" value="Logout"/>
							</form:form>
						</security:authorize>
						
						<security:authorize access="!isAuthenticated()">
							<form:form action="${pageContext.request.contextPath}/loginPage" method="GET">
								<input type="submit" class="btn btn-outline-secondary" value="Login"/>
							</form:form>
						</security:authorize>
					</li>
					</ul>
				</div>
			</div>
		</nav>
	
	</body>
</html>