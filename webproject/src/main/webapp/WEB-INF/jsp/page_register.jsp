<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<head>
<title>${TEXT[PAGE_REGISTER_TITLE]}</title>
<!-- 
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
 -->
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
	var STANDARD_EMAIL_FEEDBACK = `${STANDARD_EMAIL_FEEDBACK}`;
	var STANDARD_LOGIN_FEEDBACK = `${STANDARD_LOGIN_FEEDBACK}`;
	var STANDARD_PASSWORD_FEEDBACK = `${STANDARD_PASSWORD_FEEDBACK}`;
	var STANDARD_PASSWORD_CONFIRMATION_FEEDBACK = `${STANDARD_PASSWORD_CONFIRMATION_FEEDBACK}`;
	var EMAIL_STATUS = `${EMAIL_STATUS}`;
	var LOGIN_STATUS = `${LOGIN_STATUS}`;
	var PASSWORD_STATUS = `${PASSWORD_STATUS}`;
	var PASSWORD_CONFIRMATION_STATUS = `${PASSWORD_CONFIRMATION_STATUS}`;
	var EMAIL_FEEDBACK = `${EMAIL_FEEDBACK}`;
	var LOGIN_FEEDBACK = `${LOGIN_FEEDBACK}`;
	var PASSWORD_FEEDBACK = `${PASSWORD_FEEDBACK}`;
	var PASSWORD_CONFIRMATION_FEEDBACK = `${PASSWORD_CONFIRMATION_FEEDBACK}`;
	var PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL = `${PASSWORD_FEEDBACK_INVALID_PASSWORDS_ARE_NOT_EQUAL}`;
	
</script>
<script type="text/javascript" src="<c:url value='${JS_REGISTER}'/>"></script>

</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<h1>${TEXT[PAGE_REGISTER_H1]}</h1>
	<jsp:include page="${FORM_REGISTER}"/>
</main>
	
</body>
</html>