<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="alien-tag" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_PROFILE_TITLE]}</title>
<alien-tag:css />
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<jsp:include page="${TEMPLATE_USER_PROFILE_JSP}"/>
</main>
<alien-tag:variables />
<alien-tag:js />
</body>
</html>
