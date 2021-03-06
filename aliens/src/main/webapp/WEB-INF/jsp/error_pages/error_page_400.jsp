<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="alien-tag" uri="aliens" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<alien-tag:css />
<title>${TEXT[ERROR_PAGE_400]}</title>
</head>
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}" />
	<h1>${TEXT[ERROR_PAGE]}</h1>
	<div class="content-section">
		<h2>${TEXT[ERROR_PAGE_400]}</h2><hr/>
		<h2>${TEXT[ERROR_PAGE_400_BAD_REQUEST]}</h2>
	</div>
</main>
<alien-tag:variables />
<alien-tag:js />
</body>
</html>