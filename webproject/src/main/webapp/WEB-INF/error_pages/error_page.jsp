<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="/WEB-INF/jsp/nav.jsp"/>
	<h3>Error Page</h3>
	<div class="content-section">
		<h4>Server error:</h4><br>
		<h1>404</h1>
	</div>
</main>
</body>
</html>