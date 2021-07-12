<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
	<link rel="shortcut icon" type="image/png" href="/webproject/favicon.png"/>
</head>
<script>
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var LOGOUT_USER = `${LOGOUT_USER}`;
	var CHANGE_LOCALE = `${CHANGE_LOCALE}`;
	var LOCALE = `${LOCALE}`;
	var LOCALIZATION_RU = `${LOCALIZATION_RU}`;
	var LOCALIZATION_EN = `${LOCALIZATION_EN}`;
</script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script type="text/javascript" src="<c:url value='${JS_NAV}'/>"></script>
<header class="site-header">
	<nav class="navbar navbar-expand-md navbar-dark bg-steel fixed-top">
		<div class="container">
			<a class="navbar-brand mr-4" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_HOME_PAGE}"/>'>${TEXT[TEMPLATE_NAV_ALIENS]}</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarToggle" aria-controls="navbarToggle"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarToggle">
				<div class="navbar-nav mr-auto">
					<a class="nav-item nav-link" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_HOME_PAGE}"/>">${TEXT[TEMPLATE_NAV_HOME]}</a>
					<a class="nav-item nav-link" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ABOUT_PAGE}"/>">${TEXT[TEMPLATE_NAV_ABOUT]}</a>
				</div>
				<div class="navbar-nav nav-item nav-link">
					<div class="dropdown" id="nav-dropdawn-container">
						  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						  		${CURRENT_LOCALIZATION_NAME}
						  </button>
						  <!-- TODO hrefs -->
						  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton" id="nav-dropdown-menu">
						    	<a class="dropdown-item" href='#' onclick="navigation.changeLocaleEn()">${LOCALIZATION_EN}</a>
						     	<a class="dropdown-item" href='#' onclick="navigation.changeLocaleRu()">${LOCALIZATION_RU}</a>
						  </div>
					</div>
					<c:choose>
						<c:when test="${currentUser.role eq GUEST}">
							<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_LOGIN_PAGE}"/>'>${TEXT[TEMPLATE_NAV_LOGIN]}</a>
							<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_REGISTER_PAGE}"/>'>${TEXT[TEMPLATE_NAV_REGISTER]}</a>
						</c:when>
						<c:when test="${currentUser.role eq ADMIN or currentUser.role eq USER}">
							<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_USER_PROFILE_PAGE}"/>'>${TEXT[TEMPLATE_NAV_PROFILE]}</a>
							<a class="nav-item nav-link" href='#' onclick="navigation.logoutUser()">${TEXT[TEMPLATE_NAV_LOGOUT]}</a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</nav>
</header>


