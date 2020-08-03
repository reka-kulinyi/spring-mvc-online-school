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
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
					<th>Created at</th>
				</tr>
				<c:forEach var="instructor" items="${instructors}">
					
					<tr>
						<td>${instructor.firstName}</td>
						<td>${instructor.lastName}</td>
						<td>${instructor.email}</td>
						<td>${instructor.createdAt}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

</html>