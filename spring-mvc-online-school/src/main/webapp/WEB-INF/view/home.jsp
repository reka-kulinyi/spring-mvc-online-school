<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

<head>
	<title>Online School Home Page</title>
</head>

<body>
	<h2>Online School Home Page</h2>
	
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Instructor name</th>
					<th>Created at</th>
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
										${course.subject.name} ${course.price} |
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
	</div>
</body>

</html>