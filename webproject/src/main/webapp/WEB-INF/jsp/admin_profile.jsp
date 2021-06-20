<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script type="text/javascript" src="<c:url value='${JS_USER_PROFILE}'/>"></script>
<script type="text/javascript" src="<c:url value='${JS_ADMIN_PROFILE}'/>"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
</head>
<h1>Admin Profile</h1>
<div class="content-section">
	<div class="media">
		<img class="rounded-circle account-img"
			src="<c:url value="${currentUser.getImageUrl()}"/>" alt="no image">
		<div class="media-body">
			<h2 class="account-heading">${currentUser.getLogin()}</h2>
			<p class="text-secondary">${ currentUser.getEmail()}</p>
		</div>
	</div>
	<jsp:include page="${BAN_UNBAN_USER_FORM_JSP}"/>
	<jsp:include page="${UPDATE_EMAIL_FORM_JSP}"/>
	<jsp:include page="${UPDATE_LOGIN_FORM_JSP}"/>
	<jsp:include page="${UPDATE_IMAGE_FORM_JSP}"/>
	<jsp:include page="${UPDATE_PASSWORD_FORM_JSP}"/>
	
	
</div>
