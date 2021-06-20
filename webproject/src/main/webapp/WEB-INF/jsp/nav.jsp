<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<header class="site-header">
	<nav class="navbar navbar-expand-md navbar-dark bg-steel fixed-top">
		<div class="container">
			<a class="navbar-brand mr-4" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_HOME_PAGE}"/>'>Aliens</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarToggle" aria-controls="navbarToggle"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarToggle">
				<div class="navbar-nav mr-auto">
					<a class="nav-item nav-link" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_HOME_PAGE}"/>">Home</a>
					<a class="nav-item nav-link" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_ABOUT_PAGE}"/>">About</a>
				</div>
				<div class="navbar-nav">
					<c:choose>
						<c:when test="${currentUser == null}">
							<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_LOGIN_PAGE}"/>'>Login</a>
							<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_REGISTER_PAGE}"/>'>Register</a>
						</c:when>
						<c:when test="${currentUser != null}">
							<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_USER_PROFILE_PAGE}"/>'>Profile</a>
							<a class="nav-item nav-link" href='<c:url value="/${CONTROLLER}?${COMMAND}=${LOGOUT_USER}"/>'>Logout</a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</nav>
</header>