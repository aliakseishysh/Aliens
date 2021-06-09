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
	<%@include file="nav.jsp"%>
	<h3>Register Page</h3>
	<div class="content-section">
		<form method="POST"
			action="<c:url value="/controller?command=REGISTER_USER"/>">
			<fieldset class="form-group">
				<legend class="border-bottom mb-4">Sign Up</legend>
				<label class="control-label col-sm-2" for="email">Email:</label>
				<div class="col-sm-10">
      				<input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
    			</div>
    			<label class="control-label col-sm-2" for="email">Login:</label>
				<div class="col-sm-10">
      				<input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
    			</div>
    			<label class="control-label col-sm-2" for="password">Password:</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control" id="password" name="password" placeholder="Enter password">
    			</div>
    			<label class="control-label col-sm-2" for="password_confirm">Password confirm:</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="Enter password">
    			</div>
			</fieldset>
			<div class="form-group">
				<button class="btn btn-outline-info" type="submit">Register</button>
			</div>
		</form>
		<div class="border-top pt-3">
			<small class="text-muted"> 
			Already Have An Account? 
			<a class="ml-2" href="<c:url value="/controller?command=REDIRECT_LOGIN"/>">Sign In</a>
			</small>
		</div>
	</div>
</main>
	
</body>
</html>