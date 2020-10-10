<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
	</style>
	<title>All subjects</title>
</head>

<body>

	<div id="container">
		<%@ include file="navbar.jsp" %>
		<div id="content" class="col-sm-10 offset-sm-1">
			<h2>All subjects</h2>
			<div id="subjects">
				
					<c:choose>
						<c:when test="${fn:length(subjects) gt 0}">
							<c:forEach var="subject" items="${subjects}">
								<c:url var="linkToSubjectTeachers" value="/instructors/subjects">
									<c:param name="subjectName" value="${subject.name}"/>
								</c:url>
								<div>
									<a href="${linkToSubjectTeachers}">${subject.name}</a>
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