<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_SUGGEST_ALIEN_TITLE]}</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_CUSTOM_FILE}"/>" rel="stylesheet">
<script>
	var SUGGEST_ALIEN_PAGE = `${OPEN_USER_SUGGEST_ALIEN_PAGE}`;
	var WEB_SITE_NAME = `${WEB_SITE_NAME}`;
	var PROJECT_NAME = `${PROJECT_NAME}`;
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var OPEN_HOME_PAGE = `${OPEN_HOME_PAGE}`;
	var SUGGEST_ALIEN = `${SUGGEST_ALIEN}`;
	var SUGGEST_ALIEN_IMAGE = `${SUGGEST_ALIEN_IMAGE}`;

	var ALIEN_NAME = `${ALIEN_NAME}`;
	var ALIEN_SMALL_DESCRIPTION = `${ALIEN_SMALL_DESCRIPTION}`;
	var ALIEN_FULL_DESCRIPTION = `${ALIEN_FULL_DESCRIPTION}`;
	var ALIEN_NEW_IMAGE = `${ALIEN_NEW_IMAGE}`;
	var USER_ID = `${USER_ID}`;

	var STANDARD_ALIEN_NAME_FEEDBACK = `${STANDARD_ALIEN_NAME_FEEDBACK}`;
	var STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK = `${STANDARD_ALIEN_SMALL_DESCRIPTION_FEEDBACK}`;
	var STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK = `${STANDARD_ALIEN_FULL_DESCRIPTION_FEEDBACK}`;
	var STANDARD_IMAGE_FEEDBACK = `${STANDARD_IMAGE_FEEDBACK}`;
	var ALIEN_NAME_STATUS = `${ALIEN_NAME_STATUS}`;
	var ALIEN_SMALL_DESCRIPTION_STATUS = `${ALIEN_SMALL_DESCRIPTION_STATUS}`;
	var ALIEN_FULL_DESCRIPTION_STATUS = `${ALIEN_FULL_DESCRIPTION_STATUS}`;
	var IMAGE_STATUS = `${IMAGE_STATUS}`;
	var ALIEN_NAME_FEEDBACK = `${ALIEN_NAME_FEEDBACK}`;
	var ALIEN_SMALL_DESCRIPTION_FEEDBACK = `${ALIEN_SMALL_DESCRIPTION_FEEDBACK}`;
	var ALIEN_FULL_DESCRIPTION_FEEDBACK = `${ALIEN_FULL_DESCRIPTION_FEEDBACK}`;
	var IMAGE_FEEDBACK = `${IMAGE_FEEDBACK}`;
</script>
<script type="module" src="<c:url value='${JS_BUNDLE}'/>"></script>
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<h1>${TEXT[PAGE_SUGGEST_ALIEN_H1]}</h1>
	<div class="content-section">
		<jsp:include page="${FORM_ALIEN_SUGGEST_JSP}"/>
		<jsp:include page="${FORM_ALIEN_SUGGEST_IMAGE_JSP}"/>
	</div>
</main>
</body>
</html>
