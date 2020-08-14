<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Registration form</title>
	</head>
	<body>
		<div id="container">
			<div id="content">
				
				<!-- Registration form -->
				<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
					modelAttribute="schoolUser">
					<div>
						<!-- Check for registration error -->
						<c:if test="${registrationError != null}">
							<div>
								${registrationError}
							</div>
						</c:if>
					</div>
					
					<!-- Username -->
					<div>
						<form:input path="username" placeholder="username (*)"/>
						<form:errors path="username"/>
					</div>
					
					<!-- Password -->
					<div>
						<form:input path="password" placeholder="password (*)"/>
						<form:errors path="password"/>
					</div>
					
					<!-- Confirm Password -->
					<div>
						<form:input path="matchingPassword" placeholder="confirm password (*)"/>
						<form:errors path="matchingPassword"/>
					</div>
					
					<!-- First name -->
					<div>
						<form:input path="firstName" placeholder="first name (*)"/>
						<form:errors path="firstName"/>
					</div>
					
					<!-- Last name -->
					<div>
						<form:input path="lastName" placeholder="last name (*)"/>
						<form:errors path="lastName"/>
					</div>
					
					<!-- Email -->
					<div>
						<form:input path="email" placeholder="email (*)"/>
						<form:errors path="email"/>
					</div>
					
					<!-- Register button -->
					<div>
						<input type="submit" value="Register"/>
					</div>
				</form:form>
			</div>
		</div>
	</body>
</html>