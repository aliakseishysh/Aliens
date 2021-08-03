<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="alien-tag" uri="aliens" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_ADMIN]}</title>
<alien-tag:css />
</head>
<body>
<main role="main" class="container">
	<h1>${TEXT[PAGE_ADMIN_H1]}</h1>
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<div class="content-section">
		<jsp:include page="${FORM_ALIEN_CREATE_JSP}"/>
		<jsp:include page="${FORM_BAN_UNBAN_USER_JSP}"/>
		<jsp:include page="${FORM_PROMOTE_DEMOTE_JSP}"/>
	</div>
</main>
<alien-tag:variables />
<alien-tag:js />
</body>
</html>
