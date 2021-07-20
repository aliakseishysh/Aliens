<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>403</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="/WEB-INF/jsp/templates/template_nav.jsp"/>
	<h1>Error Page</h1>
	<div class="content-section">
		<h2>400</h2><hr/>
		<h2>Bad Request</h2>
	</div>
</main>
</body>
</html>