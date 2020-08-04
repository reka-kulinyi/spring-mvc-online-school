<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>

	<head>
		<title>Instructor profile</title>
	</head>
	
	<body>
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
					${course.subject.name} ${course.price} 
					<br/>
				</c:forEach>
			</c:when>
			<c:otherwise>
				No courses
			</c:otherwise>
		</c:choose>
		
		
	</body>

</html>