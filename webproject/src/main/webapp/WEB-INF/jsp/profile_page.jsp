<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="by.shyshaliaksey.webproject.controller.command.PagePath" %>
<c:set var="SESSION_VARIABLES" scope="page" value="${PagePath.SESSION_VARIABLES_JSP.getValue()}"/>
<c:if test="${CONTROLLER == null}">
	<jsp:include page="${SESSION_VARIABLES}"/>
</c:if>
<html>
<head>
<title>Profile</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${NAV_JSP}"/>
	<c:set var="currentRole" scope="page" value="${currentUser.getRole()}"/>
	<c:choose>
		<c:when test="${currentRole == ROLE_ADMIN}">
			<jsp:include page="${ADMIN_PROFILE_JSP}"/>
		</c:when>
		<c:when test="${currentRole == ROLE_USER}">
			<jsp:include page="${USER_PROFILE_JSP}"/>
		</c:when>
	</c:choose>
</main>
</body>
</html>
