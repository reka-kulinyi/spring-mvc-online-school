<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Instructor profile</title>
	</head>
	
	<body>
		<div id="container">
			<div id="content">
				<h2>${instructor.firstName} ${instructor.lastName}</h2>
				<p>${instructor.email}</p>
				<p>Introduction</p>
				<c:choose>
					<c:when test="${instructor.introduction != null}">
						<p>${instructor.introduction}</p>
					</c:when>
					<c:otherwise>
						No introduction
					</c:otherwise>
				</c:choose>		
				<br/>
				<p>Courses : </p>
				<c:choose>
					<c:when test="${fn:length(instructor.courses) gt 0}">
						<c:forEach var="course" items="${instructor.courses}">
							${course.subject.name}
							<fmt:setLocale value="en_US"/>
							<fmt:formatNumber value="${course.price}" type="currency"/> |
							<br/>
						</c:forEach>
					</c:when>
					<c:otherwise>
						No courses
					</c:otherwise>
				</c:choose>
				
				<div>
					<a href="${pageContext.request.contextPath}/">Back to main page</a>
				</div>
			</div>
		</div>
	</body>

</html>