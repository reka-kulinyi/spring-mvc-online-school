<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
	<title>Online School Instructors List</title>
</head>

<body>
	
	<div id="container">
		<%@ include file="navbar.jsp" %>
		<div id="content">
			<h2>Instructors</h2>
					<div>
						<c:choose>
						<c:when test="${fn:length(instructors) gt 0}">
							<table>
								<tr>
									<th>Instructor name</th>
									<th>Teaches since</th>
									<th>Courses</th>
								</tr>
								<c:forEach var="instructor" items="${instructors}">
									<!-- link for teacher profile -->
									<c:url var="profileLink" value="/instructors">
										<c:param name="instructorId" value="${instructor.id}"/>
									</c:url>
									<tr>
										<td>
											<a href="${profileLink}">${instructor.firstName} ${instructor.lastName}</a>
										</td>
										<td>${instructor.createdAt}</td>
										<td>
											<c:choose>
												<c:when test="${fn:length(instructor.courses) gt 0}">
													<c:forEach var="course" items="${instructor.courses}">
														${course.subject.name} 
														<fmt:setLocale value="en_US"/>
														<fmt:formatNumber value="${course.price}" type="currency"/> |
													</c:forEach>
												</c:when>
												<c:otherwise>
													No courses
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</table>
						</c:when>
						<c:otherwise>
							No teacher found for ${param.searchSubject} 
							or there is no course for less than ${param.maxPrice}$
						</c:otherwise>
					</c:choose>
					
				
				<br/>
				<div>
					<a href="${pageContext.request.contextPath}/">Back to main page</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>