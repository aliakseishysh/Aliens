<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
					<c:if test="${currentUser.role eq ADMIN}">
						<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ADMIN_FUNCTIONAL_PAGE}"/>'>${TEXT[TEMPLATE_NAV_ADMIN_PAGE]}</a>
						<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ADMIN_SUGGESTED_ALIENS_PAGE}"/>'>${TEXT[TEMPLATE_NAV_SUGGESTED_ALIENS]}</a>
						<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ADMIN_SUGGESTED_ALIENS_IMAGES_PAGE}"/>'>${TEXT[TEMPLATE_NAV_SUGGESTED_IMAGES]}</a>
					</c:if>
					<c:if test="${currentUser.role eq USER}">
						<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_USER_SUGGEST_ALIEN_PAGE}"/>'>${TEXT[TEMPLATE_NAV_SUGGEST]}</a>
					</c:if>
				
				</div>
				<div class="navbar-nav nav-item nav-link">
					<div class="dropdown" id="nav-dropdown-container">
						  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						  		${CURRENT_LOCALIZATION_NAME}
						  </button>
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


