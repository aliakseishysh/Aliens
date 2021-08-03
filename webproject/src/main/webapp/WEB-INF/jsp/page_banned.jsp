<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="alien-tag" uri="aliens" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${TEXT[PAGE_BANNED_TITLE]}</title>
<alien-tag:css />
<body>
<main role="main" class="container">
	<jsp:include page="${TEMPLATE_NAV_JSP}"/>
	<div class="row">
		<div class="col-md-8">
			<h1>${TEXT[PAGE_BANNED_H1]}</h1>
		</div>
		<div class="content-section col-md-8">
			<div class="">
				<h2>${TEXT[PAGE_BANNED_H2]} ${BAN_INFO}</h2>
			</div>
		</div>
		<jsp:include page="${TEMPLATE_ALIENBAR_JSP}" />
	</div>
</main>
<alien-tag:variables />
<alien-tag:js />
</body>
</html>