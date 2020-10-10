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
		<style>
			#content{margin-top: 2em;}
			.review{margin-top: 1.5em; 
			  background-color:rgb(246, 246, 246); 
			  padding: 5px;
			  border-radius: 4px;}
			.review p{ margin: 4px 5px;}
		</style>
		<title>Instructor profile</title>
	</head>
	
	<body>
		<div id="container">
			<%@ include file="navbar.jsp" %>
			<div id="content" class="col-sm-10 offset-sm-1">
				<div>
					<div style="margin-bottom: 2em">
						<h2>${instructor.firstName} ${instructor.lastName}</h2>
					</div>
					
					<security:authorize access="isAuthenticated()">
						<div>
							<c:if test="${currentUser.id == instructor.id}">
								<!-- Update profile link -->
								<div>
									<c:url var="updateProfileLink" value="/instructors/updateForm">
									<c:param name="userId" value="${instructor.id}"/>
									</c:url>
									<a href="${updateProfileLink}">Update profile</a>
								</div>
								
								<!-- Update profile link -->
								<div>
									<c:url var="addNewCourseLink" value="/courses/addNewCourse">
									<c:param name="userId" value="${instructor.id}"/>
									</c:url>
									<a href="${addNewCourseLink}">Add new course</a>
								</div>
								
							</c:if>
						</div>
					</security:authorize>
					<div style="margin-bottom: 1.5em">
						<h4>Contact</h4>
						<p>${instructor.email}</p>
					</div>
					<div style="margin-bottom: 1.5em">
						<h4>Introduction</h4>
						<c:choose>
							<c:when test="${instructor.introduction != null}">
								<p>${instructor.introduction}</p>
							</c:when>
							<c:otherwise>
								No introduction
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			
				<div style="margin-bottom: 1.5em">
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
										<c:url var="deleteLink" value="/courses/delete">
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
				
				<div>
					<h4>Reviews:</h4>
					<c:choose>
						<c:when test="${fn:length(reviews) gt 0}">
							<c:forEach var="review" items="${reviews}">
								<div class="review">
									<p>${review.text}</p>
									<p>
										by ${review.student.firstName} ${review.student.lastName}
										about ${instructor.firstName} ${instructor.lastName}'s 
										${review.course.subject.name} course
									</p>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							No reviews yet
						</c:otherwise>
					</c:choose>
				</div>
				<div style="margin-top: 1em">
					<b><a href="${pageContext.request.contextPath}/">Back to main page</a></b>
				</div>
			</div>
		</div>
	</body>

</html>