<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="by.shyshaliaksey.webproject.controller.command.PagePath" %>
<c:set var="SESSION_VARIABLES" scope="page" value="${PagePath.SESSION_VARIABLES_JSP.getValue()}"/>
<c:if test="${CONTROLLER == null}">
	<jsp:include page="${SESSION_VARIABLES}"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<body>
<main role="main" class="container">
	<jsp:include page="${NAV_JSP}"/>
	<h1>Home Page</h1>
	<div class="row">
		<div class="col-md-8">
			
			<c:forEach var="entry" items="${aliensList}">
				<c:set var="alien" value="${entry}" scope="request"/>
				<jsp:include page="${POST_JSP}"/>
			</c:forEach>
		</div>
		<div class="col-md-4">
			<div class="content-section">
				<h3>Alienbar</h3>
				<p class='text-muted'>Some features
				<ul class="list-group">
					<li class="list-group-item list-group-item-light">Announcements</li>
					<li class="list-group-item list-group-item-light">News</li>
					<li class="list-group-item list-group-item-light">Contact admin</li>
					<li class="list-group-item list-group-item-light">etc</li>
				</ul>
				</p>
			</div>
		</div>
	</div>
</main>

</body>
</html>