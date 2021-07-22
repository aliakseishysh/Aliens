<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${TEXT[PAGE_BANNED_TITLE]}</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">

<link href="<c:url value="${CSS_MAIN}"/>" rel="stylesheet">
<script>
	var BANNED_PAGE = `${OPEN_BANNED_PAGE}`;
	var CONTROLLER = `${CONTROLLER}`;
	var COMMAND = `${COMMAND}`;
	var OPEN_HOME_PAGE = `${OPEN_HOME_PAGE}`;
</script>
<script type="module" src="<c:url value='${JS_BUNDLE}'/>"></script>
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

</body>
</html>