<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script type="text/javascript" src="<c:url value='${JS_USER_PROFILE}'/>"></script>
</head>
<h1>User Profile</h1>
<div class="content-section">
	<div class="media">
		<jsp:include page="${USER_IMAGE_JSP}"/>
		<div class="media-body">
			<h2 id="account-login" class="account-heading">${currentUser.getLogin()}</h2>
			<p id="account-email" class="text-secondary">${currentUser.getEmail()}</p>
		</div>
	</div>
	<jsp:include page="${UPDATE_EMAIL_FORM_JSP}"/>
	<jsp:include page="${UPDATE_LOGIN_FORM_JSP}"/>
	<jsp:include page="${UPDATE_IMAGE_FORM_JSP}"/>
	<jsp:include page="${UPDATE_PASSWORD_FORM_JSP}"/>
</div>

