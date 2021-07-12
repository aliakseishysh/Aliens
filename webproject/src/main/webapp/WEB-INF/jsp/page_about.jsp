<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 
<fmt:setLocale value="${CURRENT_LOCALE}" />
<fmt:setBundle basename="localization/localization" />
 -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 
<script src='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js'></script>
 -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js'></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">

<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<title>${TEXT[PAGE_ABOUT_TITLE]}</title>
</head>
<body>
	<main role="main" class="container">
		<jsp:include page="${TEMPLATE_NAV_JSP}" />
		<h1>
			${TEXT[PAGE_ABOUT_H1]}
		</h1>
	</main>
</body>
</html>