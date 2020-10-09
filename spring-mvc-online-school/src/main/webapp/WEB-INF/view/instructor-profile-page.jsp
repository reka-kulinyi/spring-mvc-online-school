<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
		<title>Instructor profile</title>
	</head>
	
	<body>
		<div id="container">
			<%@ include file="navbar.jsp" %>
			<div id="content">
				<div>
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
				</div>
				<br/>
				<div>
					<h4>Courses:</h4>
					<c:choose>
						<c:when test="${fn:length(instructor.courses) gt 0}">
							<c:forEach var="course" items="${instructor.courses}">
								${course.subject.name}
								<fmt:setLocale value="en_US"/>
								<fmt:formatNumber value="${course.price}" type="currency"/>
								
								<!-- delete link -->
								<security:authorize access="isAuthenticated()">
									<c:if test="${currentUser.id == instructor.id}">
										<c:url var="deleteLink" value="/course/delete">
											<c:param name="courseId" value="${course.id}"/>
											<c:param name="instructorId" value="${instructor.id}"/>
										</c:url>
										<a href="${deleteLink}"
										onClick="if(!(confirm('Are you sure you want to delete this course?')))return false">
										Delete
										</a>
									</c:if>
								</security:authorize>
								<br/>
							</c:forEach>
						</c:when>
						<c:otherwise>
							No courses
						</c:otherwise>
					</c:choose>
				</div>
				<br/>
				<div>
					<h4>Reviews:</h4>
					<c:choose>
						<c:when test="${fn:length(reviews) gt 0}">
							<c:forEach var="review" items="${reviews}">
								<p>${review.text}</p>
								<p>
									by ${review.student.firstName} ${review.student.lastName}
									about ${instructor.firstName} ${instructor.lastName}'s 
									${review.course.subject.name} course
								</p>
								<br/>
							</c:forEach>
						</c:when>
						<c:otherwise>
							No reviews yet
						</c:otherwise>
					</c:choose>
				</div>
				<div>
					<a href="${pageContext.request.contextPath}/">Back to main page</a>
				</div>
			</div>
		</div>
	</body>

</html>