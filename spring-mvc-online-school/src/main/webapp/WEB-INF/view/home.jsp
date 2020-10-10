<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
			form div{display: flex;
			  align-items: center;
			  justify-content: center;}
			  #newTeachers{margin-top: 2em;}
			  #content{margin-top: 2em;}
			  .review{margin-top: 1.5em; 
			  	background-color:rgb(246, 246, 246); 
			  	padding: 5px;
			  	border-radius: 4px;}
			  .review p{ margin: 4px 5px;}
		</style>
		<title>Online School Home Page</title>
</head>

<body>
	<div id="container">
		<%@ include file="navbar.jsp" %>
		<div id="content" class="col-sm-10 offset-sm-1">
		<div class="row" id="main-search">
			<form:form action="search" method="GET" class="form-horizontal">
				<div class="col-sm-4">
					<label for="selectSubject">Choose subject</label>
				
					<select name="searchSubject" id="selectSubject" class="form-control">
						<c:forEach var="subject" items="${subjects}">
							<option value="${subject.name}">${subject.name}</option>					
						</c:forEach>
					</select>
				</div>
			
				<div class="col-sm-4">
					<label for="setMaxPrice">Max price</label>
			
					<input type="number" name="maxPrice" value="0" min="0" step="0.01"
					placeholder="0.0" data-number-to-fixed="2" id="setMaxPrice" class="form-control"/>$
				</div>
				<div class="col-sm-2 controls">
					<input type="submit" value="Search" class="btn btn-default"/>
				</div>
			</form:form>
		</div>
		<br/>
		
			<div id="newTeachers">
				<h4>Our newly registered teachers</h4>
				<table class="table table-condensed table-hover">
					<tr>
						<th>Instructor name</th>
						<th>Teaches since</th>
						<th>Courses</th>
					</tr>
					<c:forEach var="instructor" items="${instructors}">
						<!-- link for instructor profile -->
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
				<b><a href="${pageContext.request.contextPath}/instructors/all">All teachers</a></b>
			</div>
			<br/>
			
			<div id="latestReviews">
				<h4>Latest reviews</h4>
				<c:forEach var="review" items="${recentReviews}">
					<c:url var="linkToProfile" value="/instructors">
						<c:param name="instructorId" value="${review.course.instructor.id}"/>
					</c:url>
					<div class ="review">
						<p>About <a href="${linkToProfile}">
						${review.course.instructor.firstName} ${review.course.instructor.lastName}</a>
						</p>
						<p>${review.text}</p>
						<p>${review.createdAt} by student ${review.student.firstName} ${review.student.lastName}</p>
					</div>
				</c:forEach>
			</div>
			
			<div>
				
			</div>
		</div>
	</div>
</body>

</html>