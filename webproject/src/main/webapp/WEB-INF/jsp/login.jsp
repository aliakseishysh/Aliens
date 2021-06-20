<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${NAV_JSP}"/>
	<h3>Login Page</h3>
	<div class="content-section">
		<form method="POST"
			action="<c:url value="/${CONTROLLER}?${COMMAND}=${LOGIN_USER}"/>">
			<fieldset class="form-group">
				<legend class="border-bottom mb-4">Log In</legend>
				<label class="control-label col-sm-2" for="${EMAIL}">Email:</label>
				<div class="col-sm-10">
      				<input type="email" class="form-control" id="${EMAIL}" name="${EMAIL}" placeholder="Enter email">
    			</div>
    			<label class="control-label col-sm-2" for="${PASSWORD}">Password:</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control" id="${PASSWORD}" name="${PASSWORD}" placeholder="Enter password">
    			</div>
			</fieldset>
			<div class="form-group">
				<button class="btn btn-outline-info" type="submit">Login</button>
				<small class="text-muted ml-2"> 
					<a href="<c:url value="/${CONTROLLER}?${COMMAND}=${FORGOT_PASSWORD}"/>">Forgot Password?</a>
				</small>
			</div>
		</form>
		<div class="border-top pt-3">
			<small class="text-muted"> 
			Need An Account? 
			<a class="ml-2" href="<c:url value="/${CONTROLLER}?${COMMAND}=${OPEN_REGISTER_PAGE}"/>">Sign Up Now</a>
			</small>
		</div>
	</div>
</main>
</body>
</html>

