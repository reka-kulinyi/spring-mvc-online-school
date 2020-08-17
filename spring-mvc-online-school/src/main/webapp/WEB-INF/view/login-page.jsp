<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Online school - Login</title>
</head>
<body>
	<div>
		<!-- Login form -->
		<form action="${pageContext.request.contextPath}/authenticateTheUser" 
		method="POST">
			<div>
				<c:if test="${param.error != null}">
					<div>
						Invalid username and password
					</div>
				</c:if>
				
				<c:if test="${param.logout != null}">
					<div>
						You have been logged out
					</div>
				</c:if>
			</div>
			
			<!-- User name -->			
			<div>
				<input type="text" name="username" placeholder="username"/>
			</div>
			
			<!-- Password -->			
			<div>
				<input type="password" name="password" placeholder="password"/>
			</div>
			
			<div>
				<input type="submit" value="Login"/>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
		<div>
			<a href="${pageContext.request.contextPath}/register/registrationForm">
			Register new user</a>
		</div>
	</div>
</body>
</html>