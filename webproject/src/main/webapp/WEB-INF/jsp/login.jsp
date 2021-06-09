<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Login</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="/css/main.css"/>" rel="stylesheet">
</head>
<body>
<main role="main" class="container">
	<jsp:include page="nav.jsp"/>
	<h3>Login Page</h3>
	<div class="content-section">
		<form method="POST"
			action="<c:url value="/controller?command=LOGIN_USER"/>">
			<fieldset class="form-group">
				<legend class="border-bottom mb-4">Log In</legend>
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
      				<input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
    			</div>
    			<label class="control-label col-sm-2" for="password">Password:</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
    			</div>
			</fieldset>
			<div class="form-group">
				<button class="btn btn-outline-info" type="submit">Login</button>
				<small class="text-muted ml-2"> 
					<a href="<c:url value="/controller?command=FORGOT_PASSWORD"/>">Forgot Password?</a>
				</small>
			</div>
		</form>
		<div class="border-top pt-3">
			<small class="text-muted"> 
			Need An Account? 
			<a class="ml-2" href="<c:url value="/controller?command=REDIRECT_REGISTER"/>">Sign Up Now</a>
			</small>
		</div>
	</div>
</main>
</body>
</html>

