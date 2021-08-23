<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="alien-tag" uri="aliens" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
<title>${TEXT[PAGE_LOGIN_TITLE]}</title>
<alien-tag:css />
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<h1>${TEXT[PAGE_LOGIN_H1]}</h1>
	<jsp:include page="${FORM_LOGIN_JSP}"/>
</main>
<alien-tag:variables />
<alien-tag:js />
</body>
</html>

