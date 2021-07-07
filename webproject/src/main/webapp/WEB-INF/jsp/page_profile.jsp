<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>Profile</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_USER_PROFILE}"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<c:choose>
		<c:when test="${currentUser.role == ADMIN}">
			<jsp:include page="${TEMPLATE_ADMIN_PROFILE_JSP}"/>
		</c:when>
		<c:when test="${currentUser.role == USER}">
			<jsp:include page="${TEMPLATE_USER_PROFILE_JSP}"/>
		</c:when>
		<c:otherwise>
			<p>Unknown Role</p>
		</c:otherwise>
	</c:choose>
</main>
</body>
</html>
