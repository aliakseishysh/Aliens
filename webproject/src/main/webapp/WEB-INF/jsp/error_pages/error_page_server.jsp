<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="/WEB-INF/jsp/templates/template_nav.jsp"/>
	<h3>Error Page</h3>
	<div class="content-section">
		<h4>Server error:</h4><br>
		<h1>Server error occured</h1>
	</div>
</main>
</body>
</html>