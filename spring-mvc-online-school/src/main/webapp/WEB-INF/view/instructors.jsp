<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<title>All instructors</title>
</head>

<body>
	
	<div id="container">
		<div id="content">
			<h2>All instructors</h2>
			<div id="teachers">
				<h4>All teachers</h4>
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
			</div>
			<br/>
			<div>
				<a href="${pageContext.request.contextPath}/">Back to main page</a>
			</div>
		</div>
	</div>
</body>

</html>