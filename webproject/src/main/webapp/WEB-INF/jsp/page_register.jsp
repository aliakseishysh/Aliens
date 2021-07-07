<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<head>
<title>Login</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<script>
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var REGISTER_USER = `${REGISTER_USER}`;
	var OPEN_LOGIN_PAGE = `${OPEN_LOGIN_PAGE}`;
	var EMAIL = `${EMAIL}`;
	var LOGIN = `${LOGIN}`;
	var PASSWORD = `${PASSWORD}`;
	var PASSWORD_CONFIRM = `${PASSWORD_CONFIRM}`;
	var REGISTER_STANDARD_EMAIL_FEEDBACK = `${REGISTER_STANDARD_EMAIL_FEEDBACK}`;
	var REGISTER_STANDARD_LOGIN_FEEDBACK = `${REGISTER_STANDARD_LOGIN_FEEDBACK}`;
	var REGISTER_STANDARD_PASSWORD_FEEDBACK = `${REGISTER_STANDARD_PASSWORD_FEEDBACK}`;
	var REGISTER_STANDARD_PASSWORD_CONFIRM_FEEDBACK = `${REGISTER_STANDARD_PASSWORD_CONFIRM_FEEDBACK}`;
	var REGISTER_RESULT_INFO_EMAIL_STATUS = `${REGISTER_RESULT_INFO_EMAIL_STATUS}`;
	var REGISTER_RESULT_INFO_LOGIN_STATUS = `${REGISTER_RESULT_INFO_LOGIN_STATUS}`;
	var REGISTER_RESULT_INFO_PASSWORD_STATUS = `${REGISTER_RESULT_INFO_PASSWORD_STATUS}`;
	var REGISTER_RESULT_INFO_PASSWORD_CONFIRM_STATUS = `${REGISTER_RESULT_INFO_PASSWORD_CONFIRM_STATUS}`;
	var REGISTER_RESULT_INFO_EMAIL_FEEDBACK = `${REGISTER_RESULT_INFO_EMAIL_FEEDBACK}`;
	var REGISTER_RESULT_INFO_LOGIN_FEEDBACK = `${REGISTER_RESULT_INFO_LOGIN_FEEDBACK}`;
	var REGISTER_RESULT_INFO_PASSWORD_FEEDBACK = `${REGISTER_RESULT_INFO_PASSWORD_FEEDBACK}`;
	var REGISTER_RESULT_INFO_PASSWORD_CONFIRM_FEEDBACK = `${REGISTER_RESULT_INFO_PASSWORD_CONFIRM_FEEDBACK}`;
	var REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS = `${REGISTER_RESULT_INFO_FEEDBACK_INVALID_PASSWORDS_NOT_EQUALS}`;
	
</script>
<script type="text/javascript" src="<c:url value='${JS_REGISTER}'/>"></script>

</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<h3>Register Page</h3>
	<jsp:include page="${FORM_REGISTER}"/>
</main>
	
</body>
</html>