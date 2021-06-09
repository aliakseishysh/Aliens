<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Profile</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="nav.jsp"/>
	<c:choose>
		<c:when test="${currentUser.getRole().getValue() == 'admin'}">
			<jsp:include page="admin_profile.jsp"/>
		</c:when>
		<c:when test="${currentUser.getRole().getValue() == 'user'}">
			<jsp:include page="user_profile.jsp"/>
		</c:when>
	</c:choose>
</main>
</body>
</html>
