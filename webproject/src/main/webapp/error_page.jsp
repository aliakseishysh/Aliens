<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${NAV_JSP}"/>
	<h3>Error Page</h3>
	<div class="content-section">
		<h4>Server error:</h4><br>
		<c:out value="${sessionScope[sessionScope.ERROR_INFORMATION]}" />
	</div>
</main>
	
</body>
</html>