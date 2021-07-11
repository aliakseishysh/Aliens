<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>${TEXT[PAGE_LOGIN_TITLE]}</title>
<!-- 
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
 -->
<script
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script>
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var OPEN_REGISTER_PAGE = `${OPEN_REGISTER_PAGE}`;
	var LOGIN_USER = `${LOGIN_USER}`;
	var OPEN_HOME_PAGE = `${OPEN_HOME_PAGE}`;
	var EMAIL = `${EMAIL}`;
	var PASSWORD = `${PASSWORD}`;
	var OPEN_FORGOT_PASSWORD_PAGE = `${OPEN_FORGOT_PASSWORD_PAGE}`;
	// TODO Add this errors to ServletContext
	var STANDARD_EMAIL_FEEDBACK = `${STANDARD_EMAIL_FEEDBACK}`;
	var STANDARD_PASSWORD_FEDDBACK = `${STANDARD_PASSWORD_FEDDBACK}`;
	var LOGIN_RESULT_INFO_EMAIL_STATUS = `${LOGIN_RESULT_INFO_EMAIL_STATUS}`;
	var LOGIN_RESULT_INFO_PASSWORD_STATUS = `${LOGIN_RESULT_INFO_PASSWORD_STATUS}`;
	var LOGIN_RESULT_INFO_EMAIL_FEEDBACK = `${LOGIN_RESULT_INFO_EMAIL_FEEDBACK}`;
	var LOGIN_RESULT_INFO_PASSWORD_FEEDBACK = `${LOGIN_RESULT_INFO_PASSWORD_FEEDBACK}`;
</script>
<script type="text/javascript" src="<c:url value='${JS_LOGIN}'/>"></script>
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<h1>${TEXT[PAGE_LOGIN_H1]}</h1>
	<jsp:include page="${FORM_LOGIN_JSP}"/>
</main>
</body>
</html>

