<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<title>Insert title here</title>
	</head>
	<body>
		<div id="container">
			<%@ include file="navbar.jsp" %>
			<div id="content" class="col-sm-10 offset-sm-1">
				<h2>Add new course</h2>
				<div>
					<form:form action="${pageContext.request.contextPath}/courses/saveCourse"
					 method="POST"
					 modelAttribute="schoolCourse"
					 class="form-horizontal">
					 	<!-- error -->
					 	<div>
					 		<label for="selectSubject">Choose the subject that you will teach</label>
					 		<form:select path="subjectName" class="form-control">
					 			<c:forEach var="subject" items="${subjects}">
					 				<form:option value="${subject.name}">${subject.name}</form:option>
					 			</c:forEach>
					 		</form:select>
					 	</div>
					 	
					 	<div>
					 		<label for="setPrice">Set price</label>
					 		<input type="number" name="price" value="0" min="1" step="0.01" placeholder="0.0"
					 		data-number-to-fixed="2" id="setPrice"/>$
					 		<form:errors path="price" cssClass="error"/>
					 	</div>
						
						<div style="margin-top: 10px" class="form-group">
							<input type="submit" class="btn btn-primary" value="Add new course"/>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</body>
</html>