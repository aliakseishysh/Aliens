<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="by.shyshaliaksey.webproject.controller.command.PagePath" %>
<c:set var="SESSION_VARIABLES" scope="page" value="${PagePath.SESSION_VARIABLES_JSP.getValue()}"/>
<c:if test="${CONTROLLER == null}">
	<jsp:include page="${SESSION_VARIABLES}"/>
</c:if>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='${JS_USER_PROFILE}'/>"></script>
</head>
<h1>User Profile</h1>
<div class="content-section">
	<div class="media">
		<img class="rounded-circle account-img"
			src="<c:url value="${sessionScope[CURRENT_USER].getImageUrl()}"/>" alt="no image">
		<div class="media-body">
			<h2 id="account-login" class="account-heading">${sessionScope[CURRENT_USER].getLogin()}</h2>
			<p id="account-email" class="text-secondary">${sessionScope[CURRENT_USER].getEmail()}</p>
		</div>
	</div>
	<jsp:include page="${EMAIL_UPDATE_FORM}"/>
	<jsp:include page="${LOGIN_UPDATE_FORM}"/>
	<jsp:include page="${IMAGE_UPDATE_FORM}"/>
	<jsp:include page="${PASSWORD_UPDATE_FORM}"/>
</div>

