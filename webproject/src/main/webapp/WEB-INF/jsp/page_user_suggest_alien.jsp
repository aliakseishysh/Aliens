<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="alien-tag" uri="aliens" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<html>
<head>
<title>${TEXT[PAGE_SUGGEST_ALIEN_TITLE]}</title>
<alien-tag:css />
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
<alien-tag:variables />
<alien-tag:js />
</body>
</html>
