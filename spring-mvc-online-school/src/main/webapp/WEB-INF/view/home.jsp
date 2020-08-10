<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<title>Online School Home Page</title>
</head>

<body>
	<h2>Online School Home Page</h2>
	
	<div id="container">
		<div>
			<form:form action="search" method="GET">
				<label for="selectSubject">Choose the subject that you would like to learn</label>
				<select name="searchSubject" id="selectSubject">
					<c:forEach var="subject" items="${subjects}">
						<option value="${subject.name}">${subject.name}</option>					
					</c:forEach>
				</select>
				<label for="setMaxPrice">Max price</label>
				<input type="number" name="maxPrice" value="0" min="0" step="0.01"
				placeholder="0.0" data-number-to-fixed="2" id="setMaxPrice"/>$
				<input type="submit" value="Search"/>
			</form:form>
		</div>
		<br/>
		<div id="content">
			<div id="newTeachers">
				<h4>Our newly registered teachers</h4>
				<table>
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
				<a href="${pageContext.request.contextPath}/instructors/all">All teachers</a>
			</div>
			<br/>
			
			<div id="latestReviews">
				<h4>Latest reviews</h4>
				<c:forEach var="review" items="${recentReviews}">
					<c:url var="linkToProfile" value="/instructors">
						<c:param name="instructorId" value="${review.course.instructor.id}"/>
					</c:url>
					<div id ="review">
						<p>About <a href="${linkToProfile}">
						${review.course.instructor.firstName} ${review.course.instructor.lastName}</a>
						</p>
						<p>${review.text} ${review.createdAt} by ${review.student.firstName} ${review.student.lastName}</p>
					</div>
				</c:forEach>
			</div>
			
			<div>
				<a href="${pageContext.request.contextPath}/subjects/all">All subjects</a>
			</div>
		</div>
	</div>
</body>

</html>