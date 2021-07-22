<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_PROFILE_TITLE]}</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<link href="<c:url value="${CSS_CUSTOM_FILE}"/>" rel="stylesheet">
<script>
	var PROFILE_PAGE = `${OPEN_USER_PROFILE_PAGE}`;
</script>
<script type="module" src="<c:url value='${JS_BUNDLE}'/>"></script>
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<jsp:include page="${TEMPLATE_USER_PROFILE_JSP}"/>
</main>
</body>
</html>
