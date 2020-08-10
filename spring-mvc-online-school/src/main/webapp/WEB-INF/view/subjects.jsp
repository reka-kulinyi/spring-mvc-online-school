<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	<title>All subjects</title>
</head>

<body>
	
	
	<div id="container">
		<div id="content">
			<h2>All subjects</h2>
			<div id="subjects">
				
					<c:choose>
						<c:when test="${fn:length(subjects) gt 0}">
							<c:forEach var="subject" items="${subjects}">
								<c:url var="subjectLink" value="/subjects">
									<c:param name="subjectId" value="${subject.id}"/>
								</c:url>
								<div>
									<a href="${subjectLink}">${subject.name}</a>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							No subjects found
						</c:otherwise>
					</c:choose>
					
			</div>
			<br/>
			<div>
				<a href="${pageContext.request.contextPath}/">Back to main page</a>
			</div>
		</div>
	</div>
</body>

</html>